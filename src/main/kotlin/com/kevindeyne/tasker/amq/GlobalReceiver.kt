package com.kevindeyne.tasker.amq

import com.kevindeyne.tasker.domain.Impact
import com.kevindeyne.tasker.domain.Progress
import com.kevindeyne.tasker.domain.Role
import com.kevindeyne.tasker.domain.Urgency
import com.kevindeyne.tasker.repositories.*
import com.kevindeyne.tasker.service.KeywordGeneration
import org.springframework.context.annotation.DependsOn
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@DependsOn("AMQConfig")
@Component
class GlobalReceiver(val issueRepository: IssueRepository, val tagcloud: TagcloudRepository, val knowledge: KnowledgeRepository, val timesheet : TimesheetRepository, val userRepository: UserRepository) {

	@JmsListener(destination = "issues", containerFactory = "jmsFactory")
	fun onMessage(m: AMQMessage) {
		when (m.type) {
			AMQMessageType.ISSUE_CREATE_OR_EDIT -> handleCreateOrEditIssue(m)
			AMQMessageType.ISSUE_PROGRESS -> handleProgress(m)
			AMQMessageType.ISSUE_URGENCY -> issueRepository.updateUrgency(getIdFromMessage(m), m.userId, Urgency.valueOf(m.value))
			AMQMessageType.ISSUE_IMPACT -> issueRepository.updateImpact(getIdFromMessage(m), m.userId, Impact.valueOf(m.value))
			AMQMessageType.ISSUE_ASSIGNEE -> handleAssignee(m)
			AMQMessageType.ISSUE_ADD_VERSION -> handleAddVersion(m)
			AMQMessageType.ISSUE_REMOVE_VERSION -> handleRemoveVersion(m)
			//else -> throw RuntimeException("Unknown message type")
		}
	}

	//TODO transactionality
	fun handleCreateOrEditIssue(message: AMQMessage) {
		val create = "new".equals(message.id)
		var issueId : Long
		
		val title = message.value
		val description = message.description
		
		if(create){
			issueId = issueRepository.create(title, description, message.userId, message.sprintId, message.projectId, message.userId)
		} else {
			issueId = message.id.toLong()
			issueRepository.update(issueId, title, description, message.userId)
		}
		
		handleTagcloudGeneration("$title. $description", issueId)
		
		if(create){
			println("Attempting auto-assignment ...")
			autoAssignment(issueId)
		}
	}

	fun handleTagcloudGeneration(msg: String, issueId : Long) = KeywordGeneration.generateKeywords(msg, "en").forEach { k -> tagcloud.addToIssueIfNotExists(k, issueId) }
	
	fun autoAssignment(issueId : Long) {
		val userId = knowledge.findMostSuitedCandidateForIssue(issueId)
		if(userId != null){
			println("Assigning issue to $userId")
			issueRepository.assign(issueId, userId)
		} else {
			println("Nobody to assign to, staying with current user")
		}
	}

	fun autoAssignment(issueId : Long?, role : Role) : Long? {
		if(issueId != null) { return knowledge.findMostSuitedCandidateForIssue(issueId, role) }
		return -1L
	}
	
	fun getIdFromMessage(message: AMQMessage) : Long = message.id.toLong()
	
	fun handleAssignee(message: AMQMessage){
		if(null !== message.issueId){
			var assignee = handlePredefinedAssignees(message)
			if(assignee == -1L) {
				assignee = userRepository.findByUsernameInProject(message.projectId, message.value)
			}
			issueRepository.assign(message.issueId, assignee)
		}
	}

	fun handlePredefinedAssignees(m: AMQMessage) : Long {
		var userId : Long? = -1L
		if("Myself" === m.value) {
			return m.userId
		} else if("Teamlead" === m.value) {
			userId = autoAssignment(m.issueId, Role.TEAM_LEADER)
		} else if("Tester" === m.value) {
			userId = autoAssignment(m.issueId, Role.TESTER)
		}

		if(userId != null){
			return userId
		} else {
			return -1L
		}
	}
	
	fun handleAddVersion(message: AMQMessage){
		val version = message.value
		val branch = message.value2
		
		if(null !== message.issueId){
			issueRepository.addVersion(message.issueId, message.projectId, version, branch)	
		}		
	}
	
	fun handleRemoveVersion(message: AMQMessage){
		val version = message.value
		val branch = message.value2
		
		if(null !== message.issueId){
			issueRepository.removeVersion(message.issueId, message.projectId, version, branch)
		}
	}
	
	fun handleProgress(m: AMQMessage){
		val status = Progress.valueOf(m.value)
		issueRepository.updateStatus(getIdFromMessage(m), m.userId, status)
		
		val issueId = getIdFromMessage(m)
		
		when(status){
			Progress.IN_PROGRESS -> timesheet.startTrackingIssue(issueId, m.userId)
			Progress.DONE -> handleSolved(issueId, m.userId)
			else -> timesheet.stopTrackingIssue(issueId, m.userId)
		}
	}
	
	fun handleSolved(issueId : Long, userId : Long) {
		timesheet.stopTrackingIssue(issueId, userId)		
		val tagcloud : List<Long> = tagcloud.findByIssues(issueId.toLong())
		tagcloud.forEach{ k -> knowledge.addToKnowledgeIfNotExists(k, userId) }
	}
}
package com.kevindeyne.tasker.repositories

import com.kevindeyne.tasker.controller.form.IssueResponse
import com.kevindeyne.tasker.controller.form.StandupResponse
import com.kevindeyne.tasker.domain.Impact
import com.kevindeyne.tasker.domain.InProgressIssue
import com.kevindeyne.tasker.domain.IssueListing
import com.kevindeyne.tasker.domain.Progress
import com.kevindeyne.tasker.domain.Urgency
import com.kevindeyne.tasker.domain.Workload
import com.kevindeyne.tasker.jooq.Tables
import com.kevindeyne.tasker.jooq.tables.records.IssueRecord
import com.kevindeyne.tasker.service.SecurityHolder
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.tools.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.stream.Collectors

@Component
open class IssueRepositoryImpl (val dsl: DSLContext) : IssueRepository {
	
	@Autowired
	lateinit var userRepo : UserRepository
	
	@Autowired
	lateinit var commentRepo : CommentRepository
	
	/*initial load*/
	@Transactional
	override fun findAllActiveForUserInCurrentSprint() : List<IssueListing> {
		return dsl.selectFrom(Tables.ISSUE)
			   .where(
				   Tables.ISSUE.ASSIGNED.eq(SecurityHolder.getUserId())
				   .and(Tables.ISSUE.SPRINT_ID.eq(SecurityHolder.getSprintId()))
				   .and(ACTIVE_ISSUE)
			   )
			   .orderBy(Tables.ISSUE.IMPORTANCE.desc(), Tables.ISSUE.CREATE_DATE.asc())
			   .fetch()
			   .parallelStream()
			   .map {
				  n -> IssueListing(n.get(Tables.ISSUE.ID),
									n.get(Tables.ISSUE.TITLE),
									abbreviate(n.get(Tables.ISSUE.DESCRIPTION)),
									n.get(Tables.ISSUE.DESCRIPTION),
									determineClass(n.get(Tables.ISSUE.WORKLOAD), n.get(Tables.ISSUE.STATUS), n.get(Tables.ISSUE.URGENCY)),
									n.get(Tables.ISSUE.IMPORTANCE))
			   }
			   .collect(Collectors.toList())
	}
	
	/*pulling*/
	@Transactional
	override fun findUpdateOnIssues(sprintId : Long, maxid : String) : List<IssueResponse> {
		return dsl.selectFrom(Tables.ISSUE)
			   .where(Tables.ISSUE.ASSIGNED.eq(SecurityHolder.getUserId()))
			   .and(Tables.ISSUE.ID.gt(maxid.toLong()))
			   .and(Tables.ISSUE.SPRINT_ID.eq(sprintId))
			   .and(ACTIVE_ISSUE)
			   .orderBy(Tables.ISSUE.IMPORTANCE.desc(), Tables.ISSUE.CREATE_DATE.asc())
			   .fetch()
			   .parallelStream()
			   .map { n -> mapIssueResponse(n, true) }
			   .collect(Collectors.toList())		
	}
	
	
	companion object {
		const val IMPORTANCE_CRITICAL_H_IMP = 10
		const val IMPORTANCE_CRITICAL_N_IMP = 9
		const val IMPORTANCE_CRITICAL_L_IMP = 8
		const val IMPORTANCE_PROGRESS_H_URG = 7
		const val IMPORTANCE_PROGRESS_H_IMP = 6
		const val IMPORTANCE_PROGRESS_NORMAL = 5
		const val IMPORTANCE_PROGRESS_LOW = 4
		const val IMPORTANCE_UNDECIDED = 3
		const val IMPORTANCE_HIGHIMPACT = 2
		const val IMPORTANCE_NORMAL = 1
		const val IMPORTANCE_LOWPRIO = 0
		
		val ACTIVE_ISSUE = (
				  Tables.ISSUE.STATUS.notIn(Progress.DONE.name, Progress.WAITING_FOR_FEEDBACK.name, Progress.BACKLOG.name, Progress.NEW.name)
				  .and(Tables.ISSUE.WORKLOAD.notEqual(-1))
			  ).or(
				   Tables.ISSUE.STATUS.eq(Progress.NEW.name)
				   .and(Tables.ISSUE.WORKLOAD.eq(-1))
			  )

	}
	
	fun determineClass(workload : Int, status : String, urgency : String) : String{
		
		if(Urgency.valueOf(urgency).equals(Urgency.IMMEDIATELY)){ return "critical-issue" }		
		if(workload == -1){ return "undetermined-issue" }		
		if(Progress.valueOf(status).equals(Progress.IN_PROGRESS)){ return "inprogress-issue" }
		
		return ""
	}
	
	@Transactional
	override fun findById(issueId : Long) : IssueResponse? {
		val response : IssueRecord? = dsl.selectFrom(Tables.ISSUE)
			   .where(Tables.ISSUE.ID.eq(issueId).and(Tables.ISSUE.ASSIGNED.eq(SecurityHolder.getUserId())))
			   .fetchOne()
		
		if(response != null){
			return response.map { n -> mapIssueResponse(n, false) }	
		} else {
			return null
		}	   
	}
	
	@Transactional
	override fun findHighestPrioForUser() : IssueResponse {
		return dsl.selectFrom(Tables.ISSUE)
			   .where(
				   Tables.ISSUE.ASSIGNED.eq(SecurityHolder.getUserId())
				   .and(Tables.ISSUE.SPRINT_ID.eq(SecurityHolder.getSprintId()))
				   .and(ACTIVE_ISSUE)
			   )
			   .orderBy(Tables.ISSUE.IMPORTANCE.desc(), Tables.ISSUE.CREATE_DATE.asc())
			   .limit(1)
			   .fetchAny()
		   	   .map { n -> mapIssueResponse(n, false) }
	}
	
	@Transactional
	override fun create(title : String, description : String, userId : Long, sprintId : Long, projectId : Long, assignedTo : Long) : Long {
		val timestamp = Timestamp(System.currentTimeMillis())
		val createAndUpdateUser = userId.toString()
		
		return dsl.insertInto(Tables.ISSUE,
				Tables.ISSUE.TITLE, Tables.ISSUE.DESCRIPTION, Tables.ISSUE.ASSIGNED, Tables.ISSUE.SPRINT_ID, Tables.ISSUE.PROJECT_ID,
				Tables.ISSUE.CREATE_USER, Tables.ISSUE.UPDATE_USER, Tables.ISSUE.CREATE_DATE, Tables.ISSUE.UPDATE_DATE)
		   .values(title, description, assignedTo, sprintId, projectId, createAndUpdateUser, createAndUpdateUser, timestamp, timestamp)
		   .returning(Tables.ISSUE.ID).fetchOne().get(Tables.ISSUE.ID);
	}
	
		
	@Transactional
	override fun update(issueId : Long, title : String, description : String, userId : Long) {
		val timestamp = Timestamp(System.currentTimeMillis())
		val updateUser = userId.toString()
		
		dsl.update(Tables.ISSUE)
			.set(Tables.ISSUE.TITLE, title)
			.set(Tables.ISSUE.DESCRIPTION, description)
			.set(Tables.ISSUE.UPDATE_USER, updateUser)
			.set(Tables.ISSUE.UPDATE_DATE, timestamp)
			.where(Tables.ISSUE.ID.eq(issueId))
			.execute()
	}
			
	@Transactional
	override fun updateStatus(issueId : Long, userId : Long, status : Progress) {
		val timestamp = Timestamp(System.currentTimeMillis())
		val updateUser = userId.toString()
		
		dsl.update(Tables.ISSUE)
			.set(Tables.ISSUE.STATUS, status.name)
			.set(Tables.ISSUE.UPDATE_USER, updateUser)
			.set(Tables.ISSUE.UPDATE_DATE, timestamp)
			.set(Tables.ISSUE.IMPORTANCE, determineImportance(issueId, userId, status))
			.where(Tables.ISSUE.ID.eq(issueId))
			.execute()
	}
	
	@Transactional
	override fun updateUrgency(issueId : Long, userId : Long, urgency : Urgency) {
		val timestamp = Timestamp(System.currentTimeMillis())
		val updateUser = userId.toString()
		
		dsl.update(Tables.ISSUE)
			.set(Tables.ISSUE.URGENCY, urgency.name)
			.set(Tables.ISSUE.UPDATE_USER, updateUser)
			.set(Tables.ISSUE.UPDATE_DATE, timestamp)
			.set(Tables.ISSUE.IMPORTANCE, determineImportance(issueId, userId, null, null, null, urgency))
			.where(Tables.ISSUE.ID.eq(issueId))
			.execute()
	}
	
	@Transactional
	override fun updateImpact(issueId : Long, userId : Long, impact : Impact) {
		val timestamp = Timestamp(System.currentTimeMillis())
		val updateUser = userId.toString()
		
		dsl.update(Tables.ISSUE)
			.set(Tables.ISSUE.IMPACT, impact.name)
			.set(Tables.ISSUE.UPDATE_USER, updateUser)
			.set(Tables.ISSUE.UPDATE_DATE, timestamp)
			.set(Tables.ISSUE.IMPORTANCE, determineImportance(issueId, userId, null, null, impact))
			.where(Tables.ISSUE.ID.eq(issueId))
			.execute()
	}
	
	@Transactional
	override fun updateWorkload(issueId : Long, userId : Long, workload : Workload){
		dsl.update(Tables.ISSUE)
			.set(Tables.ISSUE.WORKLOAD, workload.hours)
			.set(Tables.ISSUE.IMPORTANCE, determineImportance(issueId, userId, null, workload.hours))
			.where(Tables.ISSUE.ID.eq(issueId))
			.execute()
	}
	
	@Transactional
	override fun updateCritical(issueId : Long, userId : Long, sprintId : Long){
		val timestamp = Timestamp(System.currentTimeMillis())
		val updateUser = userId.toString()
		
		dsl.update(Tables.ISSUE)
			.set(Tables.ISSUE.IMPACT, Impact.HIGH.name)
			.set(Tables.ISSUE.URGENCY, Urgency.IMMEDIATELY.name)
			.set(Tables.ISSUE.WORKLOAD, Workload.HIGH.hours)
			.set(Tables.ISSUE.STATUS, Progress.IN_PROGRESS.name)
			.set(Tables.ISSUE.SPRINT_ID, sprintId)
			.set(Tables.ISSUE.UPDATE_USER, updateUser)
			.set(Tables.ISSUE.UPDATE_DATE, timestamp)
			.set(Tables.ISSUE.IMPORTANCE, IMPORTANCE_CRITICAL_H_IMP)
			.where(Tables.ISSUE.ID.eq(issueId))
			.execute()
	}
	
	@Transactional
	override fun assign(issueId : Long, userId : Long){
		val timestamp = Timestamp(System.currentTimeMillis())
		val updateUser = userId.toString()
		
		dsl.update(Tables.ISSUE)
			.set(Tables.ISSUE.ASSIGNED, userId)
			.set(Tables.ISSUE.UPDATE_USER, updateUser)
			.set(Tables.ISSUE.UPDATE_DATE, timestamp)
			.execute()
	}
	
	fun mapIssueResponse(n : Record?, abbreviate : Boolean) : IssueResponse {
		if(n == null){
			return IssueResponse()
		} else {
			val commentsForIssue = commentRepo.getCommentsForIssue(n.get(Tables.ISSUE.ID))
			return IssueResponse(n.get(Tables.ISSUE.ID),
							 n.get(Tables.ISSUE.TITLE),
							 if (abbreviate) abbreviate(n.get(Tables.ISSUE.DESCRIPTION)) else n.get(Tables.ISSUE.DESCRIPTION),
						     Progress.valueOf(n.get(Tables.ISSUE.STATUS)).text,
						     Urgency.valueOf(n.get(Tables.ISSUE.URGENCY)).text,
						     Impact.valueOf(n.get(Tables.ISSUE.IMPACT)).text,
						     "3.1.14",
						     userRepo.findUsernameById(n.get(Tables.ISSUE.CREATE_USER)),
						     SimpleDateFormat("dd MMMMM yyyy").format(n.get(Tables.ISSUE.CREATE_DATE)),
							 "SLA on time",
							 determineClass(n.get(Tables.ISSUE.WORKLOAD), n.get(Tables.ISSUE.STATUS), n.get(Tables.ISSUE.URGENCY)),
							 commentsForIssue,
							 n.get(Tables.ISSUE.IMPORTANCE))
		}
	}
	
	@Transactional
	override fun findStandupIssuesForSprint(sprintId : Long) : List<StandupResponse> {
		return ArrayList<StandupResponse>();
	}
	
	private fun abbreviate(field : String) : String = (StringUtils.abbreviate(field, 120)).replace(Regex("<[^>]*>"), "")
	
	override fun findAllInProgress() : List<InProgressIssue> {
		return findAllInProgress(SecurityHolder.getUserId(), SecurityHolder.getSprintId())
	}
	
	override fun findAllInProgress(userId : Long?, sprintId : Long?) : List<InProgressIssue> {
		return dsl.selectFrom(Tables.ISSUE)
			   .where(Tables.ISSUE.ASSIGNED.eq(userId))
			   .and(Tables.ISSUE.SPRINT_ID.eq(sprintId))
			   .and(Tables.ISSUE.STATUS.eq(Progress.IN_PROGRESS.name))
			   .orderBy(Tables.ISSUE.IMPORTANCE.desc(), Tables.ISSUE.CREATE_DATE.asc())
			   .fetch()
			   .parallelStream()
			   .map { n -> buildInProgressIssue(n.get(Tables.ISSUE.ID), n.get(Tables.ISSUE.TITLE)) }
			   .collect(Collectors.toList())
	}
	
	private fun buildInProgressIssue(issueId : Long, text : String) : InProgressIssue {
		return InProgressIssue(issueId, text)
	}
	
	fun determineImportance(issueId : Long, userId : Long, status : Progress? = null, workload : Int? = null, impact : Impact? = null, urgency : Urgency? = null) : Int {
		val response : IssueRecord? = dsl.selectFrom(Tables.ISSUE)
	   .where(Tables.ISSUE.ID.eq(issueId).and(Tables.ISSUE.ASSIGNED.eq(userId)))
	   .fetchOne()
		
		if(response == null) { return 0 }
		
		val impStatus : Progress = status ?: Progress.valueOf(response.get(Tables.ISSUE.STATUS))
		val impWorkload : Int = workload ?: response.get(Tables.ISSUE.WORKLOAD)
		val impImpact : Impact = impact ?: Impact.valueOf(response.get(Tables.ISSUE.IMPACT))
		val impUrgency : Urgency = urgency ?: Urgency.valueOf(response.get(Tables.ISSUE.URGENCY))
	
		return determineImportance(impStatus, userId, impWorkload, impImpact, impUrgency)
	}

	override fun determineImportance(status : Progress, userId : Long, workload : Int, impact : Impact, urgency : Urgency) : Int {
		if(Urgency.IMMEDIATELY.equals(urgency)){		
			when (impact) {
			    Impact.HIGH -> return IMPORTANCE_CRITICAL_H_IMP
			    Impact.MINIMAL -> return IMPORTANCE_CRITICAL_L_IMP
			    else ->  return IMPORTANCE_CRITICAL_N_IMP
			}
		}
		
		if(Progress.IN_PROGRESS.equals(status)){
			if(Urgency.IMMEDIATELY.equals(impact)){ return IMPORTANCE_PROGRESS_H_URG }
			if(Impact.HIGH.equals(impact)){ return IMPORTANCE_PROGRESS_H_IMP }			
			if(Urgency.MINIMAL.equals(impact) || Impact.MINIMAL.equals(impact)){ return IMPORTANCE_PROGRESS_LOW }
			
			return IMPORTANCE_PROGRESS_NORMAL
		}
		
		if(-1 == workload){ return IMPORTANCE_UNDECIDED }
		if(Impact.HIGH.equals(impact)){ return IMPORTANCE_HIGHIMPACT }
		if(Impact.MINIMAL.equals(impact)){ return IMPORTANCE_LOWPRIO }
		
		return IMPORTANCE_NORMAL
	}
}
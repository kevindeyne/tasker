package com.kevindeyne.tasker.repositories

import com.kevindeyne.tasker.controller.form.IssueResponse
import com.kevindeyne.tasker.controller.form.StandupResponse
import com.kevindeyne.tasker.domain.*
import java.sql.Timestamp
import java.time.LocalDate

interface IssueRepository {

	fun findAllActiveForUserInCurrentSprint(): List<IssueListing>

	fun findById(issueId: Long): IssueResponse?

	fun findHighestPrioForUser(): IssueResponse
	
	fun findUpdateOnMyIssues(sprintId: Long, userId: Long, lastUpdateAt: Timestamp): List<IssueResponse>
	
	fun findUpdateOnMyIssuesRemoved(sprintId: Long, userId: Long, lastUpdateAt: Timestamp): List<String>

	fun findUpdateOnTeamIssues(sprintId: Long, userId: Long, lastUpdateAt: Timestamp): List<IssueResponse>	

	fun findUpdateOnBacklog(projectId: Long, lastUpdateAt: Timestamp): List<IssueResponse>

	fun findStandupIssuesForSprint(sprintId: Long): List<StandupResponse>

	fun create(title: String, description: String, userId: Long, sprintId: Long, projectId: Long, assignedTo: Long): Long

	fun createInProgress(title: String, description: String, userId: Long, sprintId: Long, projectId: Long, assignedTo: Long): Long

	fun update(issueId: Long, title: String, description: String, userId: Long)

	fun updateStatus(issueId: Long, userId: Long, status: Progress)

	fun updateUrgency(issueId: Long, userId: Long, urgency: Urgency)

	fun updateImpact(issueId: Long, userId: Long, impact: Impact)

	fun updateWorkload(issueId: Long, userId: Long, workload: Workload)

	fun updateCritical(issueId: Long, userId: Long, sprintId: Long)

	fun assign(issueId: Long, userId: Long)

	fun findAllInProgress(): List<InProgressIssue>

	fun findAllInProgress(userId: Long?, sprintId: Long?): List<InProgressIssue>

	fun determineImportance(status: Progress, userId: Long, workload: Int, impact: Impact, urgency: Urgency): Int

	fun counterMyIssue(userId: Long, sprintId: Long): Int

	fun counterSprint(userId: Long, sprintId: Long): Int

	fun counterBacklog(projectId: Long): Int

	fun findAllActiveForTeamInCurrentSprint(sprintId: Long, userId: Long): List<IssueListing>

	fun findAllBacklogForProject(projectId: Long): List<IssueListing>
	
	fun addVersion(issueId: Long, projectId: Long, version: String, branch: String)
	
	fun removeVersion(issueId: Long, projectId: Long, version: String, branch: String)

	fun getIssueList(userId: Long, detailDate: LocalDate) : List<TimesheetDayListing>
}
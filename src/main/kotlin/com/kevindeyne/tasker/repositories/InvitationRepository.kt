package com.kevindeyne.tasker.repositories

import com.kevindeyne.tasker.controller.mappings.AcceptInvitationDTO
import com.kevindeyne.tasker.controller.mappings.InvitationDTO
import com.kevindeyne.tasker.domain.Role

interface InvitationRepository {

	fun create(email : String, key : String, userType : Role, projectId: Long) : InvitationDTO

    fun find(inviteID: String, inviteCode: String): InvitationDTO?

    fun findEmail(inviteID: String, inviteCode: String) : AcceptInvitationDTO?

    fun removeInvitation(inviteID : String)

}
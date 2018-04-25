package com.kevindeyne.tasker.repositories

interface ActivationRepository {

	fun registerActivation(userId : Long)

	fun hasActiveActivation(userId : Long) : Boolean

	fun deleteActivation(key : String)
}
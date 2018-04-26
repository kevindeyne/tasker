package com.kevindeyne.tasker.repositories

import com.kevindeyne.tasker.controller.timesheet.TimeUtils
import com.kevindeyne.tasker.jooq.Tables
import com.kevindeyne.tasker.service.EmailService
import org.jooq.DSLContext
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp
import java.util.*

@Component
@Transactional
open class ActivationRepositoryImpl (val dsl: DSLContext, val encoder : PasswordEncoder, val emailService: EmailService, val userRepository: UserRepository) : ActivationRepository {

	override fun registerActivation(userId : Long) {
		val newKey = Random().nextInt(Integer.MAX_VALUE).toString()

		val email = userRepository.findElementById(Tables.USER.EMAIL, userId)
		emailService.sendActivationMail(newKey, email)

		dsl.insertInto(Tables.ACTIVATION_PENDING,
				Tables.ACTIVATION_PENDING.USER_ID, Tables.ACTIVATION_PENDING.ACTIVATION_KEY, Tables.ACTIVATION_PENDING.VALID_UNTIL)
				.values(userId, newKey, TimeUtils.INSTANCE.timeStampInXHours(2) )
				.execute()
	}

	override fun hasActiveActivation(userId : Long) : Boolean {
		return dsl.fetchExists(
				dsl.selectOne()
				 .from(Tables.ACTIVATION_PENDING)
				 .where(Tables.ACTIVATION_PENDING.USER_ID.eq(userId)
					.and(Tables.ACTIVATION_PENDING.VALID_UNTIL.greaterOrEqual(Timestamp(System.currentTimeMillis())))
				 ))
	}

	override fun deleteActivation(key : String) {
		dsl.deleteFrom(Tables.ACTIVATION_PENDING)
				.where(Tables.ACTIVATION_PENDING.ACTIVATION_KEY.eq(key))
				.execute()
	}
}
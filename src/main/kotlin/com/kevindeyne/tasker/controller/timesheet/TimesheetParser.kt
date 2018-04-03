package com.kevindeyne.tasker.controller.timesheet

import com.kevindeyne.tasker.domain.TimesheetDay
import com.kevindeyne.tasker.domain.TimesheetEntry
import com.kevindeyne.tasker.domain.TimesheetWeek
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.util.*

enum class TimesheetParser() {
	
	INSTANCE;

	val tU = TimeUtils.INSTANCE

	fun getTimesheetDays(entries : MutableList<TimesheetEntry>, startPeriod : LocalDate, endPeriod : LocalDate) : List<TimesheetWeek> {
		val r : MutableList<TimesheetWeek> = mutableListOf()
		var days : MutableList<TimesheetDay> = mutableListOf()

		val full : MutableMap<String, TimesheetDay> = mutableMapOf()

		//make list of days O(1)
		val dayRange = ChronoUnit.DAYS.between(startPeriod, endPeriod)
		for(i in 0..dayRange) {
			val date = startPeriod.plusDays(i)
			val inactive = date.monthValue != (LocalDate.now().monthValue)

			val t = TimesheetDay(date.dayOfMonth.toString(),
					date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.UK),
					tU.isToday(date),
					inactive,
					0,
					0.0,
					tU.toString(date))

			val key = tU.toString(tU.localDateToDate(date))
			full[key] = t

			days.add(t)

			if(days.size == 7){
				r.add(TimesheetWeek(days))
				days = mutableListOf()
			}
		}

		var sortedEntries = entries.sortedWith(compareBy({ it.startDate })).toMutableList()

		var startDate : Date? = null
		var endDate : Date? = null

		var i = 0
		while (i < sortedEntries.size) {
			val entry = sortedEntries[i]
			val k = tU.toString(entry.startDate)
			val d = full[k]

			if (d != null){
				var end = entry.endDate
				if(!TimeUtils.INSTANCE.areDatesOnSameDay(entry.startDate, end)){
					end = Date.from(LocalDateTime.ofInstant(entry.startDate.toInstant(), ZoneId.systemDefault()).withHour(17).withMinute(0).atZone(ZoneId.systemDefault()).toInstant())
					val start = Date.from(LocalDateTime.ofInstant(entry.startDate.toInstant(), ZoneId.systemDefault()).plusDays(1).withHour(8).withMinute(0).atZone(ZoneId.systemDefault()).toInstant())
					val e2 = TimesheetEntry(start,
							entry.endDate,
							entry.avgWorkday,
							entry.issueName,
							entry.issueId)
					sortedEntries.add(e2)
					println("Not on the same day, adding: ${e2.startDate}, end: ${e2.endDate}")
				}

				if(startDate == null){
					startDate = entry.startDate
					endDate = end
				} else if (endDate != null) {
					if(TimeUtils.INSTANCE.areDatesOnSameDay(startDate, entry.startDate)){
						if(entry.startDate.after(endDate)){
							d.hours += calculateHour(startDate, endDate)

							startDate = entry.startDate
							endDate = end

							d.hours += calculateHour(startDate, endDate)
						} else {
							endDate = end
							if(i + 1 == entries.size) {
								d.hours += calculateHour(startDate, endDate)
							}
						}
					} else {
						println("diff day - start: ${startDate}, end: ${endDate}")
						val kPrev = tU.toString(startDate)
						val d2 = full[kPrev]
						if(d2 != null){
							d2.hours += calculateHour(startDate, endDate)
						}

						startDate = entry.startDate
						endDate = end
						d.hours += calculateHour(startDate, endDate)
					}
				}

				println("start: ${entry.startDate}, end: ${end}, hours: ${d.hours}")

				d.total++

				full[k] = d
				i++
			}
		}

		if(r.size == 0 && days.size > 0) {
			r.add(TimesheetWeek(days))
		}

		return r
	}

	private fun calculateHour(startDate: Date, endDate: Date): Int {
		val min = TimeUtils.INSTANCE.countMinutesBetween(startDate, endDate)
		val x = min / 60
		return x
	}

	fun determineStartDate() : LocalDate {
		val localDate = TimeUtils.INSTANCE.today().toLocalDateTime().toLocalDate()
		return localDate.withDayOfMonth(1).with( TemporalAdjusters.previous( DayOfWeek.SUNDAY ) )
	}
	
	fun determineEndDate() : LocalDate {
		val localDate = TimeUtils.INSTANCE.today().toLocalDateTime().toLocalDate()
		return localDate.withDayOfMonth(localDate.lengthOfMonth()).with( TemporalAdjusters.next( DayOfWeek.SATURDAY ) )
	}
}
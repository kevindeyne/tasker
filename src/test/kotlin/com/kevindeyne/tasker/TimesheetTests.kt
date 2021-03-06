package com.kevindeyne.tasker

import com.kevindeyne.tasker.controller.timesheet.TimeUtils
import com.kevindeyne.tasker.controller.timesheet.TimesheetParser
import com.kevindeyne.tasker.domain.TimesheetEntry
import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class TimesheetTests {
	
	val parser = TimesheetParser.INSTANCE
	val time = TimeUtils.INSTANCE
	
	val format = SimpleDateFormat("dd/MM/yyyy")
		
	@Test
	fun testGenerateDays() {
		val t = LocalDateTime.now().withHour(14)
		val y = LocalDateTime.now().withHour(2).minusDays(1)
		val today = toDate(t)
		val yesterday = toDate(y)

		val entry1 = TimesheetEntry(yesterday, time.addHours(yesterday, 4))
		val entry2 = TimesheetEntry(today, time.addHours(today, 4))
		
		val days = parser.getTimesheetDays(mutableListOf(entry1, entry2), y.toLocalDate(), t.toLocalDate())

		Assert.assertTrue(days[0].days.size > 0)
		Assert.assertEquals(1, days[0].days[0].total)
		Assert.assertEquals(4.0, days[0].days[0].hours, 0.0)
		Assert.assertEquals(1, days[0].days[1].total)
		Assert.assertEquals(4.0, days[0].days[1].hours, 0.0)
	}

	@Test
	fun testAreDatesOnSameDay() {
		val today = toDate(LocalDateTime.now())
		val yesterday = toDate(LocalDateTime.now().minusDays(1))

		Assert.assertFalse(time.areDatesOnSameDay(today, yesterday))
		Assert.assertTrue(time.areDatesOnSameDay(Date(1000), time.addHours(Date(1000), 4)))
		Assert.assertTrue(time.areDatesOnSameDay(Date(1000), Date(1100)))

		val t = LocalDateTime.now().withHour(14)
		val y = LocalDateTime.now().withHour(2)
		val t2 = toDate(t)
		val y2 = toDate(y)

		val entry1 = TimesheetEntry(y2, time.addHours(y2, 4))
		val entry2 = TimesheetEntry(t2, time.addHours(t2, 4))

		val days = parser.getTimesheetDays(mutableListOf(entry1, entry2), y.toLocalDate(), t.toLocalDate())

		Assert.assertTrue(days[0].days.size > 0)
		Assert.assertEquals(2, days[0].days[0].total)
		Assert.assertEquals(8.0, days[0].days[0].hours, 0.0)
	}
	
	@Test
	fun testMinuteCount() {
		val today = toDate(LocalDateTime.now().withHour(14).withMinute(0).withSecond(0))
		val todayPlusMinute = toDate(LocalDateTime.now().withHour(14).withMinute(1).withSecond(0))
		
		Assert.assertEquals(1, time.countMinutesBetween(today, todayPlusMinute))
	}
	
	@Test
	fun testMinuteCount2() {
		val today = toDate(LocalDateTime.now().withHour(14).withMinute(0).withSecond(0))
		val todayPlusMinute = toDate(LocalDateTime.now().withHour(15).withMinute(0).withSecond(0))
		
		Assert.assertEquals(60, time.countMinutesBetween(today, todayPlusMinute))
	}

	@Test
	fun testGenerateDaysMultipleSpans() {
		val startD = LocalDateTime.now().withHour(15).withMinute(0).minusDays(1)
		val endD = LocalDateTime.now().withHour(12).withMinute(0)
		val start = toDate(startD)
		val end = toDate(endD)

		val entry = TimesheetEntry(start, end)

		val days = parser.getTimesheetDays(mutableListOf(entry), startD.toLocalDate(), endD.toLocalDate())

		Assert.assertTrue(days[0].days.size > 0)
		Assert.assertEquals(1, days[0].days[0].total)
		Assert.assertEquals(2.0, days[0].days[0].hours, 0.0)
		Assert.assertEquals(1, days[0].days[1].total)
		Assert.assertEquals(4.0, days[0].days[1].hours, 0.0)
	}

	@Test
	fun testGenerateDaysOverlap() {
		val startD1 = LocalDateTime.now().withHour(6).withMinute(0)
		val endD1 = LocalDateTime.now().withHour(17).withMinute(0)

		val e1 = TimesheetEntry(toDate(startD1), toDate(endD1))

		val startD2 = LocalDateTime.now().withHour(7).withMinute(0)
		val endD2 = LocalDateTime.now().withHour(18).withMinute(0)
		val e2 = TimesheetEntry(toDate(startD2), toDate(endD2))

		val days = parser.getTimesheetDays(mutableListOf(e1, e2), startD1.toLocalDate(), endD2.toLocalDate())

		Assert.assertTrue(days[0].days.size > 0)
		Assert.assertEquals(2, days[0].days[0].total)
		Assert.assertEquals(12.0, days[0].days[0].hours, 0.0)
	}

	@Test
	fun testGenerateDaysOverlapReverse() {
		val startD1 = LocalDateTime.now().withHour(6).withMinute(0)
		val endD1 = LocalDateTime.now().withHour(17).withMinute(0)

		val e1 = TimesheetEntry(toDate(startD1), toDate(endD1))

		val startD2 = LocalDateTime.now().withHour(7).withMinute(0)
		val endD2 = LocalDateTime.now().withHour(18).withMinute(0)
		val e2 = TimesheetEntry(toDate(startD2), toDate(endD2))

		val days = parser.getTimesheetDays(mutableListOf(e2, e1), startD1.toLocalDate(), endD2.toLocalDate())

		Assert.assertTrue(days[0].days.size > 0)
		Assert.assertEquals(2, days[0].days[0].total)
		Assert.assertEquals(12.0, days[0].days[0].hours, 0.0)
	}

	@Test
	fun testGenerateDaysOverlap2() {
		val startD1 = LocalDateTime.now().withHour(6).withMinute(0)
		val endD1 = LocalDateTime.now().withHour(17).withMinute(0)
		val e1 = TimesheetEntry(toDate(startD1), toDate(endD1))

		val startD2 = LocalDateTime.now().withHour(7).withMinute(0)
		val endD2 = LocalDateTime.now().withHour(18).withMinute(0)
		val e2 = TimesheetEntry(toDate(startD2), toDate(endD2))

		val startD3 = LocalDateTime.now().withHour(12).withMinute(0)
		val endD3 = LocalDateTime.now().withHour(16).withMinute(0)
		val e3 = TimesheetEntry(toDate(startD3), toDate(endD3))

		val days = parser.getTimesheetDays(mutableListOf(e1, e2, e3), startD1.toLocalDate(), endD2.toLocalDate())

		Assert.assertTrue(days[0].days.size > 0)
		Assert.assertEquals(3, days[0].days[0].total)
		Assert.assertEquals(12.0, days[0].days[0].hours, 0.0)
	}


	@Test
	fun testGenerateDaysOverlap3() {
		val startD1 = LocalDateTime.now().withHour(6).withMinute(0)
		val endD1 = LocalDateTime.now().withHour(17).withMinute(0)
		val e1 = TimesheetEntry(toDate(startD1), toDate(endD1))

		val startD2 = LocalDateTime.now().withHour(7).withMinute(0)
		val endD2 = LocalDateTime.now().withHour(18).withMinute(0)
		val e2 = TimesheetEntry(toDate(startD2), toDate(endD2))

		val startD3 = LocalDateTime.now().withHour(12).withMinute(0)
		val endD3 = LocalDateTime.now().withHour(16).withMinute(0)
		val e3 = TimesheetEntry(toDate(startD3), toDate(endD3))

		val startD4 = LocalDateTime.now().withHour(10).withMinute(0)
		val endD4 = LocalDateTime.now().withHour(13).withMinute(0)
		val e4 = TimesheetEntry(toDate(startD4), toDate(endD4))

		val days = parser.getTimesheetDays(mutableListOf(e1, e4, e2, e3), startD1.toLocalDate(), endD2.toLocalDate())

		Assert.assertTrue(days[0].days.size > 0)
		Assert.assertEquals(4, days[0].days[0].total)
		Assert.assertEquals(12.0, days[0].days[0].hours, 0.0)
	}

	@Test
	fun testOverlap2() {
		val startD1 = LocalDateTime.now().withHour(6).withMinute(0)
		val endD1 = LocalDateTime.now().withHour(17).withMinute(0)
		val e1 = TimesheetEntry(toDate(startD1), toDate(endD1))

		val startD6 = LocalDateTime.now().plusDays(2).withHour(9).withMinute(0)
		val endD6 = LocalDateTime.now().plusDays(2).withHour(13).withMinute(0)
		val e6 = TimesheetEntry(toDate(startD6), toDate(endD6))

		val startD7 = LocalDateTime.now().plusDays(2).withHour(10).withMinute(0)
		val endD7 = LocalDateTime.now().plusDays(2).withHour(14).withMinute(0)
		val e7 = TimesheetEntry(toDate(startD7), toDate(endD7))

		val days = parser.getTimesheetDays(mutableListOf(e6, e1, e7), startD1.toLocalDate(), endD7.toLocalDate())

		Assert.assertTrue(days[0].days.size > 0)
		Assert.assertEquals(2, days[0].days[2].total)
		Assert.assertEquals(5.0, days[0].days[2].hours, 0.0)
	}

	@Test
	fun testOverlap() {
		val startD6 = LocalDateTime.now().plusDays(2).withHour(9).withMinute(0)
		val endD6 = LocalDateTime.now().plusDays(2).withHour(13).withMinute(0)
		val e6 = TimesheetEntry(toDate(startD6), toDate(endD6))

		val startD7 = LocalDateTime.now().plusDays(2).withHour(10).withMinute(0)
		val endD7 = LocalDateTime.now().plusDays(2).withHour(14).withMinute(0)
		val e7 = TimesheetEntry(toDate(startD7), toDate(endD7))

		val days = parser.getTimesheetDays(mutableListOf(e6, e7), startD6.toLocalDate(), endD7.toLocalDate())

		Assert.assertTrue(days[0].days.size > 0)
		Assert.assertEquals(2, days[0].days[0].total)
		Assert.assertEquals(5.0, days[0].days[0].hours, 0.0)
	}

	@Test
	fun testComplexTimesheet() {
		val startD1 = LocalDateTime.now().withHour(8).withMinute(0)
		val endD1 = LocalDateTime.now().withHour(12).withMinute(0)
		val e1 = TimesheetEntry(toDate(startD1), toDate(endD1))

		val startD2 = LocalDateTime.now().withHour(14).withMinute(0)
		val endD2 = LocalDateTime.now().plusDays(1).withHour(12).withMinute(0)
		val e2 = TimesheetEntry(toDate(startD2), toDate(endD2))

		val startD3 = LocalDateTime.now().plusDays(1).withHour(13).withMinute(0)
		val endD3 = LocalDateTime.now().plusDays(1).withHour(19).withMinute(0)
		val e3 = TimesheetEntry(toDate(startD3), toDate(endD3))

		val startD4 = LocalDateTime.now().plusDays(1).withHour(14).withMinute(0)
		val endD4 = LocalDateTime.now().plusDays(1).withHour(17).withMinute(0)
		val e4 = TimesheetEntry(toDate(startD4), toDate(endD4))

		val startD5 = LocalDateTime.now().plusDays(1).withHour(15).withMinute(0)
		val endD5 = LocalDateTime.now().plusDays(1).withHour(18).withMinute(0)
		val e5 = TimesheetEntry(toDate(startD5), toDate(endD5))


		val startD6 = LocalDateTime.now().plusDays(2).withHour(9).withMinute(0)
		val endD6 = LocalDateTime.now().plusDays(2).withHour(13).withMinute(0)
		val e6 = TimesheetEntry(toDate(startD6), toDate(endD6))

		val startD7 = LocalDateTime.now().plusDays(2).withHour(10).withMinute(0)
		val endD7 = LocalDateTime.now().plusDays(2).withHour(14).withMinute(0)
		val e7 = TimesheetEntry(toDate(startD7), toDate(endD7))


		val startD8 = LocalDateTime.now().plusDays(3).withHour(9).withMinute(0)
		val endD8 = LocalDateTime.now().plusDays(3).withHour(10).withMinute(0)
		val e8 = TimesheetEntry(toDate(startD8), toDate(endD8))

		val startD9 = LocalDateTime.now().plusDays(3).withHour(11).withMinute(0)
		val endD9 = LocalDateTime.now().plusDays(3).withHour(12).withMinute(0)
		val e9 = TimesheetEntry(toDate(startD9), toDate(endD9))

		val days = parser.getTimesheetDays(mutableListOf(e2, e1, e4, e5, e3, e9, e8, e6, e7), startD1.toLocalDate(), endD9.toLocalDate())

		Assert.assertTrue(days[0].days.size > 0)
		Assert.assertEquals(2, days[0].days[0].total)
		Assert.assertEquals(7.0, days[0].days[0].hours, 0.0)
		Assert.assertEquals(4, days[0].days[1].total)
		Assert.assertEquals(10.0, days[0].days[1].hours, 0.0)
		Assert.assertEquals(2, days[0].days[2].total)
		Assert.assertEquals(5.0, days[0].days[2].hours, 0.0)
		Assert.assertEquals(2, days[0].days[3].total)
		Assert.assertEquals(2.0, days[0].days[3].hours, 0.0)
	}

	fun toDate(localDate : LocalDateTime) : Date = TimeUtils.INSTANCE.localDateToDate(localDate)

	//@Test
	fun generateTimeForStart() {

	}
		
	//@Test
	fun generateTimeForEnd() {
		
	}
}

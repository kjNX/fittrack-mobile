package com.example.fittrack_mobile.reminders

import org.threeten.bp.Duration
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

object ReminderTimeUtils {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    fun getCurrentTime(): LocalTime = LocalTime.now()

    fun formatTime(time: LocalTime): String = time.format(formatter)

    fun parseTime(timeStr: String): LocalTime = LocalTime.parse(timeStr, formatter)

    fun hasReminderPassed(programmedTime: LocalTime): Boolean = getCurrentTime().isAfter(programmedTime)

    fun minutesUntilReminder(targetTime: LocalTime): Long {
        val now = getCurrentTime()
        return Duration.between(now, targetTime).toMinutes()
    }
}

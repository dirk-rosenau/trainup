package com.trainup.common.extensions

import java.util.*

fun Long?.isToday(): Boolean {
    if (this != null) {
        val calendarExercise = getCalendarWithDayStartingAtZero(this)
        val calendarToday = getCalendarWithDayStartingAtZero(System.currentTimeMillis())
        return calendarToday.timeInMillis == calendarExercise.timeInMillis
    } else return false
}

private fun getCalendarWithDayStartingAtZero(timeInMillis: Long): Calendar {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeInMillis
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar
}
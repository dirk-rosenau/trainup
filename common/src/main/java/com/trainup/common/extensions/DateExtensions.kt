package com.trainup.common.extensions

import com.trainup.common.R
import com.trainup.common.util.StringResource
import com.trainup.common.util.toStrResource
import java.text.SimpleDateFormat
import java.util.*

fun Long?.isToday(): Boolean {
    if (this != null) {
        val timeExercise = this.convertToDayStartMillis()
        val timeToday = System.currentTimeMillis().convertToDayStartMillis()
        return timeToday == timeExercise
    } else return false
}

fun Long.convertToDayStartMillis(): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.timeInMillis
}

fun Long.convertToLocalDateString(): StringResource =
    R.string.rawString.toStrResource(
        SimpleDateFormat(
            "dd.MM.yyyy",
            Locale.getDefault()
        ).format(this)
    )

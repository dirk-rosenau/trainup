package com.dr.trainup.statistics.vm

// TODO zusammenfassen recycler list intent?
sealed class StatisticsIntent
data class SelectExerciseIntent(val stationId: Long, val date: Long) : StatisticsIntent()

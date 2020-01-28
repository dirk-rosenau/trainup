package com.dr.trainup.ui.model

data class ExerciseOverviewItem(
    val id: Long,
    val name: String,
    var selected: Boolean,
    val lastExercise: String
)

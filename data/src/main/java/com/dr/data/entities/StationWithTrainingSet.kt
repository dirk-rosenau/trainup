package com.dr.data.entities

import androidx.room.Embedded

data class StationWithTrainingSet(
    @Embedded
    val station: Station,
    @Embedded
    val trainingSet: TrainingSet?
)

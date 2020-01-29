package com.dr.data.entities

import androidx.room.Embedded

data class StationWithTime(
    @Embedded
    val station: Station,
    @Embedded
    val trainingSet: TrainingSet?
)

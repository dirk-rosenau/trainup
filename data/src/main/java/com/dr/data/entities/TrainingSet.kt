package com.dr.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// TODO naming
@Entity(
    tableName = "training_sets",
    foreignKeys = [ForeignKey(
        entity = Station::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("stationId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class TrainingSet(
    @PrimaryKey(autoGenerate = true)
    val traininSetId: Long,
    val stationId: Long,
    val date: Long,
    val weight: Float,
    val weightUnit: String,
    val repeats: Int
)

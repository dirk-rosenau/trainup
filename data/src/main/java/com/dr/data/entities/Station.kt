package com.dr.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stations")
data class Station(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val actualWeight: Int,
    val actualWeightUnit: String,
    val actualRepeats: Int,
    val seatPosition: String?
// TODO image
)
package com.dr.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "stations")
data class Station(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val seatPosition: String
// TODO image
) {

}
package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrainUnit(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val imagePath: String
)

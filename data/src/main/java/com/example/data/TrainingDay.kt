package com.example.data

import androidx.room.PrimaryKey
import java.util.*

data class TrainingDay(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val date: Date)

package com.dr.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TrainingDay(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val date: Date)

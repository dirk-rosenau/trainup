package com.example.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = TrainingSet::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("setId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class UnitProperty(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val setId: Long,
    val name: String,
    val unit: String,
    val value: Int
)

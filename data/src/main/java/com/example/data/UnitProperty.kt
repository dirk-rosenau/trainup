package com.example.data

import androidx.room.PrimaryKey

data class UnitProperty(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val unit: String,
    val value: Int
)

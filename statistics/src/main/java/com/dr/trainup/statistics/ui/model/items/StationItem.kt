package com.dr.trainup.statistics.ui.model.items

data class StationItem(
    val name: String,
    val weight: String,
    val weightUnit: String,
    val amount: Int
) : ItemContainer
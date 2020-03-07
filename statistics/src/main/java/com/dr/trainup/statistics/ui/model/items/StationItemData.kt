package com.dr.trainup.statistics.ui.model.items

data class StationItemData(
    val name: String,
    val weight: Float,
    val weightUnit: String,
    val amount: Int
) : ItemContainer

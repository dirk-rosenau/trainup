package com.dr.trainup.statistics.ui.model

import com.dr.trainup.statistics.ui.model.items.ItemContainer

interface Groupable {
    fun getItemContainer(): ItemContainer
    fun getChildren(): List<Groupable>?
    fun getChildAt(position: Int): Groupable?
}

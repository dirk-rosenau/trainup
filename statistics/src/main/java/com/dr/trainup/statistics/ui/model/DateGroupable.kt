package com.dr.trainup.statistics.ui.model

import com.dr.trainup.statistics.ui.model.items.DateItemContainer
import com.dr.trainup.statistics.ui.model.items.ItemContainer

class DateGroupable(
    private val item: DateItemContainer,
    private val children: List<Groupable>
) :
    Groupable {

    override fun getItemContainer(): ItemContainer = item

    override fun getChildren(): List<Groupable>? = children

    override fun getChildAt(position: Int): Groupable? = children.get(position)
}
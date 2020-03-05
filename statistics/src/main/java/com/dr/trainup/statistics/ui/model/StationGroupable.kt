package com.dr.trainup.statistics.ui.model

import com.dr.trainup.statistics.ui.model.items.ItemContainer
import com.dr.trainup.statistics.ui.model.items.StationItem

// TODO wird evtl nicht gebraucht
class StationGroupable(private val stationItem: StationItem) :
    Groupable {

    override fun getItemContainer(): ItemContainer = stationItem

    override fun getChildren(): List<Groupable>? = null

    override fun getChildAt(position: Int): Groupable? = null
}
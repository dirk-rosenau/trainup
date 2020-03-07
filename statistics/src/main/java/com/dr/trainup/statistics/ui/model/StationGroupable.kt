package com.dr.trainup.statistics.ui.model

import com.dr.trainup.statistics.ui.model.items.ItemContainer
import com.dr.trainup.statistics.ui.model.items.StationItemData

// TODO wird evtl nicht gebraucht
class StationGroupable(private val stationItemData: StationItemData) :
    Groupable {

    override fun getItemContainer(): ItemContainer = stationItemData

    override fun getChildren(): List<Groupable>? = null

    override fun getChildAt(position: Int): Groupable? = null
}

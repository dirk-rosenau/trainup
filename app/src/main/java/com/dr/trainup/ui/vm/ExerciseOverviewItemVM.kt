package com.dr.trainup.ui.vm

import com.dr.data.entities.Station

class ExerciseOverviewItemVM(
    private val station: Station,
    private val listener: (Long) -> Unit
) {
    fun onItemClick() {
        listener.invoke(station.id)
    }

    val stationName = station.name
}

package com.dr.trainup

import com.dr.data.entities.Station

class ExerciseOverviewItemViewModel(
    private val station: Station,
    private val listener: (Long) -> Unit
) {
    fun onItemClick() {
        listener.invoke(station.id)
    }

    val stationName = station.name
}

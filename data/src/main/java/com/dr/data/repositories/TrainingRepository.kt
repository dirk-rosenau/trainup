package com.dr.data.repositories

import com.dr.data.entities.Station
import io.reactivex.Observable

interface TrainingRepository {

    fun getStations(): Observable<List<Station>>
}

package com.dr.data.repositories

import com.dr.data.AppDatabase
import com.dr.data.entities.Station
import io.reactivex.Observable
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(private val database: AppDatabase) : TrainingRepository {

    override fun getStations(): Observable<List<Station>> {
        val stationList = listOf(
            Station(0, "Bankdrücken", "4"),
            Station(1, "Liegestütze", "-"),
            Station(2, "Butterfly", "1")
        )
        return Observable.just(stationList)
    }
}

package com.dr.data.repositories

import com.dr.data.AppDatabase
import com.dr.data.entities.Station
import com.dr.data.entities.TrainingSet
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(private val database: AppDatabase) :
    TrainingRepository {

    // TODO use db
    override fun getStations(): Observable<List<Station>> {
        val stationList = listOf(
            Station(0, "Bankdrücken", "4"),
            Station(1, "Liegestütze", "-"),
            Station(2, "Butterfly", "1")
        )
        return Observable.just(stationList)
    }

    override fun saveStation(station: Station): Single<Long> {
        return Single.fromCallable {
            database.stationDao().insertStation(station)
        }.subscribeOn(Schedulers.io())
    }

    override fun saveSet(set: TrainingSet): Single<Long> {
        return Single.fromCallable {
            database.trainingSetDao().insertTrainingSet(set)
        }.subscribeOn(Schedulers.io())
    }
}

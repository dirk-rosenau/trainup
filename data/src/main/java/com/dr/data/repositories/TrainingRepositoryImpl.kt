package com.dr.data.repositories

import com.dr.data.AppDatabase
import com.dr.data.entities.Station
import com.dr.data.entities.TrainingSet
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(private val database: AppDatabase) :
    TrainingRepository {
    override fun getInitialTrainingSetForStation(id: Long): Observable<TrainingSet> =
        database.trainingSetDao().getInitialTrainingSet(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun getStation(id: Long): Observable<Station> =
        database.stationDao().getStation(id).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )


    override fun getStations(): Observable<List<Station>> =
        database.stationDao().getStations().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


    override fun saveStation(station: Station): Single<Long> {
        return Single.fromCallable {
            database.stationDao().insertStation(station)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveSet(set: TrainingSet): Single<Long> {
        return Single.fromCallable {
            database.trainingSetDao().insertTrainingSet(set)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}

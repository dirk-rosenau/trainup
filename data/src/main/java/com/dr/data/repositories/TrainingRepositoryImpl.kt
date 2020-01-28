package com.dr.data.repositories

import com.dr.data.AppDatabase
import com.dr.data.entities.Station
import com.dr.data.entities.StationWithTime
import com.dr.data.entities.TrainingSet
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.completable.CompletableFromCallable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(private val database: AppDatabase) :
    TrainingRepository {
    private val timeToSubstract = 5 * 60 * 60 * 1000
    private val pastTime
        get() = System.currentTimeMillis() - timeToSubstract

    override fun getTrainingSetsForActualTraining(stationId: Long): Observable<List<TrainingSet>> {

        return database.trainingSetDao().getTrainingSetsForLastHours(stationId, pastTime)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getInitialTrainingSetForStation(id: Long): Observable<TrainingSet> =
        database.trainingSetDao().getInitialTrainingSet(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun getStation(id: Long): Observable<Station> =
        database.stationDao().getStation(id).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )


    override fun getStations(): Observable<List<Station>> =
        database.stationDao().getStations().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


    override fun getFirstStation(): Observable<Station> =
        database.stationDao().getFirstStation().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )

    override fun getStationWithTime(): Observable<List<StationWithTime>> =
        database.stationDao().getStationWithTime().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )

    override fun saveStation(station: Station): Single<Long> {
        return Single.fromCallable {
            database.stationDao().insertStation(station)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteStation(id: Long): Completable {
        return CompletableFromCallable {
            database.stationDao().deleteStation(id)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateStation(station: Station): Completable {
        return Completable.fromCallable {
            database.stationDao().updateStation(station)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveSet(set: TrainingSet): Single<Long> {
        return Single.fromCallable {
            database.trainingSetDao().insertTrainingSet(set)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}

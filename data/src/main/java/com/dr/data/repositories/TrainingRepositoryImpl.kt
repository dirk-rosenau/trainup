package com.dr.data.repositories

import com.dr.data.AppDatabase
import com.dr.data.entities.Station
import com.dr.data.entities.StationWithTrainingSet
import com.dr.data.entities.TrainingSet
import io.reactivex.*
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
            .compose(applySchedulers())
    }

    override fun getInitialTrainingSetForStation(id: Long): Observable<TrainingSet> =
        database.trainingSetDao().getInitialTrainingSet(id).compose(applySchedulers())


    override fun getStation(id: Long): Observable<Station> =
        database.stationDao().getStation(id).compose(applySchedulers())

    override fun getStations(): Observable<List<Station>> =
        database.stationDao().getStations().compose(applySchedulers())

    override fun getFirstStation(): Observable<Station> =
        database.stationDao().getFirstStation().compose(applySchedulers())

    override fun getStationsWithLatestEditedTrainingSet(): Observable<List<StationWithTrainingSet>> =
        database.stationDao().getStatiosWithLatestEditedTime().compose(applySchedulers())

    override suspend fun getStationsWithTrainingSets(): List<StationWithTrainingSet> =
        database.stationDao().getStationsWithTime()


    override suspend fun saveStation(station: Station): Long {
        with(database.stationDao()) {
            if (existsStation(station.id)) {
                coroutineUpdateStation(station)
                return -1
            } else {
                return coroutineInsertStation(station)
            }
        }
    }

    override fun deleteStation(id: Long): Completable {
        return CompletableFromCallable {
            database.stationDao().deleteStation(id)
        }.compose(applySchedulersCompletable())
    }

    override fun saveSet(set: TrainingSet): Single<Long> {
        return Single.fromCallable {
            database.trainingSetDao().insertTrainingSet(set)
        }.compose(applySchedulersSingle())
    }

    private fun <T> applySchedulersSingle(): SingleTransformer<T, T> {
        return SingleTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    private fun applySchedulersCompletable(): CompletableTransformer {
        return CompletableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    private fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

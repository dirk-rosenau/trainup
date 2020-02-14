package com.dr.data.repositories

import com.dr.data.entities.Station
import com.dr.data.entities.StationWithTime
import com.dr.data.entities.TrainingSet
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface TrainingRepository {

    fun getStations(): Observable<List<Station>>

    fun getFirstStation(): Observable<Station>

    fun getStation(id: Long): Observable<Station>

    fun saveStation(station: Station): Single<Long>

    fun deleteStation(id: Long): Completable

    fun updateStation(station: Station): Completable

    fun saveSet(set: TrainingSet): Single<Long>

    fun getInitialTrainingSetForStation(id: Long): Observable<TrainingSet>

    fun getTrainingSetsForActualTraining(stationId: Long): Observable<List<TrainingSet>>
    fun getStationsWithLatestEditedTime(): Observable<List<StationWithTime>>
}

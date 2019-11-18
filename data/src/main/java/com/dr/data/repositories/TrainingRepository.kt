package com.dr.data.repositories

import com.dr.data.entities.Station
import com.dr.data.entities.TrainingSet
import io.reactivex.Observable
import io.reactivex.Single

interface TrainingRepository {

    fun getStations(): Observable<List<Station>>

    fun getStation(id: Long): Observable<Station>

    fun saveStation(station: Station): Single<Long>
    fun saveSet(set: TrainingSet): Single<Long>

    fun getInitialTrainingSetForStation(id: Long): Observable<TrainingSet>

    fun getTrainingSetsForActualTraining(stationId: Long): Observable<TrainingSet>
}

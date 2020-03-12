package com.dr.data.dao

import androidx.room.*
import com.dr.data.entities.Station
import com.dr.data.entities.StationWithTrainingSet
import io.reactivex.Observable


@Dao
interface StationDao {
    @Query("SELECT * FROM stations")
    fun getStations(): Observable<List<Station>>

    @Query("SELECT * FROM stations WHERE id = :id")
    fun getStation(id: Long): Observable<Station>


    @Query("SELECT * FROM stations LIMIT 1")
    fun getFirstStation(): Observable<Station>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStation(station: Station): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateStation(station: Station)

    @Query("DELETE FROM stations WHERE id = :id")
    fun deleteStation(id: Long)

    @Query("SELECT stations.*, training_sets.* FROM stations LEFT JOIN training_sets ON training_sets.stationId = stations.id GROUP BY stations.id ORDER BY training_sets.date DESC")
    fun getStatiosWithLatestEditedTime(): Observable<List<StationWithTrainingSet>>

    @Query("SELECT stations.*, training_sets.* FROM stations JOIN training_sets ON training_sets.stationId = stations.id")
    suspend fun getStationsWithTime(): List<@JvmSuppressWildcards StationWithTrainingSet>

    @Query("SELECT EXISTS (SELECT id FROM stations WHERE id = :id)")
    suspend fun existsStation(id: Long): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun coroutineInsertStation(station: Station): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun coroutineUpdateStation(station: Station)
}

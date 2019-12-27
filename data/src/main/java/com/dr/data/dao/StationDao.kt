package com.dr.data.dao

import androidx.room.*
import com.dr.data.entities.Station
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

}
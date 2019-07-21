package com.dr.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.dr.data.entities.Station
import io.reactivex.Observable


@Dao
interface StationDao {
    @Query("SELECT * FROM stations")
    fun getStations(): Observable<List<Station>>
}
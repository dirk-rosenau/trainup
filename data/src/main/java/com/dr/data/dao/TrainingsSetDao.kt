package com.dr.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dr.data.entities.TrainingSet

@Dao
interface TrainingsSetDao {
//    @Query("SELECT * FROM training_sets WHERE stationId = :stationId AND date BETWEEN :dayStart AND :dayEnd")
//    fun getLastDayExercise(stationId: Long, dayStart: Long, dayEnd: Long): Exercise

    @Query("SELECT * FROM training_sets WHERE stationId = :stationId ORDER BY date ASC LIMIT 1")
    fun getInitialTrainingSet(stationId: Long): TrainingSet

    @Insert
    fun insertTrainingSet(set: TrainingSet): Long
}
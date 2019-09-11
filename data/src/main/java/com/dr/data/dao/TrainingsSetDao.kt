package com.dr.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.dr.data.entities.TrainingSet

@Dao
interface TrainingsSetDao {
//    @Query("SELECT * FROM training_sets WHERE stationId = :stationId AND date BETWEEN :dayStart AND :dayEnd")
//    fun getLastDayExercise(stationId: Long, dayStart: Long, dayEnd: Long): Exercise

    @Insert
    fun insertTrainingSet(set: TrainingSet): Long
}
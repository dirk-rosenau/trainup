package com.dr.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dr.data.entities.TrainingDay
import io.reactivex.Flowable

@Dao
interface TrainingDayDao {

    @Query("SELECT * FROM training_days")
    fun getTrainingDays(): Flowable<List<TrainingDay>>

    @Insert
    fun insert(trainingDay: TrainingDay)
}

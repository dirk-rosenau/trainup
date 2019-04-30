package com.dr.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.dr.data.entities.Exercise
import io.reactivex.Flowable

@Dao
interface ExeciseDao {

    @Query("SELECT * FROM exercises")
    fun getExercises(): Flowable<List<Exercise>>

    //   @Insert(onConflict = OnConflictStrategy.REPLACE)
}

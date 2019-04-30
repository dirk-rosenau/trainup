package com.dr.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.dr.data.entities.Exercise
import io.reactivex.Observable

@Dao
interface ExeciseDao {

    @Query("SELECT * FROM exercises")
    fun getExercises(): Observable<List<Exercise>>

    //   @Insert(onConflict = OnConflictStrategy.REPLACE)
}

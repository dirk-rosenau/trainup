package com.dr.data.repositories

import com.dr.data.AppDatabase
import com.dr.data.entities.Exercise
import io.reactivex.Observable
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(private val database: AppDatabase) : TrainingRepository {

    override fun getExercises(): Observable<List<Exercise>> =
        database.excerciseDao().getExercises()


}

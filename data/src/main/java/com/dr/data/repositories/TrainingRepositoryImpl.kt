package com.dr.data.repositories

import com.dr.data.AppDatabase
import com.dr.data.entities.Exercise
import io.reactivex.Observable
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(private val database: AppDatabase) : TrainingRepository {

    override fun getExercises(): Observable<List<Exercise>> {

        // database.exerciseDao().getExercises()
        val exerciseList = listOf(
            Exercise(0, "Bankpflücken", null),
            Exercise(1, "Liegestütze", null),
            Exercise(2, "Situps", null)
        )
        return Observable.just(exerciseList)

    }


}

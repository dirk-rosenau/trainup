package com.dr.data.repositories

import com.dr.data.entities.Exercise
import io.reactivex.Observable

class TrainingRepositoryImpl : TrainingRepository {
    override fun getExercises(): Observable<Exercise> {
        TODO("talk with room!")
    }


}

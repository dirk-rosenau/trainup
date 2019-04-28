package com.dr.data.repositories

import com.dr.data.entities.Exercise
import io.reactivex.Observable

interface TrainingRepository {
    fun getExercises(): Observable<Exercise>
}

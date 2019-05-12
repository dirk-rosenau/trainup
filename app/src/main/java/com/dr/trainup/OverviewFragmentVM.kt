package com.dr.trainup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dr.data.entities.Exercise
import com.dr.data.repositories.TrainingRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class OverviewFragmentVM @Inject constructor(private val trainingRepository: TrainingRepository) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    private val exerciseData = MutableLiveData<List<Exercise>>()
    fun getExerciseData(): LiveData<List<Exercise>> = exerciseData

    fun loadExercises() {
        trainingRepository.getExercises().observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = { processData(it) },
                onError = { processError(it) }).addTo(compositeDisposable)
    }

    private fun processData(it: List<Exercise>?) {
        exerciseData.value = it
    }

    private fun processError(it: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

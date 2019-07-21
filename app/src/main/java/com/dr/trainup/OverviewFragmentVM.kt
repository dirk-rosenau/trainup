package com.dr.trainup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dr.data.entities.Station
import com.dr.data.repositories.TrainingRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class OverviewFragmentVM @Inject constructor(private val trainingRepository: TrainingRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val stationData = MutableLiveData<List<Station>>()
    fun getStationData(): LiveData<List<Station>> = stationData

    fun loadExercises() {
        trainingRepository.getStations().observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = { processData(it) },
                onError = { processError(it) }).addTo(compositeDisposable)
    }

    private fun processData(it: List<Station>?) {
        stationData.value = it
    }

    private fun processError(it: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

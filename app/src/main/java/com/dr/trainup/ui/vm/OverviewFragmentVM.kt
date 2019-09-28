package com.dr.trainup.ui.vm

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

class OverviewFragmentVM @Inject constructor(private val trainingRepository: TrainingRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _actionModeEnabled = MutableLiveData<Boolean>(false)
    val actionModeEnabled: LiveData<Boolean> = _actionModeEnabled

    private val _stationData = MutableLiveData<List<Station>>()
    val stationData: LiveData<List<Station>> = _stationData

    fun loadExercises() {
        trainingRepository.getStations().observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { onStationDataLoaded(it) },
                onError = { processError(it) }).addTo(compositeDisposable)
    }

    private fun onStationDataLoaded(it: List<Station>?) {
        _stationData.value = it
    }

    private fun processError(it: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

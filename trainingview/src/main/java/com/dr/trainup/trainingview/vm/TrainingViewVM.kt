package com.dr.trainup.trainingview.vm

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.dr.data.entities.Station
import com.dr.data.entities.TrainingSet
import com.dr.data.repositories.TrainingRepository
import com.dr.trainup.trainingview.R
import com.trainup.common.ObservableViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TrainingViewVM @Inject constructor(
    app: Application,
    val trainingRepository: TrainingRepository
) : ObservableViewModel(app) {

    private val disposables = CompositeDisposable()

    val trainingSets = mutableListOf<TrainingSet>()

    private var station: Station? = null
        private set(value) {
            if (value != field) {
                field = value
                notifyPropertyChanged(BR.trainingName)
                notifyPropertyChanged(BR.weight)
                notifyPropertyChanged(BR.repeats)
            }
        }

    @get:Bindable
    val trainingName: String?
        get() {
            return station?.name
        }

    @get:Bindable
    val weight: String?
        get() = "${station?.actualWeight.toString()} ${station?.actualWeightUnit}"


    @get:Bindable
    val repeats: String?
        get() = "${station?.actualRepeats.toString()} ${getApplication<Application>().getString(R.string.times)}"

    @get:Bindable
    val labelFinishSet: String
        get() = getApplication<Application>().getString(R.string.finish_set, 1)

    fun init(stationId: Long) {
        trainingRepository.getStation(stationId)
            .subscribe { onStationLoaded(it) }.addTo(disposables)

        // TODO training sets laden für genau dieses training (tageweise?) oder 5h minus / plus?
    }

    private fun onStationLoaded(station: Station) {
        this.station = station
    }

    private fun saveStation(station: Station?) {
        station?.let {
            trainingRepository.saveStation(it).subscribe()
        }
    }

    private fun saveTrainingSet(set: TrainingSet) {
        trainingRepository.saveSet(set).subscribe()
    }

    fun onWeightMinus() {
        station = station?.let { it.copy(actualWeight = it.actualWeight - 1) }
        saveStation(station)
    }

    fun onWeightPlus() {
        station = station?.let { it.copy(actualWeight = it.actualWeight + 1) }
        saveStation(station)
    }

    fun onRepeatsMinus() {
        station = station?.let { it.copy(actualRepeats = it.actualRepeats - 1) }
        saveStation(station)
    }

    fun onRepeatsPlus() {
        station = station?.let { it.copy(actualRepeats = it.actualRepeats + 1) }
        saveStation(station)
    }

    fun onFinishSet() {
        station?.apply {
            val set = TrainingSet(
                id = 0,
                stationId = id,
                date = System.currentTimeMillis(),
                repeats = actualRepeats, weight = actualWeight, weightUnit = actualWeightUnit
            )

            saveTrainingSet(set)
        }
    }
}
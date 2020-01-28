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
    private val trainingRepository: TrainingRepository
) : ObservableViewModel(app) {

    private val disposables = CompositeDisposable()

    private val trainingSets = mutableListOf<TrainingSet>()

    private var station: Station? = null
        private set(value) {
            if (value != field) {
                field = value
                notifyPropertyChanged(BR.trainingName)
                notifyPropertyChanged(BR.weight)
                notifyPropertyChanged(BR.repeats)
                notifyPropertyChanged(BR.labelFinishSet)
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
        get() = getApplication<Application>().getString(R.string.finish_set, trainingSets.size + 1)

    fun init(stationId: Long?) {
        if (stationId == null) {
            trainingRepository.getFirstStation().subscribe { onStationLoaded(it) }
                .addTo(disposables)
        } else {
            trainingRepository.getStation(stationId).subscribe { onStationLoaded(it) }
                .addTo(disposables)
        }
    }

    private fun onStationLoaded(station: Station) {
        this.station = station
        trainingRepository.getTrainingSetsForActualTraining(station.id)
            .subscribe {
                onTrainingSetLoaded(it)
            }.addTo(disposables)
    }

    private fun onTrainingSetLoaded(trainingSets: List<TrainingSet>) {
        this.trainingSets.clear()
        this.trainingSets.addAll(trainingSets)
        notifyPropertyChanged(BR.labelFinishSet)
    }

    private fun updateStation(station: Station?) {
        station?.let {
            trainingRepository.updateStation(it).subscribe()
        }
    }

    private fun saveTrainingSet(set: TrainingSet) {
        trainingRepository.saveSet(set).subscribe()
    }

    fun onWeightMinus() {
        station =
            station?.let { station ->
                station.copy(
                    actualWeight = station.actualWeight.decIfGreater(
                        0f
                    )
                )
            }
        updateStation(station)
    }

    fun onWeightPlus() {
        station = station?.let { it.copy(actualWeight = it.actualWeight + 1) }
        updateStation(station)
    }

    fun onRepeatsMinus() {
        station = station?.let { it.copy(actualRepeats = it.actualRepeats.decIfGreater(1)) }
        updateStation(station)
    }

    fun onRepeatsPlus() {
        station = station?.let { it.copy(actualRepeats = it.actualRepeats + 1) }
        updateStation(station)
    }

    fun onFinishSet() {
        station?.apply {
            val set = TrainingSet(
                traininSetId = 0,
                stationId = id,
                date = System.currentTimeMillis(),
                repeats = actualRepeats, weight = actualWeight, weightUnit = actualWeightUnit
            )

            saveTrainingSet(set)
        }
    }

    private fun Float.decIfGreater(value: Float): Float = if (this > value) {
        this.dec()
    } else {
        this
    }

    private fun Int.decIfGreater(value: Int): Int = if (this > value) {
        this.dec()
    } else {
        this
    }
}

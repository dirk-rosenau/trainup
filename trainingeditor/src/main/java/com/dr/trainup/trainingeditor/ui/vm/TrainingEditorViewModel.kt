package com.dr.trainup.trainingeditor.ui.vm

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.dr.data.entities.Station
import com.dr.data.entities.TrainingSet
import com.dr.data.repositories.TrainingRepository
import de.trainup.common.ObservableViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TrainingEditorViewModel @Inject constructor(
    app: Application,
    val trainingRepository: TrainingRepository
) : ObservableViewModel(app) {

    private var station: Station? = null
    private var trainingSet: TrainingSet? = null

    @get:Bindable
    var stationName = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.stationName)
        }

    @get:Bindable
    var seatPosition: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.seatPosition)
        }

    @get:Bindable
    var repeats: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.repeats)
        }

    @get:Bindable
    var weight: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weight)
        }

    @get:Bindable
    var weightUnit: String = "kg"
        set(value) {
            field = value
            notifyPropertyChanged(BR.weightUnit)
        }

    private val disposables = CompositeDisposable()

    // TODO view model must get an single exercise, adds stuff to it

    // TODO hide in getter
    val addButtonLiveData = MutableLiveData<Int>()

    fun init(stationId: Long) {
        val disp = trainingRepository.getStation(stationId)
            .subscribe { onStationLoaded(it) }
        val disp2 =
            trainingRepository.getInitialTrainingSetForStation(stationId)
                .subscribe { onTrainingSetLoaded(it) }
        disposables.add(disp)
        disposables.add(disp2)
    }

    private fun onStationLoaded(station: Station) {
        this.station = station
        stationName = station.name
        seatPosition = station.seatPosition
    }

    private fun onTrainingSetLoaded(trainingSet: TrainingSet) {
        this.trainingSet = trainingSet
        repeats = trainingSet.repeats.toString()
        weight = trainingSet.weight.toString()
        weightUnit = trainingSet.weightUnit
    }

    fun saveStationData() {

        val stationID = station?.id ?: 0
        val station = // muss id ersetzt werden?
            Station(
                stationID,
                stationName,
                seatPosition
            )

        val trainingSetId = trainingSet?.id ?: 0
        val date = trainingSet?.date ?: System.currentTimeMillis()

        val reps = repeats.toInt() // im diassemble anschauen warum das sonst nicht läuft
        val dispo = trainingRepository.saveStation(station).flatMap { stationId ->
            val set = TrainingSet(
                trainingSetId, stationId, date, weight.toInt(), weightUnit, reps
            )
            trainingRepository.saveSet(set)
        }.subscribe({}, { t: Throwable? -> Log.d("Errorrr", t?.localizedMessage) })
        disposables.add(dispo)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

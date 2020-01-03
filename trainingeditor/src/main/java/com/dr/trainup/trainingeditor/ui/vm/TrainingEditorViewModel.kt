package com.dr.trainup.trainingeditor.ui.vm

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.dr.data.entities.Station
import com.dr.data.repositories.TrainingRepository
import com.trainup.common.ObservableViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TrainingEditorViewModel @Inject constructor(
    app: Application,
    private val trainingRepository: TrainingRepository
) : ObservableViewModel(app) {

    private var station: Station? = null


    var stationSaved = MutableLiveData<Boolean>(false)

    @get:Bindable
    var stationName = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.stationName)
        }

    @get:Bindable
    var seatPosition: String? = ""
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

    fun init(stationId: Long) {
        trainingRepository.getStation(stationId)
            .subscribe { onStationLoaded(it) }.addTo(disposables)
    }

    private fun onStationLoaded(station: Station) {
        this.station = station
        stationName = station.name
        seatPosition = station.seatPosition
        repeats = station.actualRepeats.toString()
        weight = station.actualWeight.toString()
        weightUnit = station.actualWeightUnit
    }

    fun saveStationData() {
        if (textFieldsAreValid()) {
            val stationID = station?.id ?: 0
            val station =
                Station(
                    stationID,
                    stationName,
                    weight.toFloat(),
                    weightUnit,
                    repeats.toInt(),
                    seatPosition
                )

            trainingRepository.saveStation(station)
                .subscribe(
                    { stationSaved.value = true },
                    { t: Throwable? -> Log.d("Errorrr", t?.localizedMessage) })
                .addTo(disposables)
        }
    }

    private fun textFieldsAreValid(): Boolean = weight.isNotEmpty()
            && repeats.isNotEmpty()
            && stationName.isNotEmpty()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

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

    var stationName: String = ""
        @Bindable
        get() {
            return station?.name ?: field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.stationName)
        }

    var seatPosition: String = ""
        @Bindable
        get() {
            return station?.seatPosition ?: ""
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.seatPosition)
        }

    var repeats: String = ""
        @Bindable
        get() {
            return trainingSet?.repeats?.toString() ?: ""
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.repeats)
        }

    var weight: String = ""
        @Bindable
        get() {
            return trainingSet?.weight?.toString() ?: ""
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.weight)
        }
    var weightUnit: String = ""
        @Bindable
        get() {
            return trainingSet?.weightUnit ?: ""
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.weightUnit)
        }

    private val disposables = CompositeDisposable()

    // TODO view model must get an single exercise, adds stuff to it

    // TODO hide in getter
    val addButtonLiveData = MutableLiveData<Int>()

    fun init(stationId: Long) {
        val disp = trainingRepository.getStation(stationId).subscribe { station = it }
        val disp2 = trainingRepository.getInitialTrainingSetForStation(stationId)
            .subscribe { trainingSet = it }
        disposables.add(disp)
        disposables.add(disp2)
    }

    fun saveStationData() {
        val station = Station(
            0,
            stationName,
            seatPosition
        )
        val dispo = trainingRepository.saveStation(station).flatMap { stationId ->
            val set = TrainingSet(
                0, stationId, System.currentTimeMillis(), weight.toInt(), weightUnit, 0
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

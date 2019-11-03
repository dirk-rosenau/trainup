package com.dr.trainup.trainingview.vm

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

import com.dr.data.entities.Station
import com.dr.data.repositories.TrainingRepository

import com.trainup.common.ObservableViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TrainingViewVM @Inject constructor(
    app: Application,
    val trainingRepository: TrainingRepository
) : ObservableViewModel(app) {

    private val disposables = CompositeDisposable()

    private var station: Station? = null
        private set(value) {
            if (value != field) {
                field = value
                notifyPropertyChanged(BR.trainingName)
                notifyPropertyChanged(BR.weight)
                notifyPropertyChanged(BR.repeats)

            }
        }

    val trainingName: String?
        @Bindable get() = station?.name

    val weight: String?
        @Bindable get() = station?.actualWeight.toString()

    val repeats: String?
        @Bindable get() = station?.actualRepeats.toString()

    fun init(stationId: Long) {
        trainingRepository.getStation(stationId)
            .subscribe { onStationLoaded(it) }.addTo(disposables)
    }

    private fun onStationLoaded(station: Station) {
        this.station = station
    }
}
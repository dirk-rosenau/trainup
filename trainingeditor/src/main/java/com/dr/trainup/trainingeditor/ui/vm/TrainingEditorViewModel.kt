package com.dr.trainup.trainingeditor.ui.vm

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import de.trainup.common.ObservableViewModel
import javax.inject.Inject

class TrainingEditorViewModel @Inject constructor(app: Application) : ObservableViewModel(app) {

    @get:Bindable
    var stationName: String = "Bankdrücken"
        set(value) {
            field = value
            notifyPropertyChanged(BR.stationName)
        }

    @get:Bindable
    var seatPosition: String = "4"
        set(value) {
            field = value
//        notifyPropertyChanged(BR.)
        }
    @get:Bindable
    var repeats: String = "0"
        set(value) {
            field = value
//        notifyPropertyChanged(BR.)
        }
    @get:Bindable
    var weight: String = "0"
        set(value) {
            field = value
//        notifyPropertyChanged(BR.)
        }
    @get:Bindable
    var weightUnit: String = "kg"
        set(value) {
            field = value
//        notifyPropertyChanged(BR.)
        }

    // TODO view model must get an single exercise, adds stuff to it

    // TODO hide in getter
    val addButtonLiveData = MutableLiveData<Int>()

//    fun saveStationData() {
//        val station = Station(
//            0,
//
//    }

}

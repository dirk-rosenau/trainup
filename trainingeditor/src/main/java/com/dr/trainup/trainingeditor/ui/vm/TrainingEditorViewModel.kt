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
import javax.inject.Inject

class TrainingEditorViewModel @Inject constructor(
    app: Application,
    val trainingRepository: TrainingRepository
) : ObservableViewModel(app) {

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
            notifyPropertyChanged(BR.seatPosition)
        }
    @get:Bindable
    var repeats: String = "0"
        set(value) {
            field = value
            notifyPropertyChanged(BR.repeats)
        }
    @get:Bindable
    var weight: String = "0"
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

    // TODO view model must get an single exercise, adds stuff to it

    // TODO hide in getter
    val addButtonLiveData = MutableLiveData<Int>()

    fun saveStationData() {
        val station = Station(
            0,
            stationName,
            seatPosition
        )
        trainingRepository.saveStation(station).map { stationId ->
            val set = TrainingSet(
                0, stationId, System.currentTimeMillis(), weight.toInt(), weightUnit, 0
            )
            trainingRepository.saveSet(set)
        }.subscribe({}, { t: Throwable? -> Log.d("Errorrr", t?.localizedMessage) })
        // Attempt to invoke interface method 'androidx.sqlite.db.SupportSQLiteDatabase androidx.sqlite.db.SupportSQLiteOpenHelper.getWritableDatabase()' on a null object reference
    }

}

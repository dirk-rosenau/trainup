package com.dr.trainup.trainingeditor.ui.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dr.trainup.trainingeditor.ui.ParameterItem
import de.trainup.common.ObservableViewModel
import javax.inject.Inject

class TrainingEditorViewModel @Inject constructor(app: Application) : ObservableViewModel(app) {
    val parameterItems = mutableMapOf<String, ParameterItem>()

    /* @Bindable
     var name: String = "Hallo"
         get() {
             return "Hallo"
         }
         set(value) {
             field = value

         }*/
    private val MAX_PARAMETERS = 5
    // TODO view model must get an single exercise, adds stuff to it

    // TODO hide in getter
    val addButtonLiveData = MutableLiveData<Int>()

    fun saveStationData() {
        //    val station = Station(
        //    0,
    }

    // TODO get from db
    fun getSeatPosition(): String = "4"

    fun getRepeats(): String = "0"

    fun getWeight(): String = "0"

    fun getWeightUnit(): String = "kg"
}

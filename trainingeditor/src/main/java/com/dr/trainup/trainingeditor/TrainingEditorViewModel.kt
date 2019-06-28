package com.dr.trainup.trainingeditor

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TrainingEditorViewModel @Inject constructor() : ViewModel() {
    private val MAX_PARAMETERS = 5
    // TODO view model must get an single exercise, adds stuff to it

    // TODO hide in getter
    val addButtonLiveData = MutableLiveData<Int>()

    fun addParameter() {

        val v = addButtonLiveData.value ?: 1
        if (v <= MAX_PARAMETERS) {
            addButtonLiveData.value = v + 1
        }
        //val param = ExerciseParameter()

        Log.d("vm", "clicked")
    }
}

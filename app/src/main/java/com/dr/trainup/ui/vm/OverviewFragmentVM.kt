package com.dr.trainup.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dr.data.entities.Station
import com.dr.data.repositories.TrainingRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class OverviewFragmentVM @Inject constructor(private val trainingRepository: TrainingRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()


    private val _itemVMs = MutableLiveData<List<ExerciseOverviewItemVM>>()
    val itemVms: LiveData<List<ExerciseOverviewItemVM>> = _itemVMs

    private val _actionMode = MutableLiveData<Boolean>(false)

    val actionMode: LiveData<Boolean> = _actionMode

    var actionModeEnabled: Boolean
        get() = _actionMode.value == true
        set(value) {
            if (actionMode.value != value) {
                _actionMode.value = value
                notifyActionModeChange()
            }
        }

    private fun notifyActionModeChange() {
        itemVms.value?.forEach {

            //   it.notifyPropertyChanged(BR.actionModeEnabled)
        }
    }

    fun loadExercises() {
        trainingRepository.getStations().observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { onStationDataLoaded(it) },
                onError = { processError(it) }).addTo(compositeDisposable)
    }

    private fun onStationDataLoaded(it: List<Station>?) {
        val itemVMList = mutableListOf<ExerciseOverviewItemVM>()
        it?.forEach {
            itemVMList.add(ExerciseOverviewItemVM(it, ::onIntent, ::actionModeEnabled))
        }
        _itemVMs.value = itemVMList
    }

    private fun onIntent(intent: OverviewIntent) {
        // TODO handle intent action, e.g. with life data
        when (intent) {
            is RequestActionModeIntent -> actionModeEnabled = true
        }
    }


    private fun processError(it: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

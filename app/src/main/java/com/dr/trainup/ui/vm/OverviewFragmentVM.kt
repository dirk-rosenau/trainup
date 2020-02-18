package com.dr.trainup.ui.vm

import android.util.Log
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dr.data.entities.StationWithTrainingSet
import com.dr.data.repositories.TrainingRepository
import com.dr.trainup.ui.model.ExerciseOverviewItem
import com.trainup.common.util.Consumable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class OverviewFragmentVM @Inject constructor(private val trainingRepository: TrainingRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _itemSelected = MutableLiveData<Consumable<Long>>()
    val itemSelected: LiveData<Consumable<Long>> = _itemSelected

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
            it.notifyPropertyChanged(BR.actionModeEnabled)
        }
    }

    // TODO move this to repository
    private fun mapToOverviewItem(stationWithTime: StationWithTrainingSet): ExerciseOverviewItem {
        return ExerciseOverviewItem(
            stationWithTime.station.id,
            stationWithTime.station.name,
            false,
            stationWithTime.trainingSet?.date
        )
    }

    fun loadExercises() {
        trainingRepository.getStationsWithLatestEditedTrainingSet()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { onStationDataLoaded(it) },
                onError = { processError(it) }).addTo(compositeDisposable)
    }

    private fun onStationDataLoaded(list: List<StationWithTrainingSet>?) {
        val itemVMList = mutableListOf<ExerciseOverviewItemVM>()
        list?.map { mapToOverviewItem(it) }?.forEach { item ->
            itemVMList.add(
                ExerciseOverviewItemVM(
                    item,
                    ::onIntent,
                    ::actionModeEnabled
                )
            )
        }
        _itemVMs.value = itemVMList
    }

    private fun onIntent(intent: OverviewIntent) {
        // TODO use case?
        when (intent) {
            is RequestActionModeIntent -> actionModeEnabled = true
            is SelectItemIntent -> handleSelectItem(intent.id)
        }
    }

    private fun handleSelectItem(id: Long) {
        _itemSelected.value = Consumable(id)
    }

    fun deselectItems() {
        itemVms.value?.forEach {
            it.selected = false
        }
    }

    fun deleteSelectedItems() {
        _itemVMs.value?.filter { it.selected }?.map {
            trainingRepository.deleteStation(it.id).subscribe {
                loadExercises()
            }
        }
    }

    private fun processError(it: Throwable) {
        Log.d("OverviewFragment", it.localizedMessage)
    }

    fun getSelectedItems(): List<Long> {
        val resultList = mutableListOf<Long>()
        itemVms.value?.filter { it.selected }?.forEach {
            resultList.add(it.id)
        }
        return resultList
    }
}

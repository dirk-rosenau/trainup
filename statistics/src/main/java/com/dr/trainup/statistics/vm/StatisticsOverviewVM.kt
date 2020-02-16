package com.dr.trainup.statistics.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dr.data.entities.StationWithTime
import com.dr.data.repositories.TrainingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class StatisticsOverviewVM @Inject constructor(private val trainingRepository: TrainingRepository) :
    ViewModel() {

    private val _stationsWithTime = MutableLiveData<List<StationWithTime>>()
    val stationWithTime: LiveData<List<StationWithTime>> = _stationsWithTime

    init {
        loadStations()
    }

    private fun loadStations() {
        viewModelScope.launch(Dispatchers.IO) {
//            val stationsWithTrainingSets = trainingRepository.getStationsWithTrainingSets()
//            _stationsWithTime.value = stationsWithTrainingSets
        }
    }
}
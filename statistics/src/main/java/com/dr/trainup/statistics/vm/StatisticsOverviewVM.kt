package com.dr.trainup.statistics.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dr.trainup.statistics.usecase.GetStatisticsOverviewUseCase
import com.dr.trainup.statistics.ui.model.Groupable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class StatisticsOverviewVM @Inject constructor(private val getStatisticsUseCase: GetStatisticsOverviewUseCase) :
    ViewModel() {


    private val _stationsWithTime = MutableLiveData<List<Groupable>>()
    val stationWithTime: LiveData<List<Groupable>> = _stationsWithTime

    init {
        loadStations()
    }

    private fun loadStations() {
        viewModelScope.launch(Dispatchers.IO) {
            _stationsWithTime.postValue(getStatisticsUseCase.getStatisticItems())
        }
    }
}

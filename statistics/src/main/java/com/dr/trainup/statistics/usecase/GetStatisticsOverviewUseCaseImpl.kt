package com.dr.trainup.statistics.usecase

import com.dr.data.entities.StationWithTrainingSet
import com.dr.data.repositories.TrainingRepository
import com.dr.trainup.statistics.ui.model.DateGroupable
import com.dr.trainup.statistics.ui.model.Groupable
import com.dr.trainup.statistics.ui.model.StationGroupable
import com.dr.trainup.statistics.ui.model.items.DateItemData
import com.dr.trainup.statistics.ui.model.items.StationItemData
import com.trainup.common.extensions.convertToDayStartMillis
import com.trainup.common.extensions.convertToLocalDateString
import javax.inject.Inject

class GetStatisticsOverviewUseCaseImpl @Inject constructor(private val repository: TrainingRepository) :
    GetStatisticsOverviewUseCase {
    override suspend fun getStatisticItems(): List<Groupable> {
        val stationsWithTrainingSets = repository.getStationsWithTrainingSets()
        val dayMap = sortDataPerDay(stationsWithTrainingSets)
        // ergebnis:
        // Map
        // 12.01.2020
        // -- StationWithtrainingSet(1, 1)
        // -- StationWithtrainingSet(1, 2)
        // -- StationWithtrainingSet(1, 3)
        // -- StationWithtrainingSet(2, 1)
        val statisticViewItems = buildStatisticViewItems(dayMap)
        return statisticViewItems
    }

    // 1. schritt: stations mit trainingsets nach tagen sortieren
    private fun sortDataPerDay(stationWithTrainingSets: List<StationWithTrainingSet>): MutableMap<Long, MutableList<StationWithTrainingSet>> {
        val daymap = mutableMapOf<Long, MutableList<StationWithTrainingSet>>()
        stationWithTrainingSets.forEach {
            val dayStart = it.trainingSet?.date?.convertToDayStartMillis()
            if (dayStart != null) {
                val dataList = getOrCreateStationListInDayMap(daymap, dayStart)
                // keys: tage, values Station with training sets (list)
                dataList.add(it)
            }
        }
        return daymap
    }

    private fun getOrCreateStationListInDayMap(
        dayMap: MutableMap<Long, MutableList<StationWithTrainingSet>>,
        dayStart: Long
    ): MutableList<StationWithTrainingSet> {
        var dataList = dayMap[dayStart]
        if (dataList == null) {
            dataList = mutableListOf()
            dayMap[dayStart] = dataList
        }
        return dataList
    }

    private fun buildStatisticViewItems(map: Map<Long, List<StationWithTrainingSet>>): MutableList<Groupable> {
        val dateContainerList = mutableListOf<Groupable>()
        map.forEach { mapEntry ->
            val date = mapEntry.key
            val dateContainer = DateItemData(date.convertToLocalDateString())
            val stationChildren = mutableListOf<StationGroupable>()
            mapEntry.value.sortedByDescending { it.trainingSet?.weight }
                .distinctBy { it.station.id }
                .forEach {
                    stationChildren.add(
                        StationGroupable(
                            StationItemData(
                                it.station.id,
                                date,
                                it.station.name,
                                it.trainingSet?.weight ?: 0f,
                                it.trainingSet?.weightUnit ?: "",
                                it.trainingSet?.repeats ?: 0
                            )
                        )
                    )
                }
            dateContainerList.add(
                DateGroupable(
                    dateContainer,
                    stationChildren
                )
            )
        }
        return dateContainerList
    }
}

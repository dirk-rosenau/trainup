package com.dr.trainup.statistics.vm

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dr.trainup.statistics.R
import com.dr.trainup.statistics.ui.model.items.StationItemData
import com.trainup.common.util.StringResource
import com.trainup.common.util.toStrResource

class StationItemVM(
    private val stationItemData: StationItemData,
    private val onIntent: (StatisticsIntent) -> Unit
) : BaseObservable() {

    @get:Bindable
    val name: String
        get() = stationItemData.name

    val subtext: StringResource
        get() = R.string.stationInfo.toStrResource(
            stationItemData.amount,
            stationItemData.weight,
            stationItemData.weightUnit
        )

    fun onClick() {
        onIntent(SelectExerciseIntent(stationItemData.stationId, stationItemData.exerciseDate))
    }
}

package com.dr.trainup.ui.vm

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dr.data.entities.Station

class ExerciseOverviewItemVM(
    private val station: Station,
    private val onIntent: (OverviewIntent) -> Unit,
    private val actionModeDelegate: () -> Boolean
) : BaseObservable() {

    @get:Bindable
    val actionModeEnabled: Boolean
        get() = actionModeDelegate()

    @get:Bindable
    var selected: Boolean = false
        get() {
            return field
        }
        set(value: Boolean) {
            field = value
            onIntent(SelectionChangedIntent)
        }

    fun onItemClick() {
        onIntent(SelectItemIntent(station.id))
    }

    fun onItemLongClick(): Boolean = if (!actionModeEnabled) {
        onIntent(RequestActionModeIntent)
        selected = true
        true
    } else {
        false
    }

    val stationName = station.name
}

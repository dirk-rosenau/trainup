package com.dr.trainup.ui.vm

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.dr.trainup.ui.model.ExerciseOverviewItem

class ExerciseOverviewItemVM(
    private val item: ExerciseOverviewItem,
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
        set(value) {
            field = value
            onIntent(SelectionChangedIntent)
            notifyPropertyChanged(BR.selected)
        }

    fun onItemClick() {
        if (actionModeEnabled) {
            selected = !selected
        } else {
            onIntent(SelectItemIntent(item.id))
        }
    }

    fun onItemLongClick(): Boolean = if (!actionModeEnabled) {
        onIntent(RequestActionModeIntent)
        selected = true
        true
    } else {
        selected = !selected
        false
    }

    val stationName = item.name

    val id = item.id
}

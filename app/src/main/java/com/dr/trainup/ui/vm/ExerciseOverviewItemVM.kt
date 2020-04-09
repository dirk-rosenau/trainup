package com.dr.trainup.ui.vm

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.dr.trainup.R
import com.dr.trainup.ui.model.ExerciseOverviewItem
import com.trainup.common.extensions.isToday
import com.trainup.common.util.StringResource
import com.trainup.common.util.toStrResource
import java.text.SimpleDateFormat
import java.util.*

class ExerciseOverviewItemVM(
    private val item: ExerciseOverviewItem,
    private val onIntent: (OverviewIntent) -> Unit,
    private val actionModeDelegate: () -> Boolean
) : BaseObservable() {

    @get:Bindable
    val actionModeEnabled: Boolean
        get() = actionModeDelegate()


    @get:Bindable
    val lastExerciseColor: Int
        get() = resolveColorCode()


    private fun resolveColorCode(): Int {
        if (item.lastExercise.isToday()) {
            return R.color.colorGreen
        }
        return R.color.colorGrey
    }

    @get:Bindable
    val lastExercise
        get() = resolveDateString()

    private fun resolveDateString(): StringResource {
        if (item.lastExercise != null) {
            if (item.lastExercise.isToday()) {
                return R.string.today.toStrResource()
            } else {
                return R.string.rawString.toStrResource(
                    SimpleDateFormat(
                        "dd.MM.yyyy",
                        Locale.getDefault()
                    ).format(item.lastExercise)
                )
            }
        }
        return R.string.empty.toStrResource()
    }

    @get:Bindable
    var selected: Boolean = false
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

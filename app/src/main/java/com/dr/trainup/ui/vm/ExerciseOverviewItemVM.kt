package com.dr.trainup.ui.vm

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.dr.trainup.R
import com.dr.trainup.ui.model.ExerciseOverviewItem
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
        if (isToday(item.lastExercise)) {
            return R.color.colorGreen
        }
        return R.color.colorAccent
    }

    @get:Bindable
    val lastExercise
        get() = resolveDateString()

    private fun resolveDateString(): StringResource {
        if (item.lastExercise != null) {
            if (isToday(item.lastExercise)) {
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

    private fun isToday(timeInMillis: Long?): Boolean {
        if (timeInMillis != null) {
            val calendarExercise = getCalendarWithNewDay(timeInMillis)
            val calendarToday = getCalendarWithNewDay(System.currentTimeMillis())
            return calendarToday.timeInMillis == calendarExercise.timeInMillis
        } else return false
    }

    private fun getCalendarWithNewDay(timeInMillis: Long): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
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

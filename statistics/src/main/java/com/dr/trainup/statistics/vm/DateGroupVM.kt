package com.dr.trainup.statistics.vm

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dr.trainup.statistics.ui.model.items.DateItemData
import com.trainup.common.util.StringResource

class DateGroupVM(private val dateItemData: DateItemData) : BaseObservable() {

    @get:Bindable
    val date: StringResource
        get() = dateItemData.date
}

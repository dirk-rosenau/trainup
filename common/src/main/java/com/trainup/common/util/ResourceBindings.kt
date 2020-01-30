package com.trainup.common.util

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("app:text")
fun setText(view: TextView, stringResource: StringResource?) {
    if (stringResource != null) {
        view.text = stringResource.resolve(view.context)
    } else {
        view.text = null
    }
}

@BindingAdapter("app:textColor")
fun setTextColor(view: TextView, @ColorRes color: Int) {
    view.setTextColor(ContextCompat.getColor(view.context, color))
}



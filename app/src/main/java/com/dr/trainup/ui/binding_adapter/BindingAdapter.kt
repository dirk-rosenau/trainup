package com.dr.trainup.ui.binding_adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visibleOrGone")
fun setVisibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

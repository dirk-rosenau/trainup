package com.trainup.common.util

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes
import com.trainup.common.R


data class StringResource(
    @StringRes val stringRes: Int = R.string.empty,
    val args: List<Any?> = emptyList()
) {
    @SuppressLint("StringFormatInvalid")
    fun resolve(context: Context): String = context.getString(stringRes, *args.toTypedArray())

    companion object {
        val EMPTY_STRING = StringResource()
    }
}

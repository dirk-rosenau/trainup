package com.dr.trainup.trainingeditor.ui

data class ParameterItem(
    val id: String,
    val name: String,
    val value: Int,
    val unit: String
) {
    fun getValueAsString() = value.toString()
}
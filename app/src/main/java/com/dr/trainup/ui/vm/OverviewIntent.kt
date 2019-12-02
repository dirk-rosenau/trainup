package com.dr.trainup.ui.vm

sealed class OverviewIntent
object RequestActionModeIntent : OverviewIntent()
object SelectionChangedIntent : OverviewIntent()
data class SelectItemIntent(val id: Long) : OverviewIntent()
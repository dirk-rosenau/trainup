package com.dr.trainup.statistics.usecase

import com.dr.trainup.statistics.ui.model.Groupable

interface GetStatisticsOverviewUseCase {
    suspend fun getStatisticItems(): List<Groupable>
}

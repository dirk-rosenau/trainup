package com.dr.trainup.statistics.di

import com.dr.trainup.statistics.usecase.GetStatisticsOverviewUseCase
import com.dr.trainup.statistics.usecase.GetStatisticsOverviewUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class StatisticModule {
    @Binds
    abstract fun bindGetStatisticUseCase(useCase: GetStatisticsOverviewUseCaseImpl): GetStatisticsOverviewUseCase
}

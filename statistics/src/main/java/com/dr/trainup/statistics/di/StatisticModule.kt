package com.dr.trainup.statistics.di

import com.dr.trainup.statistics.usecase.GetStatisticsUseCase
import com.dr.trainup.statistics.usecase.GetStatisticsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class StatisticModule {
    @Binds
    abstract fun bindGetStatisticUseCase(useCase: GetStatisticsUseCaseImpl): GetStatisticsUseCase
}
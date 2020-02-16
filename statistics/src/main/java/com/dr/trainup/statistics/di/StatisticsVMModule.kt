package com.dr.trainup.statistics.di

import androidx.lifecycle.ViewModel
import com.dr.trainup.statistics.vm.StatisticsOverviewVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StatisticsVMModule {
    @Binds
    @IntoMap
    @com.trainup.common.di.ViewModelKey(StatisticsOverviewVM::class)
    abstract fun statisticsVM(viewModel: StatisticsOverviewVM): ViewModel
}

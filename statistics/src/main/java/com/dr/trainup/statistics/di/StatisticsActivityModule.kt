package com.dr.trainup.statistics.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trainup.common.ViewModelFactory
import com.trainup.common.di.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(includes = [StatisticsFragmentModule::class, StatisticsVMModule::class])
object StatisticsActivityModule {
    @JvmStatic
    @ActivityScope
    @Provides
    fun provideViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(creators)
    }
}
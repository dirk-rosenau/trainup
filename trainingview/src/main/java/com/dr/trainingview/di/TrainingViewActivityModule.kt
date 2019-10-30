package com.dr.trainingview.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trainup.common.ViewModelFactory
import com.trainup.common.di.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(includes = [TrainingViewFragmentModule::class, TrainingViewVMModule::class])
object TrainingViewActivityModule {
    @JvmStatic
    @ActivityScope
    @Provides
    fun provideViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(creators)
    }
}
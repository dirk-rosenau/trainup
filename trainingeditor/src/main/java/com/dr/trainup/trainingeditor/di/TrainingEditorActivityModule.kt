package com.dr.trainup.trainingeditor.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.trainup.common.ViewModelFactory
import de.trainup.common.di.ActivityScope
import javax.inject.Provider

@Module(includes = [TrainingEditorFragmentModule::class, TrainingEditorViewModelModule::class])
object TrainingEditorActivityModule {
    @JvmStatic
    @ActivityScope
    @Provides
    fun provideViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(creators)
    }
}
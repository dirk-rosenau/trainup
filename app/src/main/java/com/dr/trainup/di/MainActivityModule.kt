package com.dr.trainup.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dr.trainup.trainingview.di.TrainingViewFragmentModule
import com.dr.trainup.trainingview.di.TrainingViewVMModule
import com.trainup.common.ViewModelFactory
import com.trainup.common.di.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(includes = [FragmentModule::class, ViewModelModule::class, TrainingViewFragmentModule::class, TrainingViewVMModule::class])
object MainActivityModule {
    @JvmStatic
    @Provides
    @ActivityScope
    fun provideViewModelFactory(
        creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory {
        return ViewModelFactory(creators)
    }
}

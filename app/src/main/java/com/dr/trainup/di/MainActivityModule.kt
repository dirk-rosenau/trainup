package com.dr.trainup.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dr.trainup.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(includes = [FragmentModule::class, ViewModelModule::class])
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

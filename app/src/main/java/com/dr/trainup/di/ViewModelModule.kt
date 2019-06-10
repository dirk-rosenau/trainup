package com.dr.trainup.di

import androidx.lifecycle.ViewModel
import com.dr.trainup.OverviewFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @de.trainup.common.di.ViewModelKey(OverviewFragmentVM::class)
    abstract fun overviewFragmentViewModel(viewModel: OverviewFragmentVM): ViewModel
}

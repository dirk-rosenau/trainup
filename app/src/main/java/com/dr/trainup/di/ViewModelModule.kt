package com.dr.trainup.di

import androidx.lifecycle.ViewModel
import com.dr.trainup.ui.vm.OverviewFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @com.trainup.common.di.ViewModelKey(OverviewFragmentVM::class)
    abstract fun overviewFragmentViewModel(viewModel: OverviewFragmentVM): ViewModel
}

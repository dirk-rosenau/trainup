package com.dr.trainup.trainingview.di

import androidx.lifecycle.ViewModel
import com.dr.trainup.trainingview.vm.TrainingViewVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TrainingViewVMModule {

    @Binds
    @IntoMap
    @com.trainup.common.di.ViewModelKey(TrainingViewVM::class)
    abstract fun trainingViewFragmentViewModel(viewModel: TrainingViewVM): ViewModel
}

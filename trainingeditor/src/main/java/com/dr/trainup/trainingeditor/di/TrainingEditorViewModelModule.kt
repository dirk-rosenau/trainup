package com.dr.trainup.trainingeditor.di

import androidx.lifecycle.ViewModel
import com.dr.trainup.trainingeditor.ui.vm.TrainingEditorViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TrainingEditorViewModelModule {

    @Binds
    @IntoMap
    @de.trainup.common.di.ViewModelKey(TrainingEditorViewModel::class)
    abstract fun trainingEditorFragmentViewModel(viewModel: TrainingEditorViewModel): ViewModel
}

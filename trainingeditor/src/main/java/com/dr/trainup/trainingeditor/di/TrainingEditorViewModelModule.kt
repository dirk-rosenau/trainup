package com.dr.trainup.trainingeditor.di

import androidx.lifecycle.ViewModel
import com.dr.trainup.trainingeditor.TrainingEditorViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @de.trainup.common.di.ViewModelKey(TrainingEditorViewModel::class)
    abstract fun overviewFragmentViewModel(viewModel: TrainingEditorViewModel): ViewModel
}

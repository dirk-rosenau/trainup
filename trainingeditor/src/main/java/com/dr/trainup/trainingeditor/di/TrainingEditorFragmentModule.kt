package com.dr.trainup.trainingeditor.di

import com.dr.trainup.trainingeditor.ui.TrainingEditorFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.trainup.common.di.FragmentScope

@Module
abstract class TrainingEditorFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeTrainingEditorFragment(): TrainingEditorFragment
}
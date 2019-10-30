package com.dr.trainingview.di

import com.dr.trainingview.ui.TrainingViewFragment
import com.trainup.common.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TrainingViewFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeTrainingViewFragment(): TrainingViewFragment
}
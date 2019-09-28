package com.dr.trainup.di

import com.dr.trainup.MainActivity
import com.dr.trainup.trainingeditor.di.TrainingEditorActivityModule
import com.dr.trainup.trainingeditor.ui.TrainingEditActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.trainup.common.di.ActivityScope

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [TrainingEditorActivityModule::class])
    abstract fun contributeTrainingEditActivity(): TrainingEditActivity
}

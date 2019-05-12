package com.dr.trainup.di

import com.dr.trainup.OverviewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun overviewFragmentInjector(): OverviewFragment
}

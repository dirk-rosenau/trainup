package com.dr.trainup.di

import com.dr.trainup.ui.fragments.OverviewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @de.trainup.common.di.FragmentScope
    @ContributesAndroidInjector()
    abstract fun overviewFragmentInjector(): OverviewFragment
}

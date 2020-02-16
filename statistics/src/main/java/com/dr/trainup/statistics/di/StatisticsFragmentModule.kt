package com.dr.trainup.statistics.di

import com.dr.trainup.statistics.ui.StatisticsFragment
import com.trainup.common.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StatisticsFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeStatisticsFragment(): StatisticsFragment
}
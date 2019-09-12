package com.dr.trainup.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

//import dagger.Module
//import dagger.Provides
//
//@Module
//object AppModule {
//    @JvmStatic
//    @Provides
//    fun provideTrainingRepository
//}

@Module
interface AppBindsModule {

    @Binds
    fun provideApplication(application: Application): Application
}

package com.dr.data.di

import android.content.Context
import androidx.room.Room
import com.dr.data.AppDatabase
import com.dr.data.repositories.TrainingRepository
import com.dr.data.repositories.TrainingRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideTrainingRepository(database: AppDatabase): TrainingRepository =
        TrainingRepositoryImpl(database)

    @JvmStatic
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "trainup-database"
    ).build()

}

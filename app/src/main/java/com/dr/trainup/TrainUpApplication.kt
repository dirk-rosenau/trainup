package com.dr.trainup

import android.app.Activity
import android.app.Application
import androidx.room.Room
import com.dr.data.AppDatabase
import com.dr.data.migrations.MIGRATION_1_2
import com.dr.trainup.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TrainUpApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)

        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "trainup-database")
            .addMigrations(MIGRATION_1_2).build()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}


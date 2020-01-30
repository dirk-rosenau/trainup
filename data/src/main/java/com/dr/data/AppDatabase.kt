package com.dr.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dr.data.dao.StationDao
import com.dr.data.dao.TrainingsSetDao
import com.dr.data.entities.Station
import com.dr.data.entities.TrainingSet

@Database(entities = [Station::class, TrainingSet::class], version = 2)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun stationDao(): StationDao

    abstract fun trainingSetDao(): TrainingsSetDao

}

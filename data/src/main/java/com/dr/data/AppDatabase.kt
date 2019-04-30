package com.dr.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dr.data.dao.ExeciseDao
import com.dr.data.entities.Exercise

@Database(entities = [Exercise::class], version = 1)
//@Database(entities = [TrainingDay::class, TrainingSet::class, Exercise::class, UnitProperty::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun excerciseDao(): ExeciseDao

}

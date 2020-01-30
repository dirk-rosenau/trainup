package com.dr.data.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("BEGIN TRANSACTION")
        database.execSQL("CREATE TABLE training_sets_tmp(trainingSetId INTEGER PRIMARY KEY, stationId INTEGER, date INTEGER, weight REAL, weightUnit TEXT, repeats INTEGER)")
        database.execSQL("INSERT INTO training_sets_tmp(stationId, date, weight, weightUnit, repeats) SELECT stationId, date, weightUnit, repeats FROM training_sets")
        //database.execSQL("DROP TABLE training_sets")
        database.execSQL("ALTER TABLE training_sets_tmp RENAME TO training_sets")
        database.execSQL("COMMIT")
    }
}

package com.dr.data.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("BEGIN TRANSACTION")
        database.execSQL("CREATE TABLE IF NOT EXISTS `training_sets_tmp` (`trainingSetId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `stationId` INTEGER NOT NULL, `date` INTEGER NOT NULL, `weight` REAL NOT NULL, `weightUnit` TEXT NOT NULL, `repeats` INTEGER NOT NULL, FOREIGN KEY(`stationId`) REFERENCES `stations`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        database.execSQL("INSERT INTO training_sets_tmp(stationId, date, weight, weightUnit, repeats) SELECT stationId, date, weight, weightUnit, repeats FROM training_sets")
        database.execSQL("DROP TABLE training_sets")
        database.execSQL("ALTER TABLE training_sets_tmp RENAME TO training_sets")

        database.execSQL("CREATE TABLE IF NOT EXISTS `stations_tmp` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `actualWeight` REAL NOT NULL, `actualWeightUnit` TEXT NOT NULL, `actualRepeats` INTEGER NOT NULL, `seatPosition` TEXT)");
        database.execSQL("INSERT INTO stations_tmp(name, actualWeight, actualWeightUnit, actualRepeats, seatPosition) SELECT name, actualWeight, actualWeightUnit, actualRepeats, seatPosition FROM stations")
        database.execSQL("DROP TABLE stations")
        database.execSQL("ALTER TABLE stations_tmp RENAME TO stations")
        database.execSQL("COMMIT")
    }
}

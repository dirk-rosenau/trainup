package com.dr.data

import androidx.room.TypeConverter
import java.util.*

object DateTypeConverter {

    @TypeConverter
    fun toDate(value: Long?): Date? =
        if (value == null) null else Date(value)


    @TypeConverter
    fun toLong(value: Date?): Long? = value?.time

}

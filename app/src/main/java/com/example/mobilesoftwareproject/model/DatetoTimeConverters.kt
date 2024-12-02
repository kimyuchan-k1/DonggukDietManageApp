package com.example.mobilesoftwareproject.model

import androidx.room.TypeConverter
import java.util.Date


class DatetoTimeConverters {

    //시간을 날짜로
    @TypeConverter
    fun fromTimeStamp(value: Long?): Date? {
        return value?.let {Date(it)}
    }


    // 날짜를 시간으로
    @TypeConverter
    fun dateToTimeStamp(value: Date?): Long? {
        return value?.time
    }
}
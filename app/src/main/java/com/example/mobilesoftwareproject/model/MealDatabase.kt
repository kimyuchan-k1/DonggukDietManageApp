package com.example.mobilesoftwareproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//
@Database(entities = [Meal::class], version = 1)
@TypeConverters(DatetoTimeConverters::class)
abstract class MealDatabase : RoomDatabase() {
    abstract fun MealDao() : MealDao

    // 데이터베이스 인스턴스 관리
    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null

        fun getDatabase(context: Context): MealDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

        const val DATABASE_NAME = "meals_db"
    }
}
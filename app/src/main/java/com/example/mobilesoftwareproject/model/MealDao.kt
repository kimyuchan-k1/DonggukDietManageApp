package com.example.mobilesoftwareproject.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.Date

@Dao
interface MealDao {

    @Insert()
    suspend fun insertMeal(meal: Meal)

    @Update
    suspend fun updateMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    // 모든 음식 조회
    @Query("SELECT * FROM meals ORDER BY  date DESC")
    suspend fun getAllMeals(): List<Meal>

    // id 로 음식 조회
    @Query("SELECT * FROM meals WHERE id = :mealId")
    suspend fun getMealsById(mealId: Int): Meal?

    // 날짜 범위내 음식 조회
    @Query("SELECT * FROM meals WHERE date >= :startDate AND date <= :endDate")
    suspend fun getMealsByDateRange(startDate: Date, endDate: Date): List<Meal>



}
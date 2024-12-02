package com.example.mobilesoftwareproject.repository

import com.example.mobilesoftwareproject.model.Meal
import com.example.mobilesoftwareproject.model.MealDao
import java.util.Date

class MealRepository(private val mealDao: MealDao) {

    suspend fun insertMeal(meal: Meal){
        mealDao.insertMeal(meal)
    }

    suspend fun updateMeal(meal: Meal){
        mealDao.updateMeal(meal)
    }

    suspend fun deleteMeal(meal: Meal){
        mealDao.deleteMeal(meal)


    }

    suspend fun getAllMeals(): List<Meal> {
        return mealDao.getAllMeals()
    }

    suspend fun getMealsById(mealId: Int): Meal? {
        return mealDao.getMealsById(mealId)
    }

    suspend fun getMealsByDateRange(startDate: Date, endDate: Date): List<Meal> {
        return mealDao.getMealsByDateRange(startDate, endDate)

    }
}
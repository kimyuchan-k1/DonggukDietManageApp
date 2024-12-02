package com.example.mobilesoftwareproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesoftwareproject.model.Meal
import com.example.mobilesoftwareproject.repository.MealRepository
import kotlinx.coroutines.launch
import java.io.File
import java.util.Date

class MealViewModel(private val repository: MealRepository) : ViewModel() {

    private val _allMeals = MutableLiveData<List<Meal>>()
    val allMeals: LiveData<List<Meal>> = _allMeals

    // 음식 저장
    fun insertMeal(meal: Meal) {
        viewModelScope.launch {
            // 음식 저장
            repository.insertMeal(meal)
            //모든 음식 로드
            loadAllMeals()
        }
    }


    // 초기화
    fun loadAllMeals(){
        viewModelScope.launch {
            _allMeals.value = repository.getAllMeals()
        }
    }

    // 해당 음식 가져오기
    fun getById(mealId: Int, onResult : (Meal?) -> Unit) {
        viewModelScope.launch {
            // meal 가져오기
            val meal = repository.getMealsById(mealId)
            // 해당 음식에 대한 로직 실행
            onResult(meal)

        }
    }

    // 날짜기준 음식 가져오기
    suspend fun getMealsByDateRange(startOfMonth: Date, endOfMonth: Date): List<Meal> {
        return repository.getMealsByDateRange(startOfMonth, endOfMonth)
    }

    // 추가함수

    fun deletemeal(meal: Meal) {
        viewModelScope.launch {
            repository.deleteMeal(meal)
            loadAllMeals()


        }

        meal.PhotoUri?.let {
            val file = File(it)
            if (file.exists()) {
                file.delete()
            }
        }

        
    }

}
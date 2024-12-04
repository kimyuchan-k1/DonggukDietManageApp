

package com.example.mobilesoftwareproject.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilesoftwareproject.viewmodel.MealViewModel
import java.util.Calendar
import java.util.Date

@Composable
fun AnalysisScreen(mealViewModel: MealViewModel) {
    val analysisData by remember { mutableStateOf(MealAnalysisData()) }

    LaunchedEffect(Unit) {
        val currentMonthMeals = mealViewModel.getMealsByDateRange(
            getStartOfMonth(),
            getEndOfMonth()
        )
        val totalCalories = currentMonthMeals.sumOf { it.calories ?: 0 }
        val costByMealType = currentMonthMeals.groupBy { it.mealType }.mapValues { entry ->
            entry.value.sumOf { it.price }
        }

        analysisData.totalCalories = totalCalories
        analysisData.costByMealType = costByMealType
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "이번 달 총 칼로리 섭취량: ${analysisData.totalCalories} kcal",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "식사 종류별 비용",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        analysisData.costByMealType.forEach { (mealType, cost) ->
            Text(
                text = "$mealType: $cost 원",
                fontSize = 16.sp
            )
        }
    }
}

// 분석 데이터 클래스
data class MealAnalysisData(
    var totalCalories: Int = 0,
    var costByMealType: Map<String, Int> = emptyMap(),
    var totalCost: Int = 0,
    var percentageByMealType: Map<String, Float> = emptyMap()
)



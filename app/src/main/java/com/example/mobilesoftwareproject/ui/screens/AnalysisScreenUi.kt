package com.example.mobilesoftwareproject.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mobilesoftwareproject.model.Meal
import com.example.mobilesoftwareproject.viewmodel.MealViewModel
import java.util.Calendar
import java.util.Date
import kotlin.math.round


//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CaloriesScreen(mealViewModel: MealViewModel) {
    // int 와 map 으로 구성된 analysisData
    val analysisData by remember { mutableStateOf(MealAnalysisData()) }
    var sections by remember { mutableStateOf(listOf<Float>()) }

    LaunchedEffect(Unit) {
        val currentMonthMeals = mealViewModel.getMealsByDateRange(
            getRecentMonth(),
            getTodayOfMonth()
        )
        val totalCalories = currentMonthMeals.sumOf { it.calories ?: 0 }
        val costByMealType = currentMonthMeals.groupBy { it.mealType }.mapValues { entry ->
            entry.value.sumOf { it.price }
        }
        val totalCost = costByMealType.values.sum()
        val percentageByMealType = costByMealType.mapValues { (_, cost) ->
            if (totalCost > 0) (cost.toDouble() / totalCost * 100).toFloat() else 0f
        }

        // 항상 일정한 순서로 데이터를 정렬
        val orderedMealTypes = listOf("조식", "중식", "석식", "간식/음료")
        val calculatedSections = orderedMealTypes.map { mealType ->
            percentageByMealType[mealType] ?: 0f
        }

        analysisData.totalCalories = totalCalories
        analysisData.totalCost = totalCost
        analysisData.costByMealType = costByMealType
        analysisData.percentageByMealType = percentageByMealType
        sections = calculatedSections
    }


    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        TopBar2()
        // 표와 차트 카드
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Text(
                text ="월간 칼로리 및 비용 분석",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                // 도넛 차트
                DonutChart(
                    sections = sections,
                    colors = listOf(
                        Color(0xFF9575CD),
                        Color(0xFFFFB74D),
                        Color(0xFFFF8A65),
                        Color(0xFF64B5F6)
                    ),
                    centerTextSmall = "전체 비용",
                    centerTextLarge = "${analysisData.totalCost}",
                    strokeWidth = 90f
                )
                typeCard(modifier = Modifier)

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                // 도넛 차트 로직


                BreakdownTable(
                    headers = listOf("식사", "비용", "퍼센트"),
                    rows = listOf(
                        listOf("조식", "${analysisData.costByMealType["조식"] ?: 0}", "${String.format("%.1f", sections.getOrNull(0) ?: 0f)}%"),
                        listOf("중식", "${analysisData.costByMealType["중식"] ?: 0}", "${String.format("%.1f", sections.getOrNull(1) ?: 0f)}%"),
                        listOf("석식", "${analysisData.costByMealType["석식"] ?: 0}", "${String.format("%.1f", sections.getOrNull(2) ?: 0f)}%"),
                        listOf("간식/음료", "${analysisData.costByMealType["간식/음료"] ?: 0}", "${String.format("%.1f", sections.getOrNull(3) ?: 0f)}%")
                    )
                )
            }
        }

        // Total 섹션을 흰색 카드로 묶기
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp), // 모서리를 둥글게
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // 그림자 효과
            // 하얀색으로 변경
            ,colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp) // 내부 패딩 추가
            ) {
                // Total 요약
                SummarySection(totalCalories = "${analysisData.totalCalories}", totalCost = "${analysisData.totalCost}")
            }
        }
    }
}


@Composable
fun DonutChart(
    sections: List<Float>,
    colors: List<Color>,
    centerTextSmall: String, // 작은 텍스트
    centerTextLarge: String, // 큰 텍스트
    strokeWidth: Float = 40f
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp), // 위아래 여백을 통합
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            var startAngle = 0f
            sections.forEachIndexed { index, section ->
                val sweepAngle = (section / 100) * 360
                drawArc(
                    color = colors.getOrElse(index) { Color.Gray },
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth)
                )
                startAngle += sweepAngle
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 작은 텍스트
            Text(
                text = centerTextSmall,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray // 원하는 색상
            )
            // 큰 텍스트
            Text(
                text = centerTextLarge,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black // 원하는 색상
            )
        }
    }
}
@Composable
fun BreakdownTable(headers: List<String>, rows: List<List<String>>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 17.dp), // 헤더와 데이터 행 사이 간격 추가
            horizontalArrangement = Arrangement.SpaceBetween) {
            headers.forEach { header ->
                Text(text = header, fontWeight = FontWeight.Bold, fontSize = 19.sp, modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        rows.forEach { row ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp), // 각 행 위아래 간격 추가
                horizontalArrangement = Arrangement.SpaceBetween) {
                row.forEach { cell ->
                    Text(text = cell, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun SummarySection(totalCalories: String, totalCost: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "월간 총 칼로리: $totalCalories",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier =Modifier.height(2.dp))

        Text(
            text = "월간 총 비용: $totalCost",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun TopBar2() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {

        Text(
            text = "마이 페이지",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),

        )

    }
}



//// 시작 날짜
//fun getStartOfMonth(): Date {
//    val calendar = Calendar.getInstance()
//    calendar.set(Calendar.DAY_OF_MONTH, 1)
//    return calendar.time
//}

// 오늘 날짜 구하기
fun getTodayOfMonth() : Date {
    val calendar = Calendar.getInstance()
    println(calendar)
    Log.d("오늘 날짜", calendar.time.toString())
    return calendar.time
}

// 오늘로 부터 30일전 즉, 한 달 전 날짜
fun getRecentMonth() : Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_MONTH, -30)
    Log.d("한달 전 날짜",calendar.time.toString())
    return calendar.time
}

/*

fun getEndOfMonth(): Date {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
    return calendar.time
}
*/


@Composable
fun typeCard(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column( // 수직으로 정렬
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center, // 전체 중앙 정렬
            horizontalAlignment = Alignment.CenterHorizontally // 가로 중앙 정렬
        ) {
// 첫 번째 박스
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(20.dp)
                        .background(Color(0xFF9575CD))
                )
                Text(
                    text = "조식",
                    modifier = Modifier.padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.width(8.dp)) // 박스 사이 간격

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .height(10.dp)
                            .width(20.dp)
                            .background(Color(0xFFFFB74D)) // 빨간색 박스
                    )
                    Text(
                        text = "중식",

                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }



            Spacer(modifier = Modifier.height(1.dp)) // 박스 사이 간격

            // 세 번째 박스
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(20.dp)
                        .background(Color(0xFFFF8A65)) // 빨간색 박스
                )
                Text(
                    text = "석식",


                    modifier = Modifier.padding(start = 8.dp)
                )

                Spacer(modifier =Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(20.dp)
                        .background(Color(0xFF64B5F6)) // 빨간색 박스
                )
                Text(
                    text = "간식/음료",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

        }

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    typeCard()
}

package com.example.mobilesoftwareproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilesoftwareproject.model.Item
import com.example.mobilesoftwareproject.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.rounded.ArrowBack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.mobilesoftwareproject.model.Meal
import com.example.mobilesoftwareproject.viewmodel.MealViewModel


@Composable
fun DetailScreen(navController: NavController, mealId: Int, mealViewModel: MealViewModel) {
    var meal by remember { mutableStateOf<Meal?>(null) }

    LaunchedEffect(mealId) {
        mealViewModel.getById(mealId,
            onResult = { meal = it }
        )
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("상세 정보") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "뒤로가기"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            meal?.let { mealData ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(mealData.PhotoUri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                Text(
                    text = "크게 보기",
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.6F), RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 상세 정보 표시
                    Text(text = "음식 이름: ${mealData.foodName}", fontSize = 18.sp)
                    Text(text = "반찬: ${mealData.sideDishNames}", fontSize = 16.sp)
                    Text(text = "가격: ${mealData.price} 원", fontSize = 16.sp)
                    Text(text = "칼로리: ${mealData.calories} kcal", fontSize = 16.sp)
                    Text(text = "식사 종류: ${mealData.mealType}", fontSize = 16.sp)
                    Text(text = "장소: ${mealData.Location}", fontSize = 16.sp)
                    Text(text = "날짜: ${formatDate(mealData.date)}", fontSize = 16.sp)
                    Text(text = "리뷰: ${mealData.review}", fontSize = 16.sp)
                }
            } ?: run {
                // 로딩 또는 에러 처리
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "데이터를 불러오는 중입니다...")
                }
            }
        }
    }
}


/*innerPadding ->
Column(
    modifier = Modifier
        .padding(innerPadding)
        .padding(16.dp)
) {
    // 이미지
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray),
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            painter = rememberAsyncImagePainter(item.imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
//                Text(
//                    text = "크게 보기",
//                    color = Color.White,
//                    modifier = Modifier
//                        .background(Color.Black.copy(alpha = 0.6F), RoundedCornerShape(16.dp))
//                        .padding(horizontal = 12.dp, vertical = 4.dp)
//                        .align(Alignment.BottomEnd)
//                        .padding(8.dp)
//                )
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 음식 정보 카드
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = item.name,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = item.location,
                        style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "${item.price} 원",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3F51B5)
                        )
                    )
                    Text(
                        text = item.date,
                        style = TextStyle(fontSize = 12.sp, color = Color.Gray)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "식사 종류",
                        style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                    )
                    Text(
                        text = item.type,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "칼로리",
                        style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                    )
                    Text(
                        text = "${item.calories} kcal",
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 반찬 구성
    Text(
        text = "반찬 구성",
        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item.sideDishes.forEach { sideDish ->
            TagItem(tag = sideDish)
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 리뷰
    Text(
        text = "리뷰",
        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = item.review,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier.padding(16.dp)
        )
    }
}
}*/


@Composable
fun TagItem(tag: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = tag, fontSize = 12.sp, color = Color.Black)
    }
}


@Preview
@Composable
fun PreviewDetailScreen() {
    val navController = NavController(LocalContext.current)



}

package com.example.mobilesoftwareproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background


import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter

import com.example.mobilesoftwareproject.model.Meal
import com.example.mobilesoftwareproject.viewmodel.MealViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewScreen(navController: NavController, itemViewModel: ItemViewModel) {
    val items = itemViewModel.items

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items.size) { index ->
            val item = items[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("detail/${item.id}") },
                shape = MaterialTheme.shapes.medium
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Text(item.name, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("${item.price}원", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
*/



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewScreen(navController: NavController, mealViewModel: MealViewModel) {
    // Observe the meals from MealViewModel
    // get all meals
    val mealList by mealViewModel.allMeals.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        mealViewModel.loadAllMeals()
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(modifier  = Modifier.height(16.dp))
        Title("식사 내역")



        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(mealList) { meal ->
                FoodCard(meal = meal, onClick = {
                    navController.navigate("detail/${meal.id}")
                }, onDelete = {
                    mealViewModel.deletemeal(meal)
                })
            }
        }
    }
}


@Composable
fun MealItem(meal: Meal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 4.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imageFile = meal.PhotoUri?.let { File(it) }

            if (imageFile != null && imageFile.exists()) {
                Image(
                    painter = rememberAsyncImagePainter(imageFile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                // 이미지가 없을 경우 대체 이미지 표시
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(8.dp)
                        .background(Color.Gray)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = meal.foodName,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = formatDate(meal.date),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }


        }
    }
}

@Composable
fun FoodCard(meal: Meal, onClick: () -> Unit, onDelete: () -> Unit) {

    val imageFile = meal.PhotoUri?.let { File(it) }


    androidx.compose.material3.Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp)
            .clickable { onClick() },// 카드 내부 간격 추가
        colors = CardDefaults.cardColors(
            containerColor = Color(0xfff5f5f5) // 카드 배경색 설정
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {//카트 내용 간격
            if (imageFile != null && imageFile.exists()) {
                Image(
                    painter = rememberAsyncImagePainter(imageFile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                // 이미지가 없을 경우 대체 이미지 표시
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(8.dp)
                        .background(Color.Gray)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = meal.foodName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF000000)// 텍스트 색상
                )
                Spacer(modifier = Modifier.height(4.dp))//텍스트 간격
                Text(
                    text = meal.Location,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(2.dp))//텍스트 간격
                Text(
                    text = meal.mealType,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = formatDate(meal.date),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))//텍스트 간격
                Text(
                    text = "${meal.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))//텍스트 간격

                Text(
                    text = "삭제하기",
                    fontSize = 12.sp,
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        onDelete()
                    }
                )
            }
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // 상하 간격 추가
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(start = 32.dp), // 세로 가운데 정렬
//        textAlign = TextAlign.Center // 중앙 정렬
    )
}

fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(date)
}
package com.example.mobilesoftwareproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilesoftwareproject.R

data class FoodItem(
    val image: Int,
    val name: String,
    val location: String,
    val category: String,
    val price: String,
    val date: String
)

@Composable
fun FoodListScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Title("급식 목록")
            Spacer(modifier = Modifier.height(16.dp))
            FoodList()
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
            .padding(horizontal = 16.dp),
        textAlign = TextAlign.Start
    )
}

@Composable
fun FoodList() {
    val foodItems = listOf(
        FoodItem(
            image = R.drawable.ic_launcher_background, // 리소스 파일에 이미지 추가 필요
            name = "김치찌개",
            location = "상록원 2층",
            category = "중식",
            price = "5,500원",
            date = "2024-01-15"
        ),
        FoodItem(
            image = R.drawable.ic_launcher_background, // 리소스 파일에 이미지 추가 필요
            name = "아메리카노",
            location = "교내 카페",
            category = "간식/음료",
            price = "3,000원",
            date = "2024-01-15"
        )
    )

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        foodItems.forEach { foodItem ->
            FoodCard(foodItem)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
fun FoodCard(item: FoodItem) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = item.location,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = item.category,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = item.date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Blue
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { /* Handle click */ },
            icon = { Text("📷") },
            label = { Text("입력") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Handle click */ },
            icon = { Text("📋") },
            label = { Text("목록") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Handle click */ },
            icon = { Text("📊") },
            label = { Text("분석") }
        )
    }
}
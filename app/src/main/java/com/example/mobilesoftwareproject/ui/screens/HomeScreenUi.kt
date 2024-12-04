import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//package com.example.mobilesoftwareproject.ui.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.mobilesoftwareproject.R
//import androidx.compose.ui.tooling.preview.Preview
//import coil3.compose.rememberAsyncImagePainter
//import com.example.mobilesoftwareproject.model.Meal
//
//data class FoodItem(
//    val image: Int,
//    val name: String,
//    val location: String,
//    val category: String,
//    val price: String,
//    val date: String
//)
//
//@Composable
//fun FoodListScreen() {
//    Scaffold(
//        containerColor = Color(0xFFF5F5F5), // 전체 배경색 설정 (밝은 회색 예시)
////        bottomBar = { BottomNavigationBar() }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            Spacer(modifier= Modifier.height(16.dp))
//            Title("식사 내역")
//            Spacer(modifier = Modifier.height(8.dp))//상단 여백
//            FoodList()
//        }
//    }
//}
//

//
//@Composable
//fun FoodList() {
//    val foodItems = listOf(
//        FoodItem(
//            image = R.drawable.kimchi, // 리소스 파일에 이미지 추가 필요
//            name = "김치찌개",
//            location = "상록원 2층",
//            category = "중식",
//            price = "5,500원",
//            date = "2024-01-15"
//        ),
//        FoodItem(
//            image = R.drawable.americano, // 리소스 파일에 이미지 추가 필요
//            name = "아메리카노",
//            location = "교내 카페",
//            category = "간식/음료",
//            price = "3,000원",
//            date = "2024-01-15"
//        )
//    )
//
//    LazyColumn(
//        modifier = Modifier
//            .padding(horizontal = 16.dp)
//            .fillMaxWidth()
//    ) {
//        items(foodItems) { foodItem ->
//            FoodCard()
//            Spacer(modifier = Modifier.height(32.dp))//카드간 간격
//        }
//    }//스크롤이 가능하도록 수정
//}
//
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewFoodListScreen() {
//    FoodListScreen()
//}

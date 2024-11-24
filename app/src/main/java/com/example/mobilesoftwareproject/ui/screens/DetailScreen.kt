package com.example.mobilesoftwareproject.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun DetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 상단 제목
        Text(
            text = "상세 정보",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 이미지 및 크기 보기 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("150 x 150", color = Color.Gray, fontSize = 16.sp)
            Button(
                onClick = { /* Handle full size image */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("크게 보기", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 상세 정보 카드
        Card(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // 음식 이름과 가격
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("김치찌개", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text("상록원 2층", color = Color.Gray, fontSize = 14.sp)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("5,500 원", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("2024-01-15", color = Color.Gray, fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 식사 정보
                Text("식사 정보", fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("식사 종류: 중식")
                    Text("칼로리: 650 kcal")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 반찬 구성
                Text("반찬 구성", fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Chip("계란말이")
                    Chip("무나물")
                    Chip("김")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 리뷰
                Text("리뷰", fontWeight = FontWeight.Bold)
                Text(
                    "오늘 김치찌개는 굉장히 맛있었습니다. 특히 고기가 많이 들어있어서 좋았어요.",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun Chip(text: String) {
    Surface(
        color = Color.LightGray,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}
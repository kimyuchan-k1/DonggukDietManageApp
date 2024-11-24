package com.example.mobilesoftwareproject.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilesoftwareproject.R

@Composable
fun InputScreen() {
    var location by remember { mutableStateOf("상록원 2층") }
    var foodName by remember { mutableStateOf("") }
    var review by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var mealType by remember { mutableStateOf("조식") }
    var price by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 제목
        Text(
            text = "급식 입력",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // 위치 선택
        DropdownMenuSample(
            selectedOption = location,
            onOptionSelected = { location = it }
        )

        // 음식 사진 업로드
        PhotoUploadSection()

        // 음식 이름
        OutlinedTextField(
            value = foodName,
            onValueChange = { foodName = it },
            label = { Text("음식 이름") },
            placeholder = { Text("예) 김치찌개") },
            modifier = Modifier.fillMaxWidth()
        )

        // 리뷰 입력
        OutlinedTextField(
            value = review,
            onValueChange = { review = it },
            label = { Text("리뷰") },
            placeholder = { Text("음식에 대한 평가를 작성하세요") },
            modifier = Modifier.fillMaxWidth()
        )

        // 날짜 및 식사 종류
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("날짜") },
                placeholder = { Text("mm/dd/yyyy") },
                modifier = Modifier.weight(1f)
            )

            DropdownMenuSample(
                selectedOption = mealType,
                onOptionSelected = { mealType = it }
            )
        }

        // 가격 입력
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("가격") },
            placeholder = { Text("0") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // 저장하기 버튼
        Button(
            onClick = { /* 저장 기능 구현 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("저장하기", fontSize = 18.sp)
        }
    }
}

@Composable
fun DropdownMenuSample(selectedOption: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(selectedOption)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
//            listOf("조식", "중식", "석식").forEach { option ->
//                DropdownMenuItem(onClick = {
//                    onOptionSelected(option)
//                    expanded = false
//                }) {
//                    Text(option)
//                }
//            }
        }
    }
}

@Composable
fun PhotoUploadSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(
                BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { /* 사진 업로드 기능 구현 */ },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background), // 리소스에 아이콘 추가
                contentDescription = "사진 업로드",
                tint = Color.Gray,
                modifier = Modifier.size(40.dp)
            )
            Text("사진을 업로드하세요", color = Color.Gray)
        }
    }
}

@Preview
@Composable
fun InputScreenPreview() {
    InputScreen()
}




package com.example.mobilesoftwareproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilesoftwareproject.model.Item
import com.example.mobilesoftwareproject.R
import androidx.compose.ui.res.painterResource

@Composable
fun DetailScreen(navController: NavController, item: Item) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("상세 정보") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = null
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
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(item.name, style = MaterialTheme.typography.h5)
            Text(item.location, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(8.dp))
            Text("가격: ${item.price}원")
            Text("날짜: ${item.date}")
            Text("식사 종류: ${item.type}")
            Text("칼로리: ${item.calories} kcal")
            Text("리뷰: ${item.review}")
            Text("반찬: ${item.sideDishes.joinToString(", ")}")
        }
    }
}


package com.example.mobilesoftwareproject.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilesoftwareproject.R
import com.example.mobilesoftwareproject.model.Item

@Composable
fun DetailScreenUi(navController: NavController, item: Item) {


    // 반찬 데이터 입력받기
    val sidedish: String = item.sideDishes


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {

            TopBar3(navController)
            Spacer(modifier = Modifier.height(16.dp))

            // 이미지
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
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
                                text = "비용",
                                style = TextStyle(fontSize = 12.sp, color = Color.Gray)
                            )
                            Text(
                                text = "${item.price}",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF3F51B5)
                                )
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

                    Spacer(modifier = Modifier.height(16.dp))


                    // if 반착 데이터 가 있을 시에 만 적용
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "반찬",
                                style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                            )
                            // 반찬 있으면
                            Text(
                                text = "없음", // 반찬 데이터 넣기
                                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            )
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 반찬 구성

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
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val item = Item(
        id = 1,
        imageRes = R.drawable.kimchi,
        name = "김치찌개",
        location = "상록원 2층",
        type = "중식",
        price = 55000,
        date = "2023-12-03",
        calories = 600,
        sideDishes = "계란말이",
        review = "맛있어요"
    )
    DetailScreenUi(navController = NavController(LocalContext.current), item = item)

}


@Composable
fun TopBar3(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        // 뒤로가기 버튼 넣기
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "뒤로가기"
            )
        }

        Spacer(modifier = Modifier.weight(1f))


        Text(
            text = "상세 정보",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 12.dp,)
        )
        Spacer(modifier= Modifier.weight(1f))
        Text(
            text ="            "
        )

    }

}
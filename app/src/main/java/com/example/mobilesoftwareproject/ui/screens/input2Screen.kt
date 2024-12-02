package com.example.mobilesoftwareproject.ui.screens
// 이미지 피커
// 날짜 피커



import android.app.DatePickerDialog
import android.net.Uri
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import com.example.mobilesoftwareproject.model.Meal
import com.example.mobilesoftwareproject.model.MealDatabase
import com.example.mobilesoftwareproject.repository.MealRepository

import com.example.mobilesoftwareproject.viewmodel.MealViewModel
import java.util.*


import java.io.File
import java.io.FileOutputStream
import android.content.Context

@Composable
fun MealInputScreen(navController: NavController, mealViewModel: MealViewModel) {
    val navBackStackEntry  = remember {navController.getBackStackEntry("meal_input")}
    // 상태 변수들
    // 위치 , 종류 , 날짜, 평가, 음식이름, 사이드 이름, 가격, 이미지
    var location by remember { mutableStateOf("상록원 2층") }
    var mealType by remember { mutableStateOf("조식") }
    var selectedDate by remember { mutableStateOf("") }
    var review by remember { mutableStateOf("") }
    var foodName by remember {mutableStateOf("")}
    var sideName by remember {mutableStateOf("")}
    var price by remember {mutableStateOf("")}
    var imageUri by remember {mutableStateOf<String?>(null)}

    // 위치 옵션
    val locations = listOf("상록원 2층", "상록원 3층", "기숙사")
    // 식사 종류 옵션
    val mealTypes = listOf("조식", "중식", "석식", "간식/음료")
    val context = LocalContext.current

//    val mealViewModel: MealViewModel = viewModel(
//        navBackStackEntry,
//        factory = MealViewModelFactory(MealRepository(MealDatabase.getDatabase(context).MealDao()))
//    )
    // mealType 에 맞는 칼로리를 입력하자



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // 상단 제목 바 등록 버튼 누를 시?
        TopBar(navController, onRegisterClick = {
            // 유효성 검사

            if(foodName.isEmpty() || price.isEmpty() || selectedDate.isEmpty()) {
                Toast.makeText(context, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@TopBar
            }


            //roomdb에 저장
            val newMeal = Meal(
                Location = location,
                PhotoUri = imageUri,
                foodName = foodName,
                sideDishNames = sideName,
                review = review,
                date = parseDate(selectedDate),
                mealType = mealType,
                price = price.toInt(),
                calories = calculateCalories(mealType, price.toInt())

            )

            mealViewModel.insertMeal(newMeal)

            Toast.makeText(context,"식사가 등록되었습니다.",Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        })

        Spacer(modifier = Modifier.height(16.dp))

        // 사진 첨부 - uri를 상태 변수에 저장
        PhotoAttachmentScreen { imagePath ->
            imageUri = imagePath
        }
        Spacer(modifier = Modifier.height(16.dp))

        // 식사 정보 카드
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Spacer(modifier = Modifier.width(8.dp))

                // 장소 선택 드롭다운

                Text(
                    text = "장소",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                )
                // 위치 리스트의 드롭다운 매뉴
                DropdownSelector(
                    options = locations,
                    selectedOption = location,
                    onOptionSelected = { location = it }
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {

            Text(
                text = "음식",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))



            Text(
                text = "반찬",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

        }


        Row {
            OutlinedTextField(
                value = foodName,
                onValueChange = { foodName = it },
                placeholder = { Text("음식 이름을 입력하세요", fontSize = 12.sp, color = Color.Gray
                ) },
                modifier = Modifier
                    .width(170.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier  =Modifier.weight(1f))

            // 값이 바뀔 때 마다 상태 변수들도 값이 바뀜.

            OutlinedTextField(
                value = sideName,
                onValueChange = { sideName = it},
                placeholder = { Text("반찬 이름을 입력하세요", fontSize = 12.sp, color = Color.Gray) },
                modifier = Modifier
                    .width(170.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "가격",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            placeholder = { Text("가격을 입력하세요", fontSize = 12.sp, color = Color.Gray
            ) },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )


        Spacer(modifier = Modifier.height(16.dp))

        // 식사 종류 선택
        Text(
            text = "식사 종류",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        DropdownSelector(
            options = mealTypes,
            selectedOption = mealType,
            onOptionSelected = { mealType = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 날짜 입력
        Text(
            text = "날짜 선택",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        DatePickerField(selectedDate = selectedDate, onDateSelected = { selectedDate = it })

        Spacer(modifier = Modifier.height(16.dp))

        // 문의 입력 필드
        Text(
            text = "리뷰",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = review  ,
            onValueChange = { review  = it },
            placeholder = { Text("식사 리뷰를 해주세요", fontSize = 14.sp, color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 등록 버튼
       /* Button(
            onClick = { *//* 등록 로직 *//* },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3F51B5))
        ) {
            Text(text = "등록", color = Color.White)
        }*/
    }
}

//topbar 구현 -- topbar는 따로 구현하기
@Composable
fun TopBar(navController: NavController, onRegisterClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 닫기 버튼
        Text(
            text = "✕",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.clickable { // 홈화면으로 다시 이동
                navController.popBackStack()
                 }
        )
        Spacer(modifier = Modifier.weight(1f))
        // 제목
        Text(
            text = "식사 입력",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.weight(1f))
        // 등록 버튼
        Text(
            text = "등록",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F51B5)),
            modifier = Modifier.clickable { onRegisterClick() }
        )

    }
}


// 커스텀 드롭다운
@Composable
fun DropdownSelector(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .clickable { expanded = !expanded }
            .padding(12.dp)
    ) {
        Text(text = selectedOption, color = Color.Black, fontSize = 16.sp)

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(option)
                    expanded = false
                }) {
                    Text(text = option)
                }
            }
        }
    }
}

//사진 첨부
@Composable
fun PhotoAttachmentScreen(onImageSelected: (String?) -> Unit) {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        uri?.let {
            // 이미지를 내부 저장소에 저장
            val savedImagePath = saveImageToInternalStorage(context, it)
            onImageSelected(savedImagePath)
        }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        // 사진 영역 -- 사진이 선택되었을 시만 Box 를 보이게
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (selectedImageUri != null) {
                // 사진이 선택되었을 경우 -- 해당 이미지를 표시
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text(
                    text = "사진을 첨부하세요",
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 사진 첨부 버튼
        Button(
            onClick = { imagePickerLauncher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("사진 첨부")
        }
    }
}

// 내부 저장소에 사진 저장


fun saveImageToInternalStorage(context: Context, imageUri: Uri): String? {
    return try {
        val inputStream = context.contentResolver.openInputStream(imageUri)
        inputStream?.let {
            val filename = "image_${System.currentTimeMillis()}.jpg"
            val file = File(context.filesDir, filename)
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()
            file.absolutePath
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}



// String -> Date
fun parseDate(dateString: String) : Date {
    val formatter = java.text.SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.parse(dateString) ?: Date()
}

// 임의 칼로리 계산 함수
fun calculateCalories(mealType: String, price: Int): Int {
    return when(mealType) {
        "조식" -> 500
        "중식" -> 700
        "석식" -> 600
        "간식/음료" -> 300
        else -> 0
    }
}


@Composable
fun DatePickerField(selectedDate: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .clickable {
                DatePickerDialog(
                    context,
                    { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                        val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                        onDateSelected(formattedDate)
                    },
                    year, month, day
                ).show()
            }
            .padding(12.dp)
    ) {
        Text(
            text = if (selectedDate.isEmpty()) "날짜를 선택하세요" else selectedDate,
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}


@Preview(showBackground = true, name = "Meal Input Screen Preview")
@Composable
fun MealInputScreenPreview() {

    val applicationContext = LocalContext.current
    val navController = rememberNavController()
    val mealDao = MealDatabase.getDatabase(applicationContext).MealDao()
    val mealRepository = MealRepository(mealDao)


    val mealViewModel  = MealViewModel(mealRepository)
    MealInputScreen(navController, mealViewModel)
}

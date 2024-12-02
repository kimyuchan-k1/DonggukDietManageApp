import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.mobilesoftwareproject.model.Item
import com.example.mobilesoftwareproject.viewmodel.ItemViewModel
import com.example.mobilesoftwareproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(navController: NavController, itemViewModel: ItemViewModel) {
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("상록원 2층") }
    var price by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("조식") }
    var review by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            modifier =Modifier
                .align(Alignment.CenterHorizontally)
        ) {

            Text(
                modifier = Modifier,
                text = "입력",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {},
            ) {
                Text(
                    text = "등록",
                )
            }
        }


        Text(
            text = "위치선택",
            style = MaterialTheme.typography.titleSmall,
        )

        // exposed dropdown
        LocationExposedDropdownMenu()

        // Name Input
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("음식 이름") },
            modifier = Modifier.fillMaxWidth()
        )

        // Price Input
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("가격") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Date Input
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("날짜") },
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown for type
        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("식사 종류") },
            modifier = Modifier.fillMaxWidth()
        )

        // Review Input
        Box(
            modifier = Modifier.fillMaxWidth(),

            ) {
            OutlinedTextField(

                value = review,
                onValueChange = { review = it },
                label = { Text("리뷰") },
                modifier = Modifier.fillMaxWidth()
            )
        }


        // Save Button
        Button(
            onClick = {
                // Add saving logic
                navController.navigate("view")
            },

            ) {
            Text("저장하기")
        }
    }
}

// 라디오 버튼
@Composable
fun LocationRadioButtons() {
    val locations = listOf("상록원 2층", "상록원 3층", "기숙사")
    var selectedLocation by remember { mutableStateOf(locations[0]) }

    Column {
        locations.forEach { location ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { selectedLocation = location }
            ) {
                RadioButton(
                    selected = (selectedLocation == location),
                    onClick = { selectedLocation = location }
                )
                Text(
                    text = location,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


// 드롭 다운 메뉴
@Preview
@Composable
fun LocationDropdownMenu() {
    val locations = listOf("상록원 2층", "상록원 3층", "기숙사")
    var expanded by remember { mutableStateOf(false) }
    var selectedLocation by remember { mutableStateOf(locations[0]) }

    Box(modifier = Modifier.fillMaxWidth()) {

        TextButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = selectedLocation)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            locations.forEach { location ->
                DropdownMenuItem(
                    text = { Text(location) },
                    onClick = {
                        selectedLocation = location
                        expanded = false
                    })
            }
        }
    }
}

//exposed dropdownmenu

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LocationExposedDropdownMenu() {
    val locations = listOf("상록원 2층", "상록원 3층", "기숙사")
    var expanded by remember { mutableStateOf(false) }
    var selectedLocation by remember { mutableStateOf(locations[0]) }


    ExposedDropdownMenuBox(

        expanded = expanded,
        // expanded 가 변경된다면?
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            value = selectedLocation,
            onValueChange = {},
            readOnly = true,
            label = { Text("장소 선택") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Arrow"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
            // ExposedDropdownMenuBox 내부에서는 menuAnchor를 사용해야 합니다.
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            locations.forEach { location ->
                DropdownMenuItem(
                    text = { Text(location) },
                    onClick = {
                        selectedLocation = location
                        expanded = false
                    }
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputScreenPreview() {

    InputScreen(
        navController = NavController(LocalContext.current),
        itemViewModel = ItemViewModel()
    )


}
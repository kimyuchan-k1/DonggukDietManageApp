import android.content.ClipData
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilesoftwareproject.model.Item
import com.example.mobilesoftwareproject.R


@Composable
fun CafeteriaLayout() {
    var activeSection by remember { mutableStateOf("view") }
    var selectedItem by remember { mutableStateOf<Item?>(null) }

    val sectionTitles = mapOf(
        "input" to "급식 입력",
        "view" to "급식 목록",
        "analysis" to "급식 분석"
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(sectionTitles[activeSection] ?: "") })
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = activeSection == "input",
                    onClick = { activeSection = "input" },
                    icon = { Icon(painterResource(id = R.drawable.ic_launcher_background), contentDescription = null) },
                    label = { Text("입력") }
                )
                BottomNavigationItem(
                    selected = activeSection == "view",
                    onClick = { activeSection = "view" },
                    icon = { Icon(painterResource(id = R.drawable.ic_launcher_background), contentDescription = null) },
                    label = { Text("목록") }
                )
                BottomNavigationItem(
                    selected = activeSection == "analysis",
                    onClick = { activeSection = "analysis" },
                    icon = { Icon(painterResource(id = R.drawable.ic_launcher_background), contentDescription = null) },
                    label = { Text("분석") }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                selectedItem != null -> {
                    DetailView(item = selectedItem!!) { selectedItem = null }
                }
                activeSection == "input" -> {
                    InputSection()
                }
                activeSection == "view" -> {
                    ViewSection(onSelectItem = { selectedItem = it })
                }
                activeSection == "analysis" -> {
                    AnalysisSection()
                }
            }
        }
    }
}

@Composable
fun DetailView(item: Item, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF8F9FA))
    ) {
        TopAppBar(
            title = { Text("상세 정보") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(painterResource(id = R.drawable.ic_launcher_background), contentDescription = null)
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(item.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(item.location, fontSize = 16.sp, color = Color.Gray)
        }
    }
}

@Composable
fun InputSection() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = "",
            onValueChange = { /* Handle input */ },
            label = { Text("위치 선택") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { /* Save logic */ }, modifier = Modifier.fillMaxWidth()) {
            Text("저장하기")
        }
    }
}

@Composable
fun ViewSection(onSelectItem: (Item) -> Unit) {
    val dummyData = listOf(
        Item(
            id = 1,
            name = "김치찌개",
            location = "상록원 2층",
            price = 5500,
            date = "2024-01-15",
            type = "중식",
            imageRes = R.drawable.ic_launcher_background,
            review = "굉장히 맛있었습니다.",
            calories = 650,
            sideDishes = "계란말이"
        )
    )
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        dummyData.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectItem(item) }
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(item.name, fontWeight = FontWeight.Bold)
                        Text(item.location, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun AnalysisSection() {
    Text("Analysis Placeholder", modifier = Modifier.padding(16.dp))
}

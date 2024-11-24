package com.example.mobilesoftwareproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.lang.reflect.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilesoftwareproject.ui.screens.AnalysisScreen
import com.example.mobilesoftwareproject.ui.screens.DetailScreen
import com.example.mobilesoftwareproject.ui.screens.FoodListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodListScreen() // FoodListScreen 호출
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFoodListScreen() {
    FoodListScreen()
}


@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewAnalysisScreen() {
    AnalysisScreen()
}




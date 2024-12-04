package com.example.mobilesoftwareproject.ui.screens




import androidx.activity.viewModels
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mobilesoftwareproject.model.MealDao
import com.example.mobilesoftwareproject.model.MealDatabase
import com.example.mobilesoftwareproject.repository.MealRepository
import com.example.mobilesoftwareproject.viewmodel.ItemViewModel
import com.example.mobilesoftwareproject.viewmodel.MealViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, mealViewModel: MealViewModel) {


    val bottomNavItems = listOf("meal_input", "home", "analysis")

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(


                        modifier = Modifier.padding(start = 0.dp),
                        selected = currentRoute(navController) == screen,
                        onClick = {
                            navController.navigate(screen) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            when (screen) {
                                "meal_input" -> Icon(
                                    Icons.Rounded.Add,
                                    contentDescription = null
                                )
                                "home" -> Icon(
                                    Icons.Filled.Home,
                                    contentDescription = null
                                )
                                "analysis" -> Icon(
                                    Icons.Outlined.Menu,
                                    contentDescription = null
                                )
                            }
                        },
                        label =  {
                            when (screen) {
                                "meal_input" -> Text("입력")
                                "home" -> Text("메인 페이지")
                                "analysis" -> Text("분석")
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                // 시작 지점
                startDestination = "home"
            ) {
                composable("meal_input") {
                    MealInputScreen(navController, mealViewModel)
                }
                composable("home") {
                    ViewScreen(navController, mealViewModel)
                }
                composable("analysis") {
                    // navController, mealViewModel 을 받자
                    CaloriesScreen(mealViewModel)
//                    AnalysisScreen(mealviewModel)
                }
                composable("detail/{mealId}") { backStackEntry ->
                    val mealId = backStackEntry.arguments?.getString("mealId")?.toIntOrNull() ?: 0
                    DetailScreen(navController, mealId, mealViewModel)
                    }
                }
            }
        }
    }

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    //viewmodel
    val applicatinContext = LocalContext.current
    val navController = rememberNavController()
    val mealDao = MealDatabase.getDatabase(applicatinContext).MealDao()
    val mealRepository = MealRepository(mealDao)


    val mealViewModel  = MealViewModel(mealRepository)
    MainScreen(navController, mealViewModel)

}
package com.example.mobilesoftwareproject.ui.screens



import InputScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilesoftwareproject.R
import com.example.mobilesoftwareproject.model.Item
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mobilesoftwareproject.viewmodel.ItemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(itemViewModel: ItemViewModel) {
    val navController = rememberNavController()
    val bottomNavItems = listOf("input", "view", "analysis")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val currentRoute = currentRoute(navController)
                    Text(
                        text = when (currentRoute) {
                            "input" -> "급식 입력"
                            "view" -> "급식 목록"
                            "analysis" -> "급식 분석"
                            "detail/{itemId}" -> "상세 정보"
                            else -> ""
                        },
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
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
                                "input" -> Icon(
                                    painter = painterResource(id = R.drawable.ic_launcher_background),
                                    contentDescription = null
                                )
                                "view" -> Icon(
                                    painter = painterResource(id = R.drawable.ic_launcher_background),
                                    contentDescription = null
                                )
                                "analysis" -> Icon(
                                    painter = painterResource(id = R.drawable.ic_launcher_background),
                                    contentDescription = null
                                )
                            }
                        },
                        label = {
                            when (screen) {
                                "input" -> Text("입력")
                                "view" -> Text("목록")
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
                startDestination = "view"
            ) {
                composable("input") {
                    InputScreen(navController, itemViewModel)
                }
                composable("view") {
                    ViewScreen(navController, itemViewModel)
                }
                composable("analysis") {
                    AnalysisScreen()
                }
                composable("detail/{itemId}") { backStackEntry ->
                    val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull()
                    val item = itemViewModel.items.firstOrNull { it.id == itemId }
                    item?.let {
                        DetailScreen(navController, it)
                    }
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
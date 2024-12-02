package com.example.mobilesoftwareproject

import CafeteriaLayout
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

import com.example.compose.AppTheme
import com.example.mobilesoftwareproject.model.MealDatabase
import com.example.mobilesoftwareproject.repository.MealRepository
import com.example.mobilesoftwareproject.ui.screens.MainScreen
import com.example.mobilesoftwareproject.viewmodel.ItemViewModel
import com.example.mobilesoftwareproject.viewmodel.MealViewModel
import com.example.mobilesoftwareproject.viewmodel.MealViewModelFactory


class MainActivity : ComponentActivity() {

    private lateinit var mealViewModel: MealViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mealDao = MealDatabase.getDatabase(applicationContext).MealDao()
        val mealRepository = MealRepository(mealDao)


        mealViewModel  = MealViewModel(mealRepository)
        setContent {
            val navController = rememberNavController()
            AppTheme {
                MainScreen(navController, mealViewModel)
            }
        }
    }
}




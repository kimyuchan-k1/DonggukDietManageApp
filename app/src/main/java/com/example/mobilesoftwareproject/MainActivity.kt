package com.example.mobilesoftwareproject

import CafeteriaLayout
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mobilesoftwareproject.ui.screens.MainScreen
import com.example.mobilesoftwareproject.viewmodel.ItemViewModel
import java.time.format.TextStyle



class MainActivity : ComponentActivity() {
    private val itemViewModel = ItemViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(itemViewModel)
        }
    }
}

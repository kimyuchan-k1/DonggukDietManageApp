package com.example.mobilesoftwareproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.mobilesoftwareproject.R
import com.example.mobilesoftwareproject.model.Item

class ItemViewModel : ViewModel() {
    val items: SnapshotStateList<Item> = mutableStateListOf(
        // Dummy data
        Item(
            id = 1,
            name = "김치찌개",
            location = "상록원 2층",
            price = 5500,
            date = "2024-01-15",
            type = "중식",
            imageRes = R.drawable.kimchi,
            review = "오늘 김치찌개는 굉장히 맛있었습니다.",
            calories = 650,
            sideDishes = listOf("계란말이", "무나물", "김")
        ),
        Item(
            id = 2,
            name = "아메리카노",
            location = "교내 카페",
            price = 3000,
            date = "2024-01-15",
            type = "간식/음료",
            imageRes = R.drawable.americano,
            review = "적당한 산미와 깔끔한 맛",
            calories = 5,
            sideDishes = emptyList()
        )
    )

    fun addItem(item: Item) {
        items.add(item)
    }
}
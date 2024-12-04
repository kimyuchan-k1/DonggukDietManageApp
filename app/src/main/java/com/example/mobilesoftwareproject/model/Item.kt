package com.example.mobilesoftwareproject.model


data class Item(
    val id: Int,
    val name: String,
    val location: String,
    val price: Int,
    val date: String,
    val type: String,
    val imageRes: Int,
    val review: String,
    val calories: Int,
    val sideDishes: String
)



package com.example.mobilesoftwareproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
//

// meals data table 생성
@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    val Location: String,  // 장소 - dropdown
    val PhotoUri: String?, // 사진 - 이미지
    val foodName: String,   // 음식 , 음료 이름
    val sideDishNames: String?, // 반찬
    val review: String?, // 평가
    val date: Date, //식사 날짜
    val mealType: String, // 조식,중식,석식,음료
    val price: Int, //가격
    val calories: Int? // 칼로리 -임의로 지정 -- 조식, 중식, 석식에 따라 지정
)

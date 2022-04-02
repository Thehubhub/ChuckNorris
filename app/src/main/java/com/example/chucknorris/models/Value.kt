package com.example.chucknorris.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Value(
    @SerializedName("categories")
    val categories: List<String>,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("joke")
    val joke: String
)
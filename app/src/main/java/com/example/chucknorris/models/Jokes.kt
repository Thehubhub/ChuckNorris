package com.example.chucknorris.models


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Jokes(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: List<Value>
)
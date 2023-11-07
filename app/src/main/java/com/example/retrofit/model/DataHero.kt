package com.example.retrofit.model

import com.google.gson.annotations.SerializedName

data class DataHero(
    @SerializedName("id")
    val idHero: Int,
    @SerializedName("title")
    val titleHero: String,
    @SerializedName("image")
    val imageHero: String
)

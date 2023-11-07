package com.example.retrofit.model

import com.google.gson.annotations.SerializedName

data class HeroModel(
    @SerializedName("result")
    val `data`: List<DataHero>
)

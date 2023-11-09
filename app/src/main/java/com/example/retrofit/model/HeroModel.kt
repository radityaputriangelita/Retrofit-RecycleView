package com.example.retrofit.model

import com.google.gson.annotations.SerializedName
// inisialisasi nama dari api nya juga yaitu 'result' yang diubah jadi HeroModel
data class HeroModel(
    @SerializedName("result")
    val `data`: List<DataHero>
)

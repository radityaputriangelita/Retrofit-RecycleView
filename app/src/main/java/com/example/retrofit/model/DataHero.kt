package com.example.retrofit.model

import com.google.gson.annotations.SerializedName

// inisiasi data yang akan dipake ngikutin nama dari api nya apa
data class DataHero(
    @SerializedName("id")
    val idHero: Int,
    @SerializedName("title")
    val titleHero: String,
    @SerializedName("image")
    val imageHero: String
)

package com.example.retrofit.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
object ApiClient {
    fun getInstance(): ApiService {
        //logging permintaan seluruh body datanya
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        //konfigurasi val sebelumnya dengan builder
        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()
        //buat nentuin url datanya
        val builder = Retrofit.Builder()
            .baseUrl("https://demo.lazday.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
        return builder.create(ApiService::class.java)
    }
}
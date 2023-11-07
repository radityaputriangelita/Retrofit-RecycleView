package com.example.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.DataHero
import com.example.retrofit.model.HeroModel
import com.example.retrofit.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var heroAdapter: HeroAdapter
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi RecyclerView dan LayoutManager
        binding.rvHero.layoutManager = LinearLayoutManager(this)

        // Inisialisasi adapter
        heroAdapter = HeroAdapter(emptyList<DataHero>()) { dataHero ->
            // Lakukan sesuatu ketika item diklik
            // Di sini Anda dapat mengakses dataHero untuk item yang diklik
        }

        binding.rvHero.adapter = heroAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://demo.lazday.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        apiService.getAllHeroes().enqueue(object : Callback<HeroModel> {
            override fun onResponse(call: Call<HeroModel>, response: Response<HeroModel>) {
                if (response.isSuccessful) {
                    val heroModel = response.body()
                    val heroes = heroModel?.data ?: emptyList()
                    heroAdapter.setData(heroes)
                }
            }

            override fun onFailure(call: Call<HeroModel>, t: Throwable) {
                // Handle error
            }
        })
    }
}

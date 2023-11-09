package com.example.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.DataHero
import com.example.retrofit.model.HeroModel
import com.example.retrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var heroAdapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // recycle view nya
        binding.rvHero.layoutManager = GridLayoutManager(this, 2)

        // Inisialisasi adapter

        heroAdapter = HeroAdapter(emptyList<DataHero>()) { dataHero ->}
        binding.rvHero.adapter = heroAdapter

        // get data api nya
        val apiService = ApiClient.getInstance()

        //hubungin dari api nya ke objek adaptor
        apiService.getAllHeroes().enqueue(object : Callback<HeroModel> {
            override fun onResponse(call: Call<HeroModel>, response: Response<HeroModel>) {
                if (response.isSuccessful) {
                    val heroModel = response.body()
                    val heroes = heroModel?.data ?: emptyList()
                    heroAdapter.setData(heroes)
                }
            }

            //kalau error
            override fun onFailure(call: Call<HeroModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi error",
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}

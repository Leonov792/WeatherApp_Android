package com.example.weatherapp.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        binding.btnSearch.setOnClickListener { search() }
        binding.etCity.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) { search(); true } else false
        }

        viewModel.weather.observe(this) { w ->
            if (w != null) {
                binding.tvCity.text = w.name
                binding.tvTemp.text = "${w.main.temp.toInt()}°C"
                binding.tvDesc.text = w.weather[0].description.replaceFirstChar { it.uppercase() }
                binding.tvFeels.text = "Ощущается: ${w.main.feels_like.toInt()}°C"
                binding.tvHumidity.text = "Влажность: ${w.main.humidity}%"
                binding.tvWind.text = "Ветер: ${w.wind.speed} м/с"
                binding.tvPressure.text = "Давление: ${w.main.pressure} гПа"
                Glide.with(this).load("https://openweathermap.org/img/wn/${w.weather[0].icon}@2x.png")
                    .into(binding.ivIcon)
            }
        }
        viewModel.loading.observe(this) { binding.progressBar.visibility = if (it) android.view.View.VISIBLE else android.view.View.GONE }
        viewModel.error.observe(this) { if (it != null) Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
    }

    private fun search() {
        val city = binding.etCity.text.toString().trim()
        if (city.isNotEmpty()) viewModel.loadWeather(city)
    }
}

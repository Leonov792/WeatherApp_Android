package com.example.weatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weather = MutableLiveData<com.example.weatherapp.model.WeatherResponse?>()
    val weather: LiveData<com.example.weatherapp.model.WeatherResponse?> = _weather

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadWeather(city: String) {
        _loading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                _weather.value = RetrofitInstance.api.getWeather(city)
            } catch (e: Exception) {
                _error.value = "Город не найден или нет сети"
            } finally {
                _loading.value = false
            }
        }
    }
}

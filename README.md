# ?? Weather App — Android App

Weather forecast app using OpenWeatherMap public API.

## ?? Screenshots
*(Coming soon from GitHub Actions build)*

## ?? Tech Stack
- **Kotlin** + Coroutines
- **MVVM** Architecture
- **Retrofit2** for API calls
- **OpenWeatherMap API** (free tier)
- **Glide** for image loading
- **LiveData** + **ViewBinding**

## ?? Project Structure
pp/src/main/java/com/example/weatherapp/  
+-- data/ — Retrofit API, RetrofitInstance  
+-- model/ — WeatherResponse data class  
L-- ui/ — MainActivity, WeatherViewModel

## ?? API Key
Replace YOUR_API_KEY in WeatherApi.kt with your key from [openweathermap.org](https://openweathermap.org/api)

## ?? How to Build
1. Open in **Android Studio** (Hedgehog or newer)  
2. Sync Gradle  
3. **Build > Build APK**

Or via CLI: ./gradlew assembleDebug

## ?? Download APK
See [Releases](https://github.com/Leonov792/WeatherApp_Android/releases)

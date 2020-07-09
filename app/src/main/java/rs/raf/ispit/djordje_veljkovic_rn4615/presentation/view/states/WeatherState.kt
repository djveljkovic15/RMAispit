package rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.states

import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.Weather

sealed class WeatherState{

    object Loading: WeatherState()

    object DataFetched: WeatherState()

    data class Success(val weather: List<Weather>):WeatherState()

    data class Error(val message: String):WeatherState()
}
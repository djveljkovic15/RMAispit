package rs.raf.ispit.djordje_veljkovic_rn4615.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.states.WeatherState

interface WeatherContract {

    interface ViewModel{
        val weatherState: LiveData<WeatherState>

        fun fetchWeather(city:String, days: String)

        fun getWeather(city: String)

//        fun filterWeather(city: String)
    }
}
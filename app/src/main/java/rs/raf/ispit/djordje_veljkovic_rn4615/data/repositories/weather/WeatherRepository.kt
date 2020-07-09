package rs.raf.ispit.djordje_veljkovic_rn4615.data.repositories.weather

import io.reactivex.Observable
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.Resource
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.Weather

interface WeatherRepository {

    fun fetchWeather(city:String, days:String): Observable<Resource<Unit>>

    fun getWeather(city: String): Observable<List<Weather>>
//    fun getWeather(city:String, days:String): Observable<List<Weather>>

//    fun filterWeather(city: String): Observable<List<Weather>> //, days: String

}
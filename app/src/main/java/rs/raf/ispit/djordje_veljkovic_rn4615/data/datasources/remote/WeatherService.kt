package rs.raf.ispit.djordje_veljkovic_rn4615.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.WeatherResponse

interface WeatherService {

//    @GET("forecast.json?key=6b5beea74c0b40e4aa7112728200907&q={city}&days={days}")
//    @GET("forecast.json")
//    fun findAll(@Query("key") key:String, @Query("q") city:String, @Query("days") days:String): Observable<WeatherResponse>
    @GET("forecast.json")
    fun findAll(@Query("q") city:String, @Query("days") days:String): Observable<WeatherResponse>
}
package rs.raf.ispit.djordje_veljkovic_rn4615.data.repositories.weather

import io.reactivex.Observable
import rs.raf.ispit.djordje_veljkovic_rn4615.data.datasources.local.WeatherDao
import rs.raf.ispit.djordje_veljkovic_rn4615.data.datasources.remote.WeatherService
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.Resource
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.Weather
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.WeatherEntity


class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override fun fetchWeather(city: String, days: String): Observable<Resource<Unit>> {
//        val key = "6b5beea74c0b40e4aa7112728200907"
//        return weatherService.findAll(key,city, days)
        return weatherService.findAll(city, days)
            .doOnNext {
                val weatherEntities = mutableListOf<WeatherEntity>()
//                if(days.toInt() in 1..3) {
                for (i in 0 until days.toInt()) {

                    weatherEntities.add(
                        WeatherEntity(
                            it.location.name,
                            it.current.temp_c,
                            it.forecast.forecastday[i].day.maxtemp_c,
                            it.forecast.forecastday[i].day.mintemp_c,
                            it.current.wind_mph,
                            it.current.uv,
                            it.location.lat,
                            it.location.lon,
                            it.current.condition.icon,
                            it.forecast.forecastday[i].date
                        )
                    )
                }
                weatherDao.deleteAndInsertAll(weatherEntities)
//                }

//                weatherDao.insertAll(weatherEntities)
            }.map {
                Resource.Success(Unit)
            }
    }

    override fun getWeather(city: String): Observable<List<Weather>> {
        return weatherDao.filterWeather(city)
            .map {
                it.map { response ->
                    Weather(
                        response.id,
                        response.city,
                        response.temp,
                        response.max_temp,
                        response.min_temp,
                        response.wind,
                        response.uv,
                        response.latitude,
                        response.longitude,
                        response.icon,
                        response.date
                    )
                }
            }
    }

}

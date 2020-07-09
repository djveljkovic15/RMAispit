package rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather

import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.current.CurrentResponse
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.forecast.ForecastResponse
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.location.LocationResponse

data class WeatherResponse(
    val id: Long,
    val location: LocationResponse,
    val current: CurrentResponse,
    val forecast: ForecastResponse
)
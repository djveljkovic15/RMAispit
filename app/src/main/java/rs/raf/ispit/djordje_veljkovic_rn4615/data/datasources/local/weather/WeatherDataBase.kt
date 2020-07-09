package rs.raf.ispit.djordje_veljkovic_rn4615.data.datasources.local.weather

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.ispit.djordje_veljkovic_rn4615.data.datasources.local.WeatherDao
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.WeatherEntity

@Database(
    entities = [WeatherEntity::class],
    version = 1
)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao
}
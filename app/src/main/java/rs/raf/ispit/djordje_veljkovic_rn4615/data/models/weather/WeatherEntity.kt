package rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    val city : String,
    val temp: String,
    val max_temp: String,
    val min_temp: String,
    val wind: String,
    val uv: String,
    val latitude: String,
    val longitude: String,
    val icon: String,
    val date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
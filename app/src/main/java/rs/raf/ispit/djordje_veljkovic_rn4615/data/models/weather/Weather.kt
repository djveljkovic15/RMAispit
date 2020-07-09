package rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather

data class Weather(
    val id: Long,
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

)
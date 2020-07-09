package rs.raf.ispit.djordje_veljkovic_rn4615.data.models.location

data class Location(
    val id: Long,
    val name: String,
    val region: String,
    val country: String,
    val lat: String,
    val lon: String
//    val tz_id: String,
//    val localtime_epoch: String,
//    val localtime: String
)
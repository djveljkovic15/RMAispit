package rs.raf.ispit.djordje_veljkovic_rn4615.data.models.current

import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.condition.Condition

data class CurrentResponse(
    val temp_c: String,
    val uv: String,
    val condition : Condition,
    val wind_mph : String

)
package rs.raf.ispit.djordje_veljkovic_rn4615.data.models.day

import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.condition.Condition

data class Day(
    val maxtemp_c: String,
    val mintemp_c: String,
    val condition: Condition
)

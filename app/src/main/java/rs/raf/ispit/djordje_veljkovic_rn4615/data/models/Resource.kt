package rs.raf.ispit.djordje_veljkovic_rn4615.data.models

sealed class Resource<out T> {

    data class Success<out T>(val data: T) : Resource<T>()

    data class Loading<out T>(val message: String = "") : Resource<T>()

    data class Error<out T>(val error: Throwable = Throwable(), val data: T? = null): Resource<T>()

}
package rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.Weather

class WeatherDiffCallback : DiffUtil.ItemCallback<Weather>(){
    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
//        return oldItem.city == newItem.city &&
//            oldItem.date == newItem.date

        return  oldItem.city == newItem.city &&
            oldItem.icon == newItem.icon &&
            oldItem.date == newItem.date &&
            oldItem.latitude == newItem.latitude &&
            oldItem.longitude == newItem.longitude &&
            oldItem.max_temp == newItem.max_temp &&
            oldItem.min_temp == newItem.min_temp &&
            oldItem.uv == newItem.uv &&
            oldItem.wind == newItem.wind
    }

}
package rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.ispit.djordje_veljkovic_rn4615.R
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.Weather
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.recycler.diff.WeatherDiffCallback
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.recycler.viewholder.WeatherViewHolder

class WeatherAdapter (weatherDiffCallback: WeatherDiffCallback,
                      val onClicked:(Weather)->Unit
                        ):ListAdapter<Weather, WeatherViewHolder>(weatherDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_weather,parent,false)
        return WeatherViewHolder(view) {
            val weather = getItem(it)
            onClicked.invoke(weather)
        }
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
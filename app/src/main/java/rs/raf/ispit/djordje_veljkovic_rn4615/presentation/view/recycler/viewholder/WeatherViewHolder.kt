package rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_weather.*
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.Weather
import timber.log.Timber

class WeatherViewHolder (override val containerView: View):
    RecyclerView.ViewHolder(containerView), LayoutContainer{

    fun bind(weather: Weather){
        Timber.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        Timber.e(weather.icon)
       Picasso //Prokleti picasso, mora da se doda "https:" ili ne radi!
           .get()
           .load("https:"+weather.icon)
           .into(recyclerWeatherIcon)

//        Glide
//            .with(itemView)
//            .load("//cdn.weatherapi.com/weather/64x64/day/356.png")
//            .into(recyclerWeatherIcon)

        recyclerWeatherCity.text = weather.city
        recyclerWeatherDate.text = weather.date
        recyclerWeatherTemp.text = (weather.max_temp.toDouble().plus(weather.min_temp.toDouble())/2).toString()
    }
}
package rs.raf.ispit.djordje_veljkovic_rn4615.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.ispit.djordje_veljkovic_rn4615.data.datasources.local.weather.WeatherDataBase
import rs.raf.ispit.djordje_veljkovic_rn4615.data.datasources.remote.WeatherService
import rs.raf.ispit.djordje_veljkovic_rn4615.data.repositories.weather.WeatherRepository
import rs.raf.ispit.djordje_veljkovic_rn4615.data.repositories.weather.WeatherRepositoryImpl
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.viewmodel.WeatherViewModel

val weatherModule = module{

    viewModel {
        WeatherViewModel(weatherRepository = get())
    }

    single<WeatherRepository>{
        WeatherRepositoryImpl(
            weatherService = get(),
            weatherDao = get()
        )
    }

    single{
        get<WeatherDataBase>().getWeatherDao()
    }

    single<WeatherService>{
        create(retrofit = get())
    }
}
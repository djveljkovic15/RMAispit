package rs.raf.ispit.djordje_veljkovic_rn4615.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.Resource
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.Weather
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.WeatherFilter
import rs.raf.ispit.djordje_veljkovic_rn4615.data.repositories.weather.WeatherRepository
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.contract.WeatherContract
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.states.WeatherState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class WeatherViewModel(
    private val weatherRepository: WeatherRepository)
    :ViewModel(), WeatherContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val weatherState: MutableLiveData<WeatherState> = MutableLiveData()

//    private val publishSubject: PublishSubject<WeatherFilter> = PublishSubject.create()  //WeatherFilter
//
//    init{
//        val errorMessage = "Error happened while fetching data from db"
//        val subscription = publishSubject
//            .debounce(200, TimeUnit.MILLISECONDS)
//            .distinctUntilChanged()
//            .switchMap {
//                Timber.e("RVM switch mapa weather $it")
//                weatherRepository
//                    .filterWeather(it.city)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Error in publish subject")
//                        Timber.e(it)
//                    }
//            }
//            .subscribe(
//                {
//                    Timber.e("RVM success weather $it")
//                    weatherState.value = WeatherState.Success(it)
//                },
//                {
//                    weatherState.value = WeatherState.Error(errorMessage)
//                    Timber.e(it)
//                }
//            )
//
//        subscriptions.add(subscription)
//    }
    override fun fetchWeather(city: String, days: String) {
        val errorMessage = "Error while fetching weather from server!"

        val subscription = weatherRepository
            .fetchWeather(city, days)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it){
                        is Resource.Loading -> weatherState.value = WeatherState.Loading
                        is Resource.Success -> weatherState.value = WeatherState.DataFetched
                        is Resource.Error -> weatherState.value = WeatherState.Error(errorMessage)
                    }
                },
                {
                    weatherState.value = WeatherState.Error(errorMessage)
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getWeather(city: String) {
        val errorMessage = "Error while getting data from database!"
        val subscription = weatherRepository
            .getWeather(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    weatherState.value = WeatherState.Success(it)
                },{
                    weatherState.value = WeatherState.Error(errorMessage)
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
//
//    override fun filterWeather(city: String){
//        Timber.e("FILTER TIME BAAAABE!")
//        publishSubject.onNext(
//            WeatherFilter(
//                city
//            )
//        )
//    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}
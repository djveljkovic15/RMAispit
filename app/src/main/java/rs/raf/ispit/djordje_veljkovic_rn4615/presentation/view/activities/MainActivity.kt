package rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.activities


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.ispit.djordje_veljkovic_rn4615.R
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.contract.WeatherContract
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.recycler.adapter.WeatherAdapter
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.recycler.diff.WeatherDiffCallback
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.states.WeatherState
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.viewmodel.WeatherViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val weatherViewModel: WeatherContract.ViewModel by viewModel<WeatherViewModel>()

    lateinit var weatherAdapter: WeatherAdapter

    companion object{
        val MESSAGE_KEY = "666"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    private fun init() {
        initSearchButton()
        initRecyclerView()
        initObservers()
//        initFetchTest()
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
    private fun initSearchButton() {
        upperViewPagerButton.setOnClickListener {
            //            val intent = Intent(this, MapsActivity::class.java)
//            startActivity(intent)

            if (upperViewPagerCityName.text.toString() == "") {
                Toast.makeText(this, "You must enter city name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isNetworkAvailable(this)) {
                weatherViewModel.getWeather(upperViewPagerCityName.text.toString())

            } else {
                if (upperViewPagerDays.text.toString() == "") {
                    Toast.makeText(this, "You can only ask for 1 to 10 days!", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                if (upperViewPagerDays.text.toString().toInt() !in 1..10) {
                    Toast.makeText(this, "You can only ask for 1 to 10 days!", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                Timber.e("InitSearchButton: %s", upperViewPagerCityName.text.toString())
                weatherViewModel.fetchWeather(
                    upperViewPagerCityName.text.toString(),
                    upperViewPagerDays.text.toString()
                )
//            weatherViewModel.getWeather(upperViewPagerCityName.text.toString())
            }
        }
    }

    private fun initFetchTest() {
        weatherViewModel.fetchWeather("Belgrade","2")
    }

    private fun initObservers() {
        weatherViewModel.weatherState.observe(this, Observer {
            Timber.e("Mejn obzerver , power ON! ::%s", it.toString())
            renderState(it)
        })
//        weatherViewModel.fetchWeather()
//        weatherViewModel.getWeather()
    }

    private fun initRecyclerView() {
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        weatherAdapter = WeatherAdapter(WeatherDiffCallback()) {

            // Mogu i da stavim da prosledjujem samo ID, pa da u MapsActivity radim getWeatherByID jer mi je u bazi vec
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra(MESSAGE_KEY, it)
            startActivity(intent)
        }
        mainRecyclerView.adapter = weatherAdapter
    }

    private fun renderState(state: WeatherState) {
        when (state) {
            is WeatherState.Success -> {
                weatherAdapter.submitList(state.weather)
            }
            is WeatherState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is WeatherState.DataFetched -> {
                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
        }
    }

}
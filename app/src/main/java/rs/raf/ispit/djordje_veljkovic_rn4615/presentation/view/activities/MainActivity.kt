package rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.activities


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

    private fun initSearchButton() {
        upperViewPagerButton.setOnClickListener {
            Timber.e("UROSEEE: %s",upperViewPagerCityName.toString() )
            weatherViewModel.fetchWeather(upperViewPagerCityName.text.toString(), upperViewPagerDays.text.toString())
            weatherViewModel.getWeather(upperViewPagerCityName.text.toString())
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
        weatherAdapter = WeatherAdapter(WeatherDiffCallback())
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
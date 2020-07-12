package rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import rs.raf.ispit.djordje_veljkovic_rn4615.R
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.Weather
import timber.log.Timber

class MapsActivity : AppCompatActivity(R.layout.activity_maps), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()


    }

    private fun init() {
        initActionBar()

        initMap()

    }

    private fun initMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initActionBar() {
        val actionbar = supportActionBar
        actionbar!!.title = " "
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val weather: Weather?

        intent.let {
            weather = it.getParcelableExtra(MainActivity.MESSAGE_KEY)

            activityMapsInfoCity.text = weather.city + ", " + weather.date
            activityMapsInfoMaxTemp.text = "MAXIMUM DAILY TEMPERATURE: " +weather.max_temp
            activityMapsInfoMinTemp.text = "MINIMUM DAILY TEMPERATURE: "+weather.min_temp
            activityMapsInfoWind.text = "WIND SPEED: "+ weather.wind
            activityMapsInfoUV.text = "UV RADIATION: "+weather.uv

            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.uiSettings.isZoomControlsEnabled= true



            mMap.addMarker(MarkerOptions().position(
                LatLng(weather.latitude.toDouble(),weather.longitude.toDouble())).title("Marker in "+weather.city))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(
                LatLng(weather.latitude.toDouble(),weather.longitude.toDouble())))

            mMap.setMinZoomPreference(7f)

        }

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}

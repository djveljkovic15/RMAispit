package rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rs.raf.ispit.djordje_veljkovic_rn4615.presentation.view.activities.MainActivity

class SplashScreen : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initMainActivity()
    }

    private fun initMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
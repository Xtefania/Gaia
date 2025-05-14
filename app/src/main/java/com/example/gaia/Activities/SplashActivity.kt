package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Borrar la base de datos (para pruebas)
        deleteDatabase("gaia.db");

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainHActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)

    }

}
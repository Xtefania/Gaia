package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R
//import com.example.gaia.Activities.InfoProductActivity


class MainHActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonIniciarSesion = findViewById<Button>(R.id.btn_inicio_sesion)

        botonIniciarSesion.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
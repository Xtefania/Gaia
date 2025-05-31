package com.example.gaia.Activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.gaia.R
import com.example.gaia.db.DbHelper

class MainHActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Opcional: habilitar modo edge-to-edge para pantalla completa
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.apply {
                // Por ejemplo, iconos de barra de estado en color oscuro
                setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        }

        setContentView(R.layout.activity_main)

        // Navegación

        // Botón iniciar sesión
        val btnLogin1 = findViewById<Button>(R.id.btn_inicio_sesion1)
        btnLogin1.setOnClickListener {
            val dbHelper = DbHelper(this@MainHActivity)
            val db = dbHelper.writableDatabase

            if (db != null) {
                // Toast.makeText(this, "DB creada correctamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error al crear la DB", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón registrar
        val btnRegister = findViewById<TextView>(R.id.tv_registrese)
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

            // Botones dentro del registro (¿estás seguro que deben ir aquí? Usualmente van en otra actividad)
            val btnCarrito = findViewById<ImageButton>(R.id.btnCarrito)
            val btnUbicacion = findViewById<ImageButton>(R.id.botonubicacion)
            val btnMenu = findViewById<ImageButton>(R.id.btn_menu)

            btnCarrito.setOnClickListener {
                val intent = Intent(this, CarritoActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            btnUbicacion.setOnClickListener {
                val intent = Intent(this, UbicacionActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            btnMenu.setOnClickListener {
                val intent = Intent(this, CategoriesActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }
    }
}

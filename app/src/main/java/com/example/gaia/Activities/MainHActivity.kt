package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R
import com.example.gaia.db.DbHelper
import android.widget.Toast

class MainHActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Navegación

        //  - Vista iniciar sesión
        val btnLogin1 = findViewById<Button>(R.id.btn_inicio_sesion1)
        btnLogin1.setOnClickListener {

            //  - Crear DB
            val dbHelper = DbHelper(this@MainHActivity)
            val db = dbHelper.writableDatabase

            if (db != null) {
//                Toast.makeText(this, "DB creada correctamente", Toast.LENGTH_SHORT).show();

                //  - Vista iniciar sesión
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error al crear la DB", Toast.LENGTH_SHORT).show();
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        // - Vista registrar
        val btnRegister = findViewById<TextView>(R.id.tv_registrese)
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

            /// ana botones de activity base aqui inicia
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
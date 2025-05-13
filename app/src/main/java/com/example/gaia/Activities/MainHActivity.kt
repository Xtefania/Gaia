package com.example.gaia.Activities
import com.example.gaia.db.DbHelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R


class MainHActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Navegación

        //  - Vista crear DB
        val btnLogin1 = findViewById<Button>(R.id.btn_inicio_sesion1)
        btnLogin1.setOnClickListener {
            val dbHelper = DbHelper(this@MainHActivity)
            val db = dbHelper.writableDatabase

            if (db != null) {
                Toast.makeText(this, "Base de datos creada correctamente", Toast.LENGTH_SHORT).show();

                //  - Vista iniciar sesión
                val intent = Intent(this, LoginActivity::class.java)
                 startActivity(intent)
            } else {
                Toast.makeText(this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
            }
        }

        // - Vista registrar
        val btnRegister = findViewById<TextView>(R.id.tv_registrese)
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
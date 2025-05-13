package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {

        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        // Navegación

        //  - Vista recuperar contraseña
        val tvPassword = findViewById<TextView>(R.id.tv_contrasena_olv)
        tvPassword.setOnClickListener {
            val intent = Intent(this, OlvidoContrasenaActivity::class.java)
            startActivity(intent)
        }

        //  - Vista categorias de productos
        val btnLogin2 = findViewById<Button>(R.id.btn_inicio_sesion2)
        btnLogin2.setOnClickListener {
            val intent = Intent(this, CategoriesActivity::class.java)
            startActivity(intent)
        }

        // - Vista registrar
        val btnRegister = findViewById<TextView>(R.id.tv_registrese)
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
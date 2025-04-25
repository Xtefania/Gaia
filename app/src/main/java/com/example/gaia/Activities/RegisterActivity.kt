package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R

class RegisterActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createaccount)

        // Navegación

        //  - Vista iniciar sesión
        val btnRegister = findViewById<Button>(R.id.btn_register)
        btnRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
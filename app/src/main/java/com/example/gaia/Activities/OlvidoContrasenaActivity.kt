package com.example.gaia.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R
import android.widget.EditText
import android.widget.Button
import android.content.Context
import android.widget.Toast
import org.json.JSONObject

class OlvidoContrasenaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvido_contrasena)

        val emailInput = findViewById<EditText>(R.id.et_campo_correo)
        val btnRecuperar = findViewById<Button>(R.id.bt_recuperarContr)

        btnRecuperar.setOnClickListener {
            val emailIngresado = emailInput.text.toString().trim()

            if (emailIngresado.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa tu correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("UsuariosApp", Context.MODE_PRIVATE)
            val userDataStr = prefs.getString(emailIngresado, null)

            if (userDataStr != null) {
                val userData = JSONObject(userDataStr)
                val password = userData.optString("password", null)

                if (password != null) {
                    Toast.makeText(this, "Tu contraseña es: $password", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "No se encontró una contraseña asociada", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Correo no registrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
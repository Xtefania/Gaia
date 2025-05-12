package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.db.DbUsers

class LoginActivity : AppCompatActivity() {
    private lateinit var txtNombre: EditText
    private lateinit var txtRol: EditText

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

        val btnLogin2 = findViewById<Button>(R.id.btn_inicio_sesion2)
        txtNombre = findViewById(R.id.et_campo_correo)
        txtRol = findViewById(R.id.et_campo_contrasena)

        btnLogin2.setOnClickListener {
            val dbUser = DbUsers(this)
            val id = dbUser.insertUser(
                txtNombre.text.toString(), txtRol.text.toString()
            )

            if (id > 0) {
                Toast.makeText(this, "Usuario insertado con ID: $id", Toast.LENGTH_SHORT).show();
                cleanFields();
            } else {
                Toast.makeText(this, "Error al insertar usuario", Toast.LENGTH_SHORT).show()
            }

            //  - Vista categorias de productos
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

    private fun cleanFields() {
        txtNombre.setText("")
        txtRol.setText("")
    }

}
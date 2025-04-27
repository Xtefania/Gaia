package com.example.gaia.Activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

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

        // Lógica campo tipo fecha
        val editTextFechaNacimiento = findViewById<EditText>(R.id.editTextFechaNacimiento)

        editTextFechaNacimiento.setOnClickListener {
            val calendario = Calendar.getInstance()
            val año = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker =
                DatePickerDialog(this, { _, añoSeleccionado, mesSeleccionado, diaSeleccionado ->
                    val fecha = "$diaSeleccionado/${mesSeleccionado + 1}/$añoSeleccionado"
                    editTextFechaNacimiento.setText(fecha)
                }, año, mes, dia)

            datePicker.show()
        }

    }
}
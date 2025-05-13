package com.example.gaia.Activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.HexFormat

class RegisterActivity : AppCompatActivity() {

    // FALTA FOTO DE PERFIL -----------------

    // Declaración de variables
    private lateinit var nombreEditText: EditText
    private lateinit var apellidoEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repetirPasswordEditText: EditText
    private lateinit var telefonoEditText: EditText
    private lateinit var fechaNacimientoEditText: EditText
    private lateinit var generoEditText: EditText
    private lateinit var crearCuentaButton: Button



    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createaccount)

        // Inicialización de variables
        nombreEditText = findViewById(R.id.editTextNombre)
        apellidoEditText = findViewById(R.id.editTextLastName)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        repetirPasswordEditText = findViewById(R.id.editTextConfirmPassword)
        telefonoEditText = findViewById(R.id.editTextTelefono)
        fechaNacimientoEditText = findViewById(R.id.editTextFechaNacimiento)
        generoEditText = findViewById(R.id.editTextGender)
        crearCuentaButton = findViewById(R.id.btn_register)

        crearCuentaButton.setOnClickListener { registrarUsuario() }


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

    private fun registrarUsuario() {
        val nombre = nombreEditText.text.toString().trim() // trim() elimina espacios al principio y al final
        val apellido = apellidoEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val repetirPassword = repetirPasswordEditText.text.toString().trim()
        val telefono = telefonoEditText.text.toString().trim()
        val fechaNacimiento = fechaNacimientoEditText.text.toString().trim()
        val genero = generoEditText.text.toString().trim()

        // Validación
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty() ||
            repetirPassword.isEmpty() || telefono.isEmpty() || fechaNacimiento.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Validar formato del correo electrónico
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
            return
        }
       
        if (password != repetirPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        if (!esPasswordValida(password)) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial", Toast.LENGTH_LONG).show()
            return
        }


            // SharedPreferences
        val sharedPreferences = getSharedPreferences("UsuariosApp", MODE_PRIVATE)
        if (sharedPreferences.contains(email)) {
            Toast.makeText(this, "Ya existe un usuario con este correo", Toast.LENGTH_SHORT).show()
            return
        }

        val editor = sharedPreferences.edit()
        editor.putString("nombre", nombre)
        editor.putString("apellido", apellido)
        editor.putString("email", email)
        editor.putString("password", hashearContrasena(password))
        editor.putString("telefono", telefono)
        editor.putString("fechaNacimiento", fechaNacimiento)
        editor.putString("genero", genero)
        editor.apply()

        Toast.makeText(this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Validar formato Contraseña
    private fun esPasswordValida(password: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}\$")
        return regex.matches(password)
    }

    // Función hashearContrasena()
    fun hashearContrasena(contrasena: String): String? {
        try {
            val digest = MessageDigest.getInstance("SHA-256")
            val bytesHash = digest.digest(contrasena.toByteArray())

            // Convertir el array de bytes a una cadena hexadecimal
            val constructorHexadecimal = StringBuilder()
            for (b in bytesHash) {
                constructorHexadecimal.append(String.format("%02x", b))
            }
            return constructorHexadecimal.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            return null
        }
    }
}
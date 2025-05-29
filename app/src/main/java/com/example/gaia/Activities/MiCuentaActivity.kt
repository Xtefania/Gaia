package com.example.gaia.Activities

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject

class MiCuentaActivity : AppCompatActivity() {

    private var photoUri: Uri? = null

    private lateinit var cameraLauncher: ActivityResultLauncher<Uri>
    private lateinit var galleryLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_cuenta)

        // Inicializar lanzadores
        setupLaunchers()

        val prefs = getSharedPreferences("UsuariosApp", Context.MODE_PRIVATE)
        val emailActual = prefs.getString("usuario_actual", null)

        if (emailActual == null) {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val usuarioStr = prefs.getString(emailActual, null)
        if (usuarioStr == null) {
            Toast.makeText(this, "Datos no encontrados", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val usuario = JSONObject(usuarioStr)

        // Cargar datos en los campos editables (TextInputEditText)
        findViewById<TextInputEditText>(R.id.editTextNombre).setText(usuario.getString("nombre"))
        findViewById<TextInputEditText>(R.id.editTextLastName).setText(usuario.getString("apellido"))
        findViewById<TextInputEditText>(R.id.editTextEmail).setText(usuario.getString("email"))
        findViewById<TextInputEditText>(R.id.editTextTelefono).setText(usuario.getString("telefono"))
        findViewById<TextInputEditText>(R.id.editTextFechaNacimiento).setText(usuario.getString("fechaNacimiento"))
        findViewById<TextInputEditText>(R.id.editTextGender).setText(usuario.getString("genero"))

        // Cargar foto si existe
        val uriFoto = usuario.optString("fotoPerfilUri", "")
        if (uriFoto.isNotEmpty()) {
            try {
                findViewById<ImageView>(R.id.iv_AgregarFoto).setImageURI(Uri.parse(uriFoto))
            } catch (e: SecurityException) {
                e.printStackTrace()
                Toast.makeText(this, "No se puede acceder a la imagen de perfil", Toast.LENGTH_SHORT).show()
            }
        }

        // Click para cambiar foto (galería o cámara)
        findViewById<ImageView>(R.id.iv_AgregarFoto).setOnClickListener {
            mostrarOpcionesFoto()
        }

        // Botón guardar cambios
        findViewById<Button>(R.id.btn_guardar).setOnClickListener {
            guardarCambios(emailActual)
        }
    }

    private fun setupLaunchers() {
        // Lanzador para tomar foto con cámara
        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                photoUri?.let {
                    findViewById<ImageView>(R.id.iv_AgregarFoto).setImageURI(it)
                }
            }
        }

        // Lanzador para seleccionar imagen de galería
        galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                photoUri = it
                findViewById<ImageView>(R.id.iv_AgregarFoto).setImageURI(it)
            }
        }
    }

    private fun mostrarOpcionesFoto() {
        // Aquí puedes mostrar un diálogo para elegir entre cámara o galería.
        // Por simplicidad, por ejemplo, abrimos galería directamente:
        galleryLauncher.launch("image/*")
    }

    private fun guardarCambios(emailActual: String) {
        val prefs = getSharedPreferences("UsuariosApp", Context.MODE_PRIVATE)
        val usuarioStr = prefs.getString(emailActual, null)
        if (usuarioStr == null) {
            Toast.makeText(this, "No se puede guardar. Datos no encontrados", Toast.LENGTH_SHORT).show()
            return
        }

        val usuario = JSONObject(usuarioStr)

        val nuevoNombre = findViewById<TextInputEditText>(R.id.editTextNombre).text.toString()
        val nuevoApellido = findViewById<TextInputEditText>(R.id.editTextLastName).text.toString()
        val nuevoEmail = findViewById<TextInputEditText>(R.id.editTextEmail).text.toString()
        val nuevoTelefono = findViewById<TextInputEditText>(R.id.editTextTelefono).text.toString()
        val nuevaFecha = findViewById<TextInputEditText>(R.id.editTextFechaNacimiento).text.toString()
        val nuevoGenero = findViewById<TextInputEditText>(R.id.editTextGender).text.toString()
        val nuevaFotoUri = photoUri?.toString() ?: usuario.optString("fotoPerfilUri", "")

        usuario.put("nombre", nuevoNombre)
        usuario.put("apellido", nuevoApellido)
        usuario.put("email", nuevoEmail)
        usuario.put("telefono", nuevoTelefono)
        usuario.put("fechaNacimiento", nuevaFecha)
        usuario.put("genero", nuevoGenero)
        usuario.put("fotoPerfilUri", nuevaFotoUri)

        // Guardar JSON actualizado en SharedPreferences
        prefs.edit().putString(emailActual, usuario.toString()).apply()

        Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show()
    }
}

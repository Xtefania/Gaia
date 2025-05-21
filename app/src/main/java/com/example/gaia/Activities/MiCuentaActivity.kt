package com.example.gaia.Activities

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R
import org.json.JSONObject
import android.Manifest
import android.content.Intent
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.material.textfield.TextInputEditText


class MiCuentaActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_cuenta)


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

        // Referencias a las vistas
        findViewById<TextView>(R.id.editTextNombre).text = "${usuario.getString("nombre")}"
        findViewById<TextView>(R.id.editTextLastName).text = "${usuario.getString("apellido")}"
        findViewById<TextView>(R.id.editTextEmail).text = "${usuario.getString("email")}"
        findViewById<TextView>(R.id.editTextTelefono).text = "${usuario.getString("telefono")}"
        findViewById<TextView>(R.id.editTextFechaNacimiento).text = "${usuario.getString("fechaNacimiento")}"
        findViewById<TextView>(R.id.editTextGender).text = "${usuario.getString("genero")}"

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
        }

        // Imagen de perfil
        val uriFoto = usuario.optString("fotoPerfilUri", "")
        if (uriFoto.isNotEmpty()) {
            try {
                findViewById<ImageView>(R.id.iv_AgregarFoto).setImageURI(Uri.parse(uriFoto))
            } catch (e: SecurityException) {
                e.printStackTrace()
                Toast.makeText(this, "No se puede acceder a la imagen de perfil", Toast.LENGTH_SHORT).show()
            }
        }


        // Update
        val btnEditar = findViewById<Button>(R.id.btn_guardar)

        btnEditar.setOnClickListener {
            val prefs = getSharedPreferences("UsuariosApp", Context.MODE_PRIVATE)
            val emailActual = prefs.getString("usuario_actual", null)
            val usuarioStr = prefs.getString(emailActual, null)

            if (usuarioStr != null && emailActual != null) {
                val usuario = JSONObject(usuarioStr)
                val contrasenaActual = usuario.optString("contrasena", "")

                // Obtener nuevos valores desde los campos
                val nuevoNombre = findViewById<TextInputEditText>(R.id.editTextNombre).text.toString()
                val nuevoApellido = findViewById<TextInputEditText>(R.id.editTextLastName).text.toString()
                val nuevoEmail = findViewById<TextInputEditText>(R.id.editTextEmail).text.toString()
                val nuevoTelefono = findViewById<TextInputEditText>(R.id.editTextTelefono).text.toString()
                val nuevaFecha = findViewById<TextInputEditText>(R.id.editTextFechaNacimiento).text.toString()
                val nuevoGenero = findViewById<TextInputEditText>(R.id.editTextGender).text.toString()
                val nuevaFotoUri = selectedImageUri?.toString() ?: usuario.optString("fotoPerfilUri", "")

                // Actualizar campos
                usuario.put("nombre", nuevoNombre)
                usuario.put("apellido", nuevoApellido)
                usuario.put("email", nuevoEmail)
                usuario.put("telefono", nuevoTelefono)
                usuario.put("fechaNacimiento", nuevaFecha)
                usuario.put("genero", nuevoGenero)
                usuario.put("fotoPerfilUri", nuevaFotoUri)

                usuario.put("contrasena", contrasenaActual)

                // Guardar bajo nueva clave si cambia el email
                val editor = prefs.edit()
                editor.remove(emailActual) // elimina el viejo
                editor.putString(nuevoEmail, usuario.toString())
                editor.putString("usuario_actual", nuevoEmail)
                editor.apply()

                Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show()
                recreate() // refresca la vista
            }
        }


        val ivFoto = findViewById<ImageView>(R.id.iv_AgregarFoto)

        ivFoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }


        // Delete
        val btnEliminar = findViewById<Button>(R.id.btn_eliminar)
        btnEliminar.setOnClickListener {
            val prefs = getSharedPreferences("UsuariosApp", Context.MODE_PRIVATE)
            val emailActual = prefs.getString("usuario_actual", null)

            if (emailActual != null) {
                prefs.edit().remove(emailActual).remove("usuario_actual").apply()
                Toast.makeText(this, "Cuenta eliminada", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Borra el backstack
                startActivity(intent)
                finish()
            }
        }




    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 101 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            recreate() // vuelve a cargar la Activity con permisos concedidos
        } else {
            Toast.makeText(this, "Permiso", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data
            findViewById<ImageView>(R.id.iv_AgregarFoto).setImageURI(selectedImageUri)
        }
    }


}
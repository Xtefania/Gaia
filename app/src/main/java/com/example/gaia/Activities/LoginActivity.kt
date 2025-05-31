package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.util.Log
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.EditText
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.tasks.Task
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException


class LoginActivity : AppCompatActivity() {

    // Inicio de sesión con Google
    private lateinit var btnGoogle: Button
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 123
    private val TAG = "GoogleSignIn"

    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {

        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        // Configurar Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        // Crear el cliente de Google SignIn
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        btnGoogle = findViewById(R.id.btn_google)

        btnGoogle.setOnClickListener {
            signIn()
        }

/*
        //- Vista categorias de productos - CON USUARIO
        val btnLogin2 = findViewById<Button>(R.id.btn_inicio_sesion2)
        val emailEditText = findViewById<EditText>(R.id.et_campo_correo)

        btnLogin2.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa tu correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("UsuariosApp", MODE_PRIVATE)
            val userDataStr = prefs.getString(email, null)

            if (userDataStr != null) {
                // Usuario existe, guardar como usuario actual
                prefs.edit().putString("usuario_actual", email).apply()

                // Ir a Categorías
                val intent = Intent(this, DisenoFragmActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Correo no registrado", Toast.LENGTH_SHORT).show()
            }
        }*/






        //  - Vista recuperar contraseña
        val tvPassword = findViewById<TextView>(R.id.tv_contrasena_olv)
        tvPassword.setOnClickListener {
            val intent = Intent(this, OlvidoContrasenaActivity::class.java)
            startActivity(intent)
        }



        //- Vista categorias de productos - SIN USUARIO
        val btnLogin2 = findViewById<Button>(R.id.btn_inicio_sesion2)
        btnLogin2.setOnClickListener {
            val intent = Intent(this, DisenoFragmActivity::class.java)
            startActivity(intent)
        }

        // - Vista registrar
        val btnRegister = findViewById<TextView>(R.id.tv_registrese)
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Inicio de sesión exitoso
            Log.d(TAG, "signInSuccess: ${account.email}")
            Toast.makeText(this, "Bienvenido ${account.displayName}", Toast.LENGTH_SHORT).show()

            // - Vista categorias de productos desde Botón Google
            intent = Intent(this, DisenoFragmActivity::class.java)
            intent.putExtra("USER_EMAIL", account.email)
            intent.putExtra("USER_NAME", account.displayName)
            startActivity(intent)

        } catch (e: ApiException) {
            // Error en el inicio de sesión
            Toast.makeText(this, "mensaje", Toast.LENGTH_SHORT).show()
        }
    }
}
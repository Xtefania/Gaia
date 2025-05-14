package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.content.Context
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import com.example.gaia.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class CategoriesActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        // Navegación

        //  - Vista lista productos de la categoria
        //  - Lógica principal
//        val btnListProductsCategory = findViewById<TextView>(R.id.textView2)
//        btnListProductsCategory.setOnClickListener {
//            val intent = Intent(this, ListProductActivity::class.java)
//            startActivity(intent)
//        }

        //  - Lógica necesaria para el Mockup
        //  - Arreglo de textos de las categorias
        val buttonIds = listOf(
            R.id.textView2,
            R.id.textView3,
            R.id.textView4,
            R.id.textView5,
            R.id.textView6,
            R.id.textView7,
            R.id.textView9,
            R.id.textView10,
            R.id.textView11,
            R.id.textView8,
        )

        //  - Iterar sobre el arreglo y aplicar la función de click (redireccionamiento)
        for (id in buttonIds) {
            val button = findViewById<TextView>(id)
            button.setOnClickListener {
                val intent = Intent(this, ListProductActivity::class.java)
                startActivity(intent)
            }
        }

        //  - Vista carrito
        val carrito = findViewById<ImageView>(R.id.imageView6)
        carrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        //  - Vista ubicación
        val btnUbicacion = findViewById<ImageView>(R.id.imageView4)
        btnUbicacion.setOnClickListener {
            val intent = Intent(this, UbicacionActivity::class.java)
            startActivity(intent)
        }

        val btnCerrarSesion = findViewById<Button>(R.id.btn_cerrarsesion)
        btnCerrarSesion.setOnClickListener {
            cerrarSesion(this)
        }
    }

    // Función de cierre de sesión
    private fun cerrarSesion(context: Context) {
        // Limpiar SharedPreferences
        val prefs: SharedPreferences = context.getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.clear()
        editor.apply()

        // Cerrar sesión de Google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

        googleSignInClient.signOut().addOnCompleteListener {
            // Redirigir al MainActivity
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }
}
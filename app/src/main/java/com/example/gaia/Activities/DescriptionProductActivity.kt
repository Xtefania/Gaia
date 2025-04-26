package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class DescriptionProductActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_product)

        // Navegación

        //  - Vista info productos
        val infoProduct = findViewById<ImageView>(R.id.btn_minus_descripcion)
        infoProduct.setOnClickListener {
            val intent = Intent(this, InfoProductActivity::class.java)
            startActivity(intent)
        }

        //  - Vista detalles intredientes
        val cardProduct = findViewById<ImageView>(R.id.btn_plus_members)
        cardProduct.setOnClickListener {
            val intent = Intent(this, MembersProductActivity::class.java)
            startActivity(intent)
        }

        //  - Vista carrito
        //  - Desde icono
        val btnCarrito = findViewById<ImageView>(R.id.btn_cart)
        btnCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        //  - Desde btn Añadir al carrito
        val btnAnadirCarrito = findViewById<Button>(R.id.btn_anadir_carrito)
        btnAnadirCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        //  - Vista ubicación
        val btnUbicacion = findViewById<ImageView>(R.id.btn_location)
        btnUbicacion.setOnClickListener {
            val intent = Intent(this, UbicacionActivity::class.java)
            startActivity(intent)
        }
    }
}
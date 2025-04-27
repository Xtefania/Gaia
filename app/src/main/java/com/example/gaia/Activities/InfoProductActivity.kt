package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.gaia.R

class InfoProductActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_product)

        // Navegación

        //  - Vista ubicación
        val btnUbicacion = findViewById<ImageView>(R.id.btn_location)
        btnUbicacion.setOnClickListener {
            val intent = Intent(this, UbicacionActivity::class.java)
            startActivity(intent)
        }

        //  - Vista carrito
        //  - Desde icono
        val carrito = findViewById<ImageView>(R.id.btn_cart)
        carrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        //  - Desde btn Añadir al carrito
        val btnAnadirCarrito = findViewById<Button>(R.id.btn_anadir_carrito)
        btnAnadirCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        //  - Vista descripción producto
        val cardDescription = findViewById<ConstraintLayout>(R.id.card_description)
        cardDescription.setOnClickListener {
            val intent = Intent(this, DescriptionProductActivity::class.java)
            startActivity(intent)
        }

        //  - Vista ingredientes producto
        val cardMembers = findViewById<ConstraintLayout>(R.id.card_members)
        cardMembers.setOnClickListener {
            val intent = Intent(this, IngredientsProductActivity::class.java)
            startActivity(intent)
        }
    }
}


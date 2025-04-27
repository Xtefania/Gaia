package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity

class IngredientsProductActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients_product)

        // Navegación

        //  - Vista ubicación
        val btnUbicacion = findViewById<ImageView>(R.id.btn_location)
        btnUbicacion.setOnClickListener {
            val intent = Intent(this, UbicacionActivity::class.java)
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

        //  - Vista descripción producto
        val descriptionProduct = findViewById<ImageView>(R.id.btn_minus_members)
        descriptionProduct.setOnClickListener {
            val intent = Intent(this, DescriptionProductActivity::class.java)
            startActivity(intent)
        }
    }
}
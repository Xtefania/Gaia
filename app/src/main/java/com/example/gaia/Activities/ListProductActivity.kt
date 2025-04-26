package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ListProductActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragancias)

        // Navegación

        //  - Vista detalle del producto
        //  - Lógica principal
//        val cardProduct = findViewById<ConstraintLayout>(R.id.cl_producto_1)
//        cardProduct.setOnClickListener {
//            val intent = Intent(this, InfoProductActivity::class.java)
//            startActivity(intent)
//        }

        //  - Lógica necesaria para el Mockup
        //  - Arreglo de cards productos
        val cardsIds = listOf(
            R.id.cl_producto_1,
            R.id.cl_producto_2,
            R.id.cl_producto_3,
            R.id.cl_producto_4,
        )

        //  - Iterar sobre el arreglo y aplicar la función de click (redireccionamiento)
        for (id in cardsIds) {
            val card = findViewById<ConstraintLayout>(id)
            card.setOnClickListener {
                val intent = Intent(this, InfoProductActivity::class.java)
                startActivity(intent)
            }
        }


        //  - Vista carrito
        val carrito = findViewById<ImageView>(R.id.btn_cart)
        carrito.setOnClickListener {
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
package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class UbicacionActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Navegación

//        //  - Vista detalle del producto
//        val cardProduct = findViewById<ConstraintLayout>(R.id.cl_producto_1)
//        cardProduct.setOnClickListener {
//            val intent = Intent(this, InfoProductActivity::class.java)
//            startActivity(intent)
//        }
//
//        //  - Vista carrito
//        val carrito = findViewById<ImageView>(R.id.btn_cart)
//        carrito.setOnClickListener {
//            val intent = Intent(this, CarritoActivity::class.java)
//            startActivity(intent)
//        }
    }
}
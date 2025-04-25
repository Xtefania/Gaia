package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class CarritoActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        // Navegación

        //  - Vista detalle descripción
//        val descriptionProduct = findViewById<ImageView>(R.id.btn_minus_members)
//        descriptionProduct.setOnClickListener {
//            val intent = Intent(this, DescriptionProductActivity::class.java)
//            startActivity(intent)
//        }
    }
}
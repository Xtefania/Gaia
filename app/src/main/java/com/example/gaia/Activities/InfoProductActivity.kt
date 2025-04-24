package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity

class InfoProductActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_product)

        // Navegación

        //  - Vista descripción
        val cardProduct = findViewById<ImageView>(R.id.btn_plus_descripcion)
        cardProduct.setOnClickListener {
            val intent = Intent(this, DescriptionProductActivity::class.java)
            startActivity(intent)
        }
    }
}
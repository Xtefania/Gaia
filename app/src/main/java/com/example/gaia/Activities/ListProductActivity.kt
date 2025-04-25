package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
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
        val cardProduct = findViewById<ImageView>(R.id.iv_product_1)
        cardProduct.setOnClickListener {
            val intent = Intent(this, InfoProductActivity::class.java)
            startActivity(intent)
        }
    }
}
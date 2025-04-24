package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
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

//        //  - Vista categorias de productos
//        val cardProduct = findViewById<ConstraintLayout>(R.id.card_product)
//        cardProduct.setOnClickListener {
//            val intent = Intent(this, InfoProductActivity::class.java)
//            startActivity(intent)
//        }
    }
}
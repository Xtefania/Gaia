package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class CategoriesActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        // Navegación

        //  - Vista categorias de productos
        val btnListProductsCategory = findViewById<ConstraintLayout>(R.id.cl_category_products_1)
        btnListProductsCategory.setOnClickListener {
            val intent = Intent(this, ListProductActivity::class.java)
            startActivity(intent)
        }

        //  - Vista carrito
        val carrito = findViewById<ImageView>(R.id.btn_cart)
        carrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }
    }
}

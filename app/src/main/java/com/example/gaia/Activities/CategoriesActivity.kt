package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity

class CategoriesActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        // Navegación

        //  - Vista categorias de productos
        val btnShowCategory = findViewById<ImageView>(R.id.btn_show_category)
        btnShowCategory.setOnClickListener {
            val intent = Intent(this, ListProductActivity::class.java)
            startActivity(intent)
        }
    }
}

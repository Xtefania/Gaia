package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
        //  - Vista descripción producto
        val cardDescription = findViewById<ConstraintLayout>(R.id.card_description)
        cardDescription.setOnClickListener {
            val intent = Intent(this, DescriptionProductActivity::class.java)
            startActivity(intent)
        }

        //  - Vista integrantes producto
        val cardMembers = findViewById<ConstraintLayout>(R.id.card_members)
        cardMembers.setOnClickListener {
            val intent = Intent(this, MembersProductActivity::class.java)
            startActivity(intent)
        }

        //  - Vista carrito
        val carrito = findViewById<ImageView>(R.id.btn_cart)
        carrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }
    }

//    // Función para mostrar/ocultar el contenido
//    private fun toggleVisibility(view: View) {
//        view.visibility = if (view.visibility == View.GONE) View.VISIBLE else View.GONE
//    }
}


//package com.example.gaia.Activities
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.ImageView
//import com.example.gaia.R
//import androidx.appcompat.app.AppCompatActivity
//
//package com.example.acordeon
//
//import android.os.Bundle
//import android.view.View
//import com.example.acordeon.databinding.ActivityMainBinding
//
//class InfoProductActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//
////    override fun onCreate(savedInstanceState: Bundle?) {
////
////    }
//
//    private fun toggleVisibility(view: View) {
//        view.visibility = if (view.visibility == View.GONE) View.VISIBLE else View.GONE
//    }
//
//    // Renderización
//    override fun onCreate(savedInstanceState: Bundle?) {
//        // - Vista principal
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_info_product)
//
//
//
//
//
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Alternar visibilidad al hacer clic en "Descripción"
//        binding.titleDescription.setOnClickListener {
//            toggleVisibility(binding.contentDescription)
//        }
//
//        // Alternar visibilidad al hacer clic en "Ingredientes"
//        binding.titleIngredients.setOnClickListener {
//            toggleVisibility(binding.contentIngredients)
//        }
//    }
//}
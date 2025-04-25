package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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

        //  - Vista info productos
        val infoProduct = findViewById<ImageView>(R.id.btn_minus_descripcion)
        infoProduct.setOnClickListener {
            val intent = Intent(this, InfoProductActivity::class.java)
            startActivity(intent)
        }

        //  - Vista detalles intredientes
        val cardProduct = findViewById<ImageView>(R.id.btn_plus_members)
        cardProduct.setOnClickListener {
            val intent = Intent(this, MembersProductActivity::class.java)
            startActivity(intent)
        }

        //  - Vista carrito
        val carrito = findViewById<ImageView>(R.id.btn_cart)
        carrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        // Obtener referencias de los TextView
//        val titleDescription = findViewById<TextView>(R.id.tv_descripcion)
//        val contentDescription = findViewById<TextView>(R.id.contentDescription)
//        // Lógica para alternar visibilidad
//        titleDescription.setOnClickListener {
//            toggleVisibility(contentDescription)
//        }
    }

    // Función para mostrar/ocultar el contenido
//    private fun toggleVisibility(view: View) {
//        view.visibility = if (view.visibility == View.GONE) View.VISIBLE else View.GONE
//    }
}
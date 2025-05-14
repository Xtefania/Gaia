package com.example.gaia.Activities

import android.os.Bundle
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.adapters.ProductoCarritoAdapter
import com.example.gaia.models.ProductoCarrito

class CarritoActivity : AppCompatActivity() {
    // Renderización
    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCarrito)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listaProductos = listOf(
            ProductoCarrito("Desodorante", 64000.0, "desodorante2"),
            ProductoCarrito("Jabón", 32000.0, "jabon1")
        )

        val adapter = ProductoCarritoAdapter(this, listaProductos)
        recyclerView.adapter = adapter


        // Navegación

        //  - Vista detalle descripción
//        val descriptionProduct = findViewById<ImageView>(R.id.btn_minus_members)
//        descriptionProduct.setOnClickListener {
//            val intent = Intent(this, DescriptionProductActivity::class.java)
//            startActivity(intent)
//        }
    }
}
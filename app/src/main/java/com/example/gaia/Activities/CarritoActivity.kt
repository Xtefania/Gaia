package com.example.gaia.Activities

import ProductoCarritoAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.R
import com.example.gaia.db.DbCarrito
import com.example.gaia.models.ProductoCarrito

class CarritoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoCarritoAdapter
    private lateinit var listaProductos: List<ProductoCarrito>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        recyclerView = findViewById(R.id.recyclerViewCarrito)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dbCarrito = DbCarrito(this)
        listaProductos = dbCarrito.obtenerProductosCarrito()

        adapter = ProductoCarritoAdapter(listaProductos, this)
        recyclerView.adapter = adapter
    }
}

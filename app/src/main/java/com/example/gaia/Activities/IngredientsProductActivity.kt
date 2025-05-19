package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.gaia.R
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.db.DbProductos
import com.example.gaia.models.Producto

class IngredientsProductActivity : AppCompatActivity() {

    // Referencias a los elementos de la vista
    private lateinit var tvIngredientes: TextView

    // Variables globales
    private var idProducto = 0
    private var producto: Producto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // - Vista principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients_product)

        initViews()

        idProducto = intent.getIntExtra("ID_PRODUCTO", -1)
        if (idProducto != -1) {
            loadProductData(idProducto)
        } else {
            Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show()
            finish()
        }

        setupListeners()
    }

    /**
     * Inicializa referencias a los elementos del layout
     */
    private fun initViews() {
        tvIngredientes = findViewById(R.id.tv_members_product_t1)
    }

    /**
     * Carga los datos del producto desde la base de datos
     */
    private fun loadProductData(idProducto: Int) {
        val dbProductos = DbProductos(this)
        producto = dbProductos.getProductById(idProducto)

        producto?.let {
            tvIngredientes.text = it.ingredientes
        } ?: run {
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Configura las acciones de los botones
     */
    private fun setupListeners() {
        // Navegación

        //  - Vista ubicación
        val btnUbicacion = findViewById<ImageView>(R.id.btn_location)
        btnUbicacion.setOnClickListener {
            val intent = Intent(this, UbicacionActivity::class.java)
            startActivity(intent)
        }

        //  - Vista carrito
        //  - Desde icono
        val btnCarrito = findViewById<ImageView>(R.id.btn_cart)
        btnCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        //  - Desde btn Añadir al carrito
        val btnAnadirCarrito = findViewById<Button>(R.id.btn_anadir_carrito)
        btnAnadirCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        //  - Vista descripción producto
        val descriptionProduct = findViewById<ImageView>(R.id.btn_minus_members)
        descriptionProduct.setOnClickListener {
            producto?.let {
                val intent = Intent(this, DescriptionProductActivity::class.java)
                intent.putExtra("ID_PRODUCTO", idProducto)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
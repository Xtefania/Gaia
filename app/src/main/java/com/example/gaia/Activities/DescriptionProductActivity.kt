package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R

class DescriptionProductActivity : AppCompatActivity() {

    // Referencias a elementos de la vista
    private lateinit var btnUbicacion: ImageView
    private lateinit var btnCarrito: ImageView
    private lateinit var btnAnadirCarrito: Button
    private lateinit var btnInfoProducto: ImageView
    private lateinit var btnIngredientes: ImageView
    private lateinit var tvDescripcion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_product)

        // Inicializa vistas
        initViews()

        // Muestra la descripción del producto
        loadDescription()

        // Configura los eventos de clic
        setupListeners()
    }

    /**
     * Inicializa referencias a los elementos del layout
     */
    private fun initViews() {
        btnUbicacion = findViewById(R.id.btn_location)
        btnCarrito = findViewById(R.id.btn_cart)
        btnAnadirCarrito = findViewById(R.id.btn_anadir_carrito)
        btnInfoProducto = findViewById(R.id.btn_minus_descripcion)
        btnIngredientes = findViewById(R.id.btn_plus_members)
        tvDescripcion = findViewById(R.id.tv_description_product_p1)
    }

    /**
     * Configura los listeners para los botones de navegación
     */
    private fun setupListeners() {
        // Navegar a ubicación
        btnUbicacion.setOnClickListener {
            startActivity(Intent(this, UbicacionActivity::class.java))
        }

        // Ir al carrito desde el ícono
        btnCarrito.setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }

        // Ir al carrito desde el botón
        btnAnadirCarrito.setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }

        // Volver a la vista de información del producto
        btnInfoProducto.setOnClickListener {
            startActivity(Intent(this, InfoProductActivity::class.java))
        }

        // Ir a ingredientes del producto
        btnIngredientes.setOnClickListener {
            startActivity(Intent(this, IngredientsProductActivity::class.java))
        }
    }

    /**
     * Obtiene y muestra la descripción del producto desde el Intent
     */
    private fun loadDescription() {
        val descripcion = intent.getStringExtra("descripcion")
        tvDescripcion.text = descripcion ?: "Sin descripción disponible"
    }
}

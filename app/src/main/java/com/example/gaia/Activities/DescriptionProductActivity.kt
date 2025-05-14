package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.gaia.R
import com.example.gaia.db.DbProductos
import com.example.gaia.models.Producto

class DescriptionProductActivity : AppCompatActivity() {

    // Referencias a los elementos de la vista
    private lateinit var btnUbicacion: ImageView
    private lateinit var btnCarrito: ImageView
    private lateinit var btnAnadirCarrito: Button
    private lateinit var btnInfoProducto: ImageView
    private lateinit var tvDescripcion: TextView

    // Variables globales
    private var idProducto = 0
    private var producto: Producto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_product)

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
        btnUbicacion = findViewById(R.id.btn_location)
        btnCarrito = findViewById(R.id.btn_cart)
        btnAnadirCarrito = findViewById(R.id.btn_anadir_carrito)
        btnInfoProducto = findViewById(R.id.btn_minus_descripcion)
        tvDescripcion = findViewById(R.id.tv_description_product_p1)
    }

    /**
     * Carga los datos del producto desde la base de datos
     */
    private fun loadProductData(idProducto: Int) {
        val dbProductos = DbProductos(this)
        producto = dbProductos.getProductoById(idProducto)

        producto?.let {
            tvDescripcion.text = it.descripcion
        } ?: run {
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Configura las acciones de los botones
     */
    private fun setupListeners() {
        // Navegar a la actividad de ubicación
        btnUbicacion.setOnClickListener { startActivity(UbicacionActivity::class.java) }

        // Ir al carrito desde el ícono
        btnCarrito.setOnClickListener { startActivity(CarritoActivity::class.java) }

        // Ir al carrito desde el botón
        btnAnadirCarrito.setOnClickListener { startActivity(CarritoActivity::class.java) }

        // Volver a la vista de información del producto
        btnInfoProducto.setOnClickListener { startActivity(InfoProductActivity::class.java) }

        // Ir a ingredientes del producto
        findViewById<ImageView>(R.id.btn_plus_members).setOnClickListener {
            producto?.let {
                val intent = Intent(this, IngredientsProductActivity::class.java)
                intent.putExtra("ID_PRODUCTO", idProducto)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Función de extensión para iniciar actividad
     */
    private fun startActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}

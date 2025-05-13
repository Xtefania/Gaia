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
import com.example.gaia.data.FakeDatabase
import com.example.gaia.models.Producto

class InfoProductActivity : AppCompatActivity() {

    // Referencias a los elementos de la vista
    private lateinit var imageView: ImageView
    private lateinit var tvTitulo: TextView
    private lateinit var tvPrecio: TextView

    // Producto a mostrar (ID fijo por ahora)
    private var producto: Producto? = null
    private val idProducto = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_product)

        // Inicializa vistas
        initViews()

        // Carga y muestra la información del producto
        loadProductData()

        // Configura los listeners para botones y tarjetas
        setupListeners()
    }

    /**
     * Inicializa referencias a los elementos del layout
     */
    private fun initViews() {
        imageView = findViewById(R.id.imagen_producto)
        tvTitulo = findViewById(R.id.tv_titulo_producto)
        tvPrecio = findViewById(R.id.tv_price_product)
    }

    /**
     * Carga los datos del producto desde la base de datos falsa
     * y actualiza la UI si el producto existe
     */
    private fun loadProductData() {
//        val sharedPrefs = getSharedPreferences("gaia_prefs", MODE_PRIVATE)
//        val idProducto = sharedPrefs.getInt("producto_id", -1) // -1 si no existe

        producto = FakeDatabase.obtenerProductoPorId(idProducto)

        if (producto != null) {
            producto?.let {
                it.imagenResId?.let { img -> imageView.setImageResource(img) }
                tvTitulo.text = it.nombre
                tvPrecio.text = it.precio
            }
        } else {
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Configura las acciones al hacer clic en botones e íconos
     */
    private fun setupListeners() {
        // Ir a la pantalla de ubicación
        findViewById<ImageView>(R.id.btn_location).setOnClickListener {
            startActivity(Intent(this, UbicacionActivity::class.java))
        }

        // Ir al carrito desde ícono
        findViewById<ImageView>(R.id.btn_cart).setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }

        // Ir al carrito desde botón "Añadir al carrito"
        findViewById<Button>(R.id.btn_anadir_carrito).setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }

        // Ir a la descripción del producto
        findViewById<ConstraintLayout>(R.id.card_description).setOnClickListener {
            producto?.let {
                val intent = Intent(this, DescriptionProductActivity::class.java)
                intent.putExtra("descripcion", it.descripcion)
                startActivity(intent)
            }
        }

        // Ir a los ingredientes del producto
        findViewById<ConstraintLayout>(R.id.card_members).setOnClickListener {
            startActivity(Intent(this, IngredientsProductActivity::class.java))
        }
    }

    //  - Vista detalle del producto
    //  - Lógica principal
//    val cardProduct = findViewById<ConstraintLayout>(R.id.cl_producto_1)
//    cardProduct.setOnClickListener {
//        val sharedPrefs = getSharedPreferences("gaia_prefs", MODE_PRIVATE)
//        val editor = sharedPrefs.edit()
//        editor.putInt("producto_id", 1)
//        editor.apply()
//
//        val intent = Intent(this, InfoProductActivity::class.java)
//        startActivity(intent)
//    }
}

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

class InfoProductActivity : AppCompatActivity() {

    // Variables globales
    //    private var idProducto = 0
    private var idProducto = 1
    private var producto: Producto? = null

    // Referencias a los elementos de la vista
    private lateinit var ivImagenProducto: ImageView
    private lateinit var tvTituloProducto: TextView
    private lateinit var tvPrecioProducto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_product)

        initViews()
        loadProductData()
        setupListeners()
    }

    /**
     * Inicializa referencias a los elementos del layout
     */
    private fun initViews() {
        ivImagenProducto = findViewById(R.id.imagen_producto)
        tvTituloProducto = findViewById(R.id.tv_titulo_producto)
        tvPrecioProducto = findViewById(R.id.tv_price_product)
    }

    /**
     * Carga los datos del producto desde la base de datos
     */
    private fun loadProductData() {
        val dbProductos = DbProductos(this)
        producto = dbProductos.getProductoById(idProducto)

        if (producto != null) {
            tvTituloProducto.text = producto?.nombre
            tvPrecioProducto.text = producto?.precio.toString()

            // Obtener el nombre de la imagen desde la base de datos
            val imageName = producto?.imagen

            // Obtener el ID del recurso drawable usando el nombre
            val imageResId = resources.getIdentifier(imageName, "drawable", packageName)

            // Asignar el recurso al ImageView
            val imageView = findViewById<ImageView>(R.id.imagen_producto)

            if (imageResId != 0) {
                imageView.setImageResource(imageResId)
            }

        } else {
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ListProductActivity::class.java))
            finish()
        }
    }

    /**
     * Configura acciones de los botones
     */
    private fun setupListeners() {
        findViewById<ImageView>(R.id.btn_location).setOnClickListener {
            startActivity(Intent(this, UbicacionActivity::class.java))
        }

        findViewById<ImageView>(R.id.btn_cart).setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }

        findViewById<Button>(R.id.btn_anadir_carrito).setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }

        // Descripción
        findViewById<ConstraintLayout>(R.id.card_description).setOnClickListener {
            producto?.let {
                val intent = Intent(this, DescriptionProductActivity::class.java)
                intent.putExtra("ID_PRODUCTO", idProducto)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        // Ingredientes
        findViewById<ConstraintLayout>(R.id.card_members).setOnClickListener {
            producto?.let {
                val intent = Intent(this, IngredientsProductActivity::class.java)
                intent.putExtra("ID_PRODUCTO", idProducto)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
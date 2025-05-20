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
import com.example.gaia.db.DbCarrito
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
        setContentView(R.layout.activity_info_producto)

        initViews()

        // Obtener el id del producto dinamicamente
//        idProducto = intent.getIntExtra("ID_PRODUCTO", -1)
//        if (idProducto != -1) {
//            loadProductData(idProducto)
//        } else {
//            Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show()
//            finish()
//        }

        loadProductData()

        setupListeners()
    }

    // Inicializa referencias a los elementos del layout
    private fun initViews() {
        ivImagenProducto = findViewById(R.id.imagen_producto)
        tvTituloProducto = findViewById(R.id.tv_titulo_producto)
        tvPrecioProducto = findViewById(R.id.tv_price_product)
    }

    // Carga los datos del producto ID
    private fun loadProductData() {
        val dbProductos = DbProductos(this)
        producto = dbProductos.getProductById(idProducto)

        if (producto != null) {
            // Obtener el nombre de la imagen
            val imageName = producto?.imagen

            // Obtener el ID del recurso drawable usando el nombre
            val imageResId = resources.getIdentifier(imageName, "drawable", packageName)

            // Si encontró la imagen en drawable, usarla, sino usar imagen por defecto
            if (imageResId != 0) {
                ivImagenProducto.setImageResource(imageResId)
            } else {
                // Aquí asignas la imagen por defecto
                ivImagenProducto.setImageResource(R.drawable.img_product_not_found)
            }

            tvTituloProducto.text = producto?.nombre
            tvPrecioProducto.text = producto?.precio.toString()
        } else {
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ListProductActivity::class.java))
            finish()
        }
    }

    // Configuración de los botones (acciones)
    private fun setupListeners() {
        // Agregar producto al carrito
        findViewById<Button>(R.id.btn_anadir_carrito).setOnClickListener {
            producto?.let {
                val dbCarrito = DbCarrito(this)
                dbCarrito.agregarProductoAlCarrito(it.id)
                Toast.makeText(this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, CarritoActivity::class.java))
            } ?: run {
                Toast.makeText(this, "Producto inválido", Toast.LENGTH_SHORT).show()
            }
        }

        // Redireccionar a la descripción del producto
        findViewById<ConstraintLayout>(R.id.card_description).setOnClickListener {
            producto?.let {
                val intent = Intent(this, DescriptionProductActivity::class.java)
                intent.putExtra("ID_PRODUCTO", idProducto)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        // Redireccionar a los ingredientes del producto
        findViewById<ConstraintLayout>(R.id.card_members).setOnClickListener {
            producto?.let {
                val intent = Intent(this, IngredientsProductActivity::class.java)
                intent.putExtra("ID_PRODUCTO", idProducto)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<ImageView>(R.id.btn_location).setOnClickListener {
            startActivity(Intent(this, UbicacionActivity::class.java))
        }

        findViewById<ImageView>(R.id.btn_cart).setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }
    }
}


package com.example.gaia.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gaia.R
import com.example.gaia.db.DbProductos
import com.example.gaia.models.Producto
import com.example.gaia.Activities.UbicacionActivity
import com.example.gaia.Activities.CarritoActivity
import com.example.gaia.Activities.DescriptionProductActivity

class IngredientsProductFragment : Fragment() {

    private lateinit var tvIngredientes: TextView

    private var idProducto = -1
    private var producto: Producto? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout del fragmento
        return inflater.inflate(R.layout.activity_ingredients_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa vistas
        initViews(view)

        // Recibe el idProducto del bundle de argumentos
        idProducto = arguments?.getInt("ID_PRODUCTO") ?: -1
        if (idProducto != -1) {
            loadProductData(idProducto)
        } else {
            Toast.makeText(requireContext(), "ID inválido", Toast.LENGTH_SHORT).show()
        }

//        setupListeners(view)
    }

    private fun initViews(view: View) {
        tvIngredientes = view.findViewById(R.id.tv_members_product_t1)
    }

    private fun loadProductData(idProducto: Int) {
        val dbProductos = DbProductos(requireContext())
        producto = dbProductos.getProductById(idProducto)

        producto?.let {
            tvIngredientes.text = it.ingredientes
        } ?: run {
            Toast.makeText(requireContext(), "Producto no encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupListeners(view: View) {
        val btnUbicacion = view.findViewById<ImageView>(R.id.btn_location)
        btnUbicacion.setOnClickListener {
            val intent = Intent(requireContext(), UbicacionActivity::class.java)
            startActivity(intent)
        }

        val btnCarrito = view.findViewById<ImageView>(R.id.btn_cart)
        btnCarrito.setOnClickListener {
            val intent = Intent(requireContext(), CarritoActivity::class.java)
            startActivity(intent)
        }

        val btnAnadirCarrito = view.findViewById<Button>(R.id.btn_anadir_carrito)
        btnAnadirCarrito.setOnClickListener {
            val intent = Intent(requireContext(), CarritoActivity::class.java)
            startActivity(intent)
        }

        val descriptionProduct = view.findViewById<ImageView>(R.id.btn_minus_members)
        descriptionProduct.setOnClickListener {
            producto?.let {
                val intent = Intent(requireContext(), DescriptionProductActivity::class.java)
                intent.putExtra("ID_PRODUCTO", idProducto)
                startActivity(intent)
            } ?: run {
                Toast.makeText(requireContext(), "Producto no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

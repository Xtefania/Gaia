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
import com.example.gaia.db.DbCarrito
import java.text.NumberFormat
import java.util.Locale

class IngredientsProductFragment : Fragment() {

    // Variables Layout
    private lateinit var tituloProducto: TextView
    private lateinit var precioProducto: TextView
    private lateinit var btnIngredientes: ImageView
    private lateinit var ingredientesProducto: TextView
    private lateinit var btnAñadirCarrito: Button

    // Variables Producto
    private var idProducto = -1
    private var producto: Producto? = null

    // Formato precios
    private val formatoCOP = NumberFormat.getNumberInstance(Locale("es", "CO"))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_ingredientes_producto, container, false)
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

        setupListeners(view)
    }

    // Inicializando referencias a los elementos del layout
    private fun initViews(view: View) {
        tituloProducto = view.findViewById(R.id.tv_titulo_producto)
        precioProducto = view.findViewById(R.id.tv_price_product)
        btnIngredientes = view.findViewById(R.id.btn_menos_ingredientes)
        ingredientesProducto = view.findViewById(R.id.tv_ingredientes_producto)
        btnAñadirCarrito = view.findViewById(R.id.btn_anadir_carrito)
    }

    // Cargar datos del producto
    private fun loadProductData(idProducto: Int) {
        val dbProductos = DbProductos(requireContext())
        producto = dbProductos.getProductById(idProducto)

        producto?.let {
            tituloProducto.text = producto?.nombre
            precioProducto.text = "$ ${formatoCOP.format(producto?.precio)}"
            ingredientesProducto.text = producto?.ingredientes
        } ?: run {
            Toast.makeText(requireContext(), "Producto no encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupListeners(view: View) {
        // Disminuir ingredientes
        btnIngredientes.setOnClickListener {
            val fragment = DescriptionProductFragment()
            val bundle = Bundle()
            bundle.putInt("ID_PRODUCTO", idProducto)
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction().replace(R.id.frameContainer, fragment)
                .addToBackStack(null).commit()
        }

        btnAñadirCarrito.setOnClickListener {
            producto?.let {
                // Agregar producto al carrito
                val dbCarrito = DbCarrito(requireContext())
                dbCarrito.agregarProductoAlCarrito(it.id)
                Toast.makeText(requireContext(), "Producto agregado al carrito", Toast.LENGTH_SHORT)
                    .show()

                // Ir al CarritoFragment
                val fragment = CarritoFragment()
                val bundle = Bundle()
                fragment.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameContainer, fragment)
                    .addToBackStack(null)
                    .commit()
            } ?: run {
                Toast.makeText(requireContext(), "Producto inválido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

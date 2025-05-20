package com.example.gaia.Fragments

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
import com.example.gaia.db.DbCarrito
import com.example.gaia.db.DbProductos
import com.example.gaia.models.Producto
import java.text.NumberFormat
import java.util.Locale

class InfoProductFragment : Fragment() {

    // Variables Layout
    private lateinit var imagenProducto: ImageView
    private lateinit var tituloProducto: TextView
    private lateinit var precioProducto: TextView
    private lateinit var btnDescripcion: ImageView
    private lateinit var btnIngredientes: ImageView
    private lateinit var btnAñadirCarrito: Button

    // Variables Producto
    private var idProducto = 0
    private var producto: Producto? = null

    // Formato precios
    private val formatoCOP = NumberFormat.getNumberInstance(Locale("es", "CO"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_info_producto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        // Obtener el idProducto de argumentos del fragmento
        idProducto = arguments?.getInt("ID_PRODUCTO") ?: -1
        if (idProducto != -1) {
            loadProductData(idProducto)
        } else {
            Toast.makeText(requireContext(), "ID inválido", Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }

        setupListeners(view)
    }

    // Inicializando referencias a los elementos del layout
    private fun initViews(view: View) {
        imagenProducto = view.findViewById(R.id.imagen_producto)
        tituloProducto = view.findViewById(R.id.tv_titulo_producto)
        precioProducto = view.findViewById(R.id.tv_price_product)
        btnDescripcion = view.findViewById(R.id.btn_plus_descripcion)
        btnIngredientes = view.findViewById(R.id.btn_plus_ìngredientes)
        btnAñadirCarrito = view.findViewById(R.id.btn_anadir_carrito)
    }

    // Cargar datos del producto
    private fun loadProductData(idProducto: Int) {
        val dbProductos = DbProductos(requireContext())
        producto = dbProductos.getProductById(idProducto)

        producto?.let {
            val imageName = producto?.imagen
            val imageResId =
                resources.getIdentifier(imageName, "drawable", requireContext().packageName)
            if (imageResId != 0) {
                imagenProducto.setImageResource(imageResId)
            } else {
                imagenProducto.setImageResource(R.drawable.img_product_not_found)
            }

            tituloProducto.text = producto?.nombre
            precioProducto.text = "$ ${formatoCOP.format(producto?.precio)}"
        } ?: run {
            Toast.makeText(requireContext(), "Producto no encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    // Clicks, navegación, etc
    private fun setupListeners(view: View) {
        btnDescripcion.setOnClickListener {
            val fragment = DescriptionProductFragment()
            val bundle = Bundle()
            bundle.putInt("ID_PRODUCTO", idProducto)
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction().replace(R.id.frameContainer, fragment)
                .addToBackStack(null).commit()
        }

        btnIngredientes.setOnClickListener {
            val fragment = IngredientsProductFragment()
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

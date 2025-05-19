package com.example.gaia.Fragments

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.gaia.*
import com.example.gaia.db.DbProductos
import com.example.gaia.db.DbCarrito
import com.example.gaia.models.Producto

class DescriptionProductFragment : Fragment() {

    // Variables Layout
    private lateinit var btnDescripcion: ImageView
    private lateinit var tituloProducto: TextView
    private lateinit var precioProducto: TextView
    private lateinit var descripcionProducto: TextView
    private lateinit var btnIngredientes: ImageView
    private lateinit var btnAñadirCarrito: Button

    // Variables Producto
    private var idProducto = 0
    private var producto: Producto? = null

    // Crear la vista del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Layout principal
        val view = inflater.inflate(R.layout.activity_description_product, container, false)

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

        return view
    }

    // Inicializando referencias a los elementos del layout
    private fun initViews(view: View) {
        btnDescripcion = view.findViewById(R.id.btn_minus_descripcion)
        tituloProducto = view.findViewById(R.id.tv_titulo_producto)
        precioProducto = view.findViewById(R.id.tv_price_product)
        descripcionProducto = view.findViewById(R.id.tv_description_product)
        btnIngredientes = view.findViewById(R.id.btn_plus_ingredients)
        btnAñadirCarrito = view.findViewById(R.id.btn_anadir_carrito)
    }

    // Cargar datos del producto
    private fun loadProductData(idProducto: Int) {
        val dbProductos = DbProductos(requireContext())
        producto = dbProductos.getProductById(idProducto)

        producto?.let {
            tituloProducto.text = it.nombre
            precioProducto.text = it.precio.toString()
            descripcionProducto.text = it.descripcion
        } ?: run {
            Toast.makeText(requireContext(), "Producto no encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    // Clicks, navegación, etc
    private fun setupListeners(view: View) {

        // Disminuir descripción
        btnDescripcion.setOnClickListener {
            val fragment = InfoProductFragment()
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

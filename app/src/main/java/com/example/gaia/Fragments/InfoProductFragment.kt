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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.gaia.R
import com.example.gaia.db.DbCarrito
import com.example.gaia.db.DbProductos
import com.example.gaia.models.Producto
import com.example.gaia.Activities.*

class InfoProductFragment : Fragment() {

    private var idProducto = 1
    private var producto: Producto? = null

    private lateinit var ivImagenProducto: ImageView
    private lateinit var tvTituloProducto: TextView
    private lateinit var tvPrecioProducto: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_info_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        // Obtener el id dinámicamente si es necesario
        // idProducto = arguments?.getInt("ID_PRODUCTO") ?: -1

        loadProductData()
        setupListeners(view)
    }

    private fun initViews(view: View) {
        ivImagenProducto = view.findViewById(R.id.imagen_producto)
        tvTituloProducto = view.findViewById(R.id.tv_titulo_producto)
        tvPrecioProducto = view.findViewById(R.id.tv_price_product)
    }

    private fun loadProductData() {
        val dbProductos = DbProductos(requireContext())
        producto = dbProductos.getProductById(idProducto)

        if (producto != null) {
//            val imageName = producto?.imagen
//            val imageResId =
//                resources.getIdentifier(imageName, "drawable", requireContext().packageName)

//            if (imageResId != 0) {
//                ivImagenProducto.setImageResource(imageResId)
//            } else {
//                ivImagenProducto.setImageResource(R.drawable.img_product_not_found)
//            }

            tvTituloProducto.text = producto?.nombre
            tvPrecioProducto.text = producto?.precio.toString()
        } else {
            Toast.makeText(requireContext(), "Producto no encontrado", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(requireContext(), ListProductActivity::class.java))
//            requireActivity().finish()
        }
    }

    private fun setupListeners(view: View) {
//        view.findViewById<Button>(R.id.btn_anadir_carrito).setOnClickListener {
//            producto?.let {
//                val dbCarrito = DbCarrito(requireContext())
//                dbCarrito.agregarProductoAlCarrito(it.id)
//                Toast.makeText(requireContext(), "Producto agregado al carrito", Toast.LENGTH_SHORT)
//                    .show()
//                startActivity(Intent(requireContext(), CarritoActivity::class.java))
//            } ?: run {
//                Toast.makeText(requireContext(), "Producto inválido", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        view.findViewById<ConstraintLayout>(R.id.card_description).setOnClickListener {
//            producto?.let {
//                val intent = Intent(requireContext(), DescriptionProductActivity::class.java)
//                intent.putExtra("ID_PRODUCTO", idProducto)
//                startActivity(intent)
//            } ?: run {
//                Toast.makeText(requireContext(), "Producto no encontrado", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//
//        view.findViewById<ConstraintLayout>(R.id.card_members).setOnClickListener {
//            producto?.let {
//                val intent = Intent(requireContext(), IngredientsProductActivity::class.java)
//                intent.putExtra("ID_PRODUCTO", idProducto)
//                startActivity(intent)
//            } ?: run {
//                Toast.makeText(requireContext(), "Producto no encontrado", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//
//        view.findViewById<ImageView>(R.id.btn_location).setOnClickListener {
//            startActivity(Intent(requireContext(), UbicacionActivity::class.java))
//        }
//
//        view.findViewById<ImageView>(R.id.btn_cart).setOnClickListener {
//            startActivity(Intent(requireContext(), CarritoActivity::class.java))
//        }
    }
}

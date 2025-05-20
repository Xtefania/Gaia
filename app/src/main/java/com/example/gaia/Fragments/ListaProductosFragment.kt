package com.example.gaia.Activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.Fragments.InfoProductFragment
import com.example.gaia.R
import com.example.gaia.adapters.ProductoAdapter
import com.example.gaia.db.DbProductos

class ListaProductosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter  // Cambiado aquí

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_productos, container, false)
        recyclerView = view.findViewById(R.id.recyclerProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val db = DbProductos(requireContext())
        val productosDesdeDB = db.obtenerProductosConCategorias()

        adapter = ProductoAdapter(productosDesdeDB) { productoId ->
            val fragment = InfoProductFragment()
            val bundle = Bundle()
            bundle.putInt("ID_PRODUCTO", productoId)
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = adapter

        return view
    }
}

//package com.example.gaia.Activities
//
//import com.example.gaia.adapters.ProductoAdapter
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.gaia.R
//
//class ListaProductosFragment2 : Fragment() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: ProductoAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_lista_productos, container, false)
//        recyclerView = view.findViewById(R.id.recyclerProductos)
//
//        // Lista combinada de títulos y productos
//        val productos: List<ProductoItem> = listOf(
//            ProductoItem.Titulo("Fragancias"),
//            ProductoItem.Producto("Fragancia A", "$20.000", R.drawable.fragancia1),
//            ProductoItem.Producto("Fragancia B", "$35.000", R.drawable.fragancia2),
//            ProductoItem.Producto("Fragancia C", "$42.000", R.drawable.fragancia3),
//            ProductoItem.Producto("Fragancia D", "$20.000", R.drawable.fragancia1),
//            ProductoItem.Producto("Fragancia E", "$35.000", R.drawable.fragancia2),
//            ProductoItem.Producto("Fragancia F", "$42.000", R.drawable.fragancia3),
//
//            ProductoItem.Titulo("Maquillaje"),
//            ProductoItem.Producto("Labial Hidratante", "$20.000", R.drawable.fragancia1),
//            ProductoItem.Producto("Balsamo Labial", "$35.000", R.drawable.fragancia2),
//            ProductoItem.Producto("Gloss Labial C", "$42.000", R.drawable.fragancia3),
//
//            ProductoItem.Titulo("Cuidado Personal"),
//            ProductoItem.Producto("Jabón A", "$20.000", R.drawable.fragancia1),
//            ProductoItem.Producto("Exfoliante B", "$35.000", R.drawable.fragancia2),
//            ProductoItem.Producto("Desodorante C", "$42.000", R.drawable.fragancia3)
//        )
//
//        // Configurar RecyclerView
//        adapter = ProductoAdapter(productos)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.adapter = adapter
//
//        return view
//    }
//}
//sealed class ProductoItem {
//    data class Producto(val nombre: String, val precio: String, val imagenResId: Int) : ProductoItem()
//    data class Titulo(val texto: String) : ProductoItem()
//}
package com.example.gaia.adapters

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.gaia.R
//import com.example.gaia.models.Producto
//
//class ProductoAdapter(
//    private val productos: List<Producto>,
//    private val onItemClick: (Producto) -> Unit
//) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {
//
//    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(producto: Producto) {
////            itemView.findViewById<TextView>(R.id.nombreTextView).text = producto.nombre
////            itemView.setOnClickListener {
////                onItemClick(producto)
////            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_producto, parent, false)
//        return ProductoViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
//        holder.bind(productos[position])
//    }
//
//    override fun getItemCount(): Int = productos.size
//}


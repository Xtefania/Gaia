package com.example.gaia.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.R
import com.example.gaia.models.ProductoCarrito

class ProductoCarritoAdapter(
    private val context: Context,
    private val lista: List<ProductoCarrito>
) : RecyclerView.Adapter<ProductoCarritoAdapter.ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.producto_carrito, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = lista[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = "$${producto.precio}"

        // Cargar imagen desde drawable por nombre
        val resId = context.resources.getIdentifier(
            producto.imagen,
            "drawable",
            context.packageName
        )

//        holder.imagen.setImageResource(
//            if (resId != 0) resId else R.drawable.imagen_por_defecto
//        )
    }

    override fun getItemCount(): Int = lista.size

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imageViewProducto)
        val nombre: TextView = itemView.findViewById(R.id.textViewNombre)
        val precio: TextView = itemView.findViewById(R.id.textViewPrecio)
    }
}

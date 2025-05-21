package com.example.gaia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.R
import com.example.gaia.models.ProductoItem

class ProductoAdapter(
    private val originalItems: List<ProductoItem>,
    private val onProductoClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val visibleItems = mutableListOf<ProductoItem>()
    private val sectionStates = mutableMapOf<Int, Boolean>()

    companion object {
        private const val VIEW_TYPE_PRODUCTO = 0
        private const val VIEW_TYPE_TITULO = 1
    }

    init {
        updateVisibleItems()
    }

    private fun updateVisibleItems() {
        visibleItems.clear()
        var currentSection = -1
        originalItems.forEachIndexed { index, item ->
            when (item) {
                is ProductoItem.Titulo -> {
                    visibleItems.add(item)
                    currentSection = index
                }

                is ProductoItem.ProductoVisual -> {
                    val isExpanded = sectionStates[currentSection] ?: false
                    if (isExpanded) visibleItems.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = when (visibleItems[position]) {
        is ProductoItem.ProductoVisual -> VIEW_TYPE_PRODUCTO
        is ProductoItem.Titulo -> VIEW_TYPE_TITULO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_TITULO -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_titulo, parent, false)
                TituloViewHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_producto, parent, false)
                ProductoViewHolder(view)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = visibleItems[position]
        when (holder) {
            is ProductoViewHolder -> {
                item as ProductoItem.ProductoVisual
                holder.nombre.text = item.nombre
                holder.precio.text = item.precio
                holder.imagen.setImageResource(item.imagenResId)

                holder.itemView.setOnClickListener {
                    onProductoClick(item.id)
                }
            }

            is TituloViewHolder -> {
                item as ProductoItem.Titulo
                holder.titulo.text = item.texto

                val sectionIndex = originalItems.indexOf(item)
                val isExpanded = sectionStates[sectionIndex] ?: false

                val flechaIcon = if (isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                holder.flecha.setImageResource(flechaIcon)

                holder.itemView.setOnClickListener {
                    sectionStates[sectionIndex] = !isExpanded
                    updateVisibleItems()
                }
            }
        }
    }

    override fun getItemCount(): Int = visibleItems.size

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.nombreProducto)
        val precio: TextView = itemView.findViewById(R.id.precioProducto)
        val imagen: ImageView = itemView.findViewById(R.id.imagenProducto)
    }

    class TituloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.tituloTextView)
        val flecha: ImageView = itemView.findViewById(R.id.flechaImage)
    }
}

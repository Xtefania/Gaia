import ProductoAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.Activities.ProductoItem
import com.example.gaia.R

class ProductoAdapter(private val originalItems: List<ProductoItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                is ProductoItem.Producto -> {
                    val isExpanded = sectionStates[currentSection] ?: false
                    if (isExpanded) visibleItems.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int =
        when (visibleItems[position]) {
            is ProductoItem.Producto -> VIEW_TYPE_PRODUCTO
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
                item as ProductoItem.Producto
                holder.nombre.text = item.nombre
                holder.precio.text = item.precio
                holder.imagen.setImageResource(item.imagenResId)
            }
            is TituloViewHolder -> {
                item as ProductoItem.Titulo
                holder.titulo.text = item.texto
                holder.itemView.setOnClickListener {
                    val sectionIndex = originalItems.indexOf(item)
                    val current = sectionStates[sectionIndex] ?: false
                    sectionStates[sectionIndex] = !current
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
    }
}
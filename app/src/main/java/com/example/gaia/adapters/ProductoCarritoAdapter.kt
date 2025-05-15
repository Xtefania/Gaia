import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.R
import com.example.gaia.models.Producto
import com.example.gaia.models.ProductoCarrito

class ProductoCarritoAdapter(
    private val lista: List<ProductoCarrito>,
    private val context: Context,
) : RecyclerView.Adapter<ProductoCarritoAdapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textViewNombre)
        val precio: TextView = itemView.findViewById(R.id.textViewPrecio)
        val cantidad: TextView = itemView.findViewById(R.id.tv_cantidad)
        val imagen: ImageView = itemView.findViewById(R.id.imageViewProducto)
        val flechaMas: ImageView = itemView.findViewById(R.id.iv_flecha_mas)
        val flechaMenos: ImageView = itemView.findViewById(R.id.iv_flecha_menos)
        val quitar: TextView = itemView.findViewById(R.id.textViewAccion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.producto_carrito, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = lista[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = producto.precio.toString()
        holder.cantidad.text = producto.cantidad.toString()
//        holder.imagen.setImageResource(producto.imagen.toString())

        holder.flechaMas.setOnClickListener {
            producto.cantidad++
            notifyItemChanged(position)
        }

        holder.flechaMenos.setOnClickListener {
            if (producto.cantidad > 0) {
                producto.cantidad--
                notifyItemChanged(position)
            }
        }

        holder.quitar.setOnClickListener {
            onQuitarClick(producto)
        }
    }

    private fun onQuitarClick(productoCarrito: ProductoCarrito) {

    }

    override fun getItemCount(): Int = lista.size
}

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.R
import com.example.gaia.db.DbCarrito
import com.example.gaia.models.ProductoCarrito
import java.text.NumberFormat
import java.util.Locale

class ProductoCarritoAdapter(
    private val lista: MutableList<ProductoCarrito>,
    private val context: Context,
    private val listener: OnCantidadCambiadaListener,
    private val listenerProductoQuitado: OnProductoQuitadoListener
) : RecyclerView.Adapter<ProductoCarritoAdapter.ProductoViewHolder>() {

    // Formato precios
    private val formatoCOP = NumberFormat.getNumberInstance(Locale("es", "CO"))

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Variables layout
        val imagen: ImageView = itemView.findViewById(R.id.iv_imagen_producto)
        val nombre: TextView = itemView.findViewById(R.id.tv_nombre_producto)
        val precio: TextView = itemView.findViewById(R.id.tv_precio_producto)
        val cantidad: TextView = itemView.findViewById(R.id.tv_cantidad)
        val flechaMas: ImageView = itemView.findViewById(R.id.iv_flecha_mas)
        val flechaMenos: ImageView = itemView.findViewById(R.id.iv_flecha_menos)
        val quitar: TextView = itemView.findViewById(R.id.tv_quitar_producto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.producto_carrito, parent, false)
        return ProductoViewHolder(view)
    }

    // Iterar
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        // Obtiene el producto en la posición actual
        val producto = lista[position]

        // Asigna los datos del producto
        val resourceId =
            context.resources.getIdentifier(producto.imagen, "drawable", context.packageName)
        if (resourceId != 0) {
            holder.imagen.setImageResource(resourceId)
        } else {
            // Imagen por defecto si no se encuentra el recurso
            holder.imagen.setImageResource(R.drawable.img_product_not_found)
        }
        holder.nombre.text = producto.nombre
        holder.precio.text = "$ ${formatoCOP.format(producto.precio)}"
        holder.cantidad.text = producto.cantidad.toString()

        // Acciones extras

        // Incrementar cantidad
        holder.flechaMas.setOnClickListener {
            producto.cantidad++
            DbCarrito(context).actualizarCantidadProducto(producto.id, producto.cantidad)
            notifyItemChanged(position)
            listener.onCantidadCambiada()
        }

        // Decrementar cantidad
        holder.flechaMenos.setOnClickListener {
            if (producto.cantidad > 1) {
                producto.cantidad--
                DbCarrito(context).actualizarCantidadProducto(producto.id, producto.cantidad)
                notifyItemChanged(position)
                listener.onCantidadCambiada()
            }
        }

        // Quitar producto
        holder.quitar.setOnClickListener {
            onQuitarClick(producto, position)
        }
    }

    private fun onQuitarClick(productoCarrito: ProductoCarrito, position: Int) {
        val dbCarrito = DbCarrito(context)
        dbCarrito.quitarProductoDelCarrito(productoCarrito.id)
        lista.removeAt(position)
        notifyItemRemoved(position)
        Toast.makeText(context, "Producto eliminado", Toast.LENGTH_SHORT).show()
        listenerProductoQuitado.onProductoQuitado()
    }

    override fun getItemCount(): Int = lista.size

    interface OnCantidadCambiadaListener {
        fun onCantidadCambiada()
    }

    interface OnProductoQuitadoListener {
        fun onProductoQuitado()
    }
}

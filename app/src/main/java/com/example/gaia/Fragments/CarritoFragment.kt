package com.example.gaia.Fragments

import ProductoCarritoAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.R
import com.example.gaia.db.DbCarrito
import com.example.gaia.models.ProductoCarrito
import java.text.NumberFormat
import java.util.Locale

class CarritoFragment : Fragment() {

    private lateinit var recyclerViewProducto: RecyclerView
    private lateinit var adapterProducto: ProductoCarritoAdapter
    private lateinit var listaProductos: MutableList<ProductoCarrito>

    private lateinit var spinnerPaises: Spinner
    private lateinit var spinnerCiudades: Spinner
    private var paisSeleccionado: String = ""
    private var ciudadSeleccionada: String = ""

    private val tarifasEnvioUbicacion = mapOf(
        "Colombia" to mapOf("Bogotá" to 1000, "Medellín" to 2000, "Cali" to 3000),
        "Argentina" to mapOf("Buenos Aires" to 1000, "Córdoba" to 2000, "Rosario" to 3000),
        "Brasil" to mapOf("São Paulo" to 1000, "Rio de Janeiro" to 2000, "Brasília" to 3000)
    )

    private lateinit var tvSubtotalProductos: TextView
    private lateinit var tvTarifaEnvioPrecio: TextView
    private lateinit var tvTotal: TextView
    private lateinit var btnCalcular: Button

    private val formatoCOP = NumberFormat.getNumberInstance(Locale("es", "CO"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_carrito, container, false)
        initViews(view)
        recyclerViewProductosCarrito(view)
        spinnerPais()
        calcularSubtotalProductos()
        calcularTarifaEnvio()
        return view
    }

    private fun initViews(view: View) {
        recyclerViewProducto = view.findViewById(R.id.recyclerViewCarrito)
        spinnerPaises = view.findViewById(R.id.opciones_list_item1)
        spinnerCiudades = view.findViewById(R.id.opciones_list_item2)
        tvSubtotalProductos = view.findViewById(R.id.tv_subtotal_precio)
        tvTarifaEnvioPrecio = view.findViewById(R.id.tv_tarfia_precio)
        tvTotal = view.findViewById(R.id.tv_total_precio)
        btnCalcular = view.findViewById(R.id.btn_calcular)
    }

    private fun recyclerViewProductosCarrito(view: View) {
        val dbCarrito = DbCarrito(requireContext())
        listaProductos = dbCarrito.obtenerProductosCarrito().toMutableList()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCarrito)
        val tvSinProductos = view.findViewById<TextView>(R.id.tv_sin_productos)

        if (listaProductos.isEmpty()) {
            recyclerView.visibility = View.GONE
            tvSinProductos.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            tvSinProductos.visibility = View.GONE

            adapterProducto = ProductoCarritoAdapter(
                listaProductos,
                requireContext(),
                object : ProductoCarritoAdapter.OnCantidadCambiadaListener {
                    override fun onCantidadCambiada() {
                        calcularSubtotalProductos()
                    }
                },
                object : ProductoCarritoAdapter.OnProductoQuitadoListener {
                    override fun onProductoQuitado() {
                        actualizarVistaSegunLista()
                        calcularSubtotalProductos()
                    }
                }
            )

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapterProducto
        }
    }

    private fun actualizarVistaSegunLista() {
        if (listaProductos.isEmpty()) {
            recyclerViewProducto.visibility = View.GONE
            view?.findViewById<TextView>(R.id.tv_sin_productos)?.visibility = View.VISIBLE
        } else {
            recyclerViewProducto.visibility = View.VISIBLE
            view?.findViewById<TextView>(R.id.tv_sin_productos)?.visibility = View.GONE
        }
    }

    private fun spinnerPais() {
        paisSeleccionado = resources.getStringArray(R.array.lista_paises)[0]

        val adapterPaises = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lista_paises,
            android.R.layout.simple_spinner_item
        )

        adapterPaises.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPaises.adapter = adapterPaises

        spinnerPaises.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                paisSeleccionado = parent.getItemAtPosition(position) as String
                spinnerCiudades(paisSeleccionado)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun spinnerCiudades(pais: String) {
        val ciudadesArrayId = when (pais) {
            "Colombia" -> R.array.ciudades_colombia
            "Argentina" -> R.array.ciudades_argentina
            "Brasil" -> R.array.ciudades_brasil
            else -> R.array.ciudades_colombia
        }

        val adapterCiudades = ArrayAdapter.createFromResource(
            requireContext(),
            ciudadesArrayId,
            android.R.layout.simple_spinner_item
        )

        adapterCiudades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCiudades.adapter = adapterCiudades

        ciudadSeleccionada = resources.getStringArray(ciudadesArrayId)[0]

        spinnerCiudades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                ciudadSeleccionada = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun calcularSubtotalProductos() {
        val subtotal = listaProductos.sumOf { it.precio * it.cantidad }
        tvSubtotalProductos.text = "$ ${formatoCOP.format(subtotal)}"
    }

    private fun calcularTarifaEnvio() {
        btnCalcular.setOnClickListener {
            val tarifa = tarifasEnvioUbicacion[paisSeleccionado]?.get(ciudadSeleccionada)
            if (tarifa != null) {
                tvTarifaEnvioPrecio.text = "$ ${formatoCOP.format(tarifa)}"
            } else {
                tvTarifaEnvioPrecio.text = "No disponible"
            }
            calcularTotal()
        }
    }

    private fun calcularTotal() {
        val subtotal =
            tvSubtotalProductos.text.toString().filter { it.isDigit() }.toIntOrNull() ?: 0
        val tarifaEnvio =
            tvTarifaEnvioPrecio.text.toString().filter { it.isDigit() }.toIntOrNull() ?: 0
        val total = subtotal + tarifaEnvio
        tvTotal.text = "$ ${formatoCOP.format(total)}"
    }
}

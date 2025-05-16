package com.example.gaia.Activities

import ProductoCarritoAdapter
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.R
import com.example.gaia.db.DbCarrito
import com.example.gaia.models.ProductoCarrito

class CarritoActivity : AppCompatActivity() {

    // RecyclerView y su Adapter para mostrar los productos en el carrito
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoCarritoAdapter
    private lateinit var listaProductos: MutableList<ProductoCarrito>

    // Views para mostrar tarifa y subtotal
    private lateinit var tvTarifaEnvio: TextView
    private lateinit var tvSubtotal: TextView

    // Spinners para seleccionar país y ciudad
    private lateinit var spinnerPaises: Spinner
    private lateinit var spinnerCiudades: Spinner

    // Mapa con tarifas de envío por país y ciudad
    private val tarifasEnvio = mapOf(
        "Colombia" to mapOf(
            "Bogotá" to 10.0,
            "Medellín" to 12.0,
            "Cali" to 11.0
        ),
        "Argentina" to mapOf(
            "Buenos Aires" to 15.0,
            "Córdoba" to 13.0,
            "Rosario" to 14.0
        ),
        "Brasil" to mapOf(
            "São Paulo" to 20.0,
            "Rio de Janeiro" to 22.0,
            "Brasília" to 18.0
        )
    )

    // Variables para almacenar selección actual
    private var paisSeleccionado: String = ""
    private var ciudadSeleccionada: String = ""

    // Total de los productos (sin incluir tarifa)
    private var totalProductos: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        // Inicializar RecyclerView y cargar productos desde DB
        recyclerView = findViewById(R.id.recyclerViewCarrito)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val dbCarrito = DbCarrito(this)
        listaProductos = dbCarrito.obtenerProductosCarrito().toMutableList()
        adapter = ProductoCarritoAdapter(listaProductos, this)
        recyclerView.adapter = adapter

        // Inicializar views para tarifa, subtotal y spinners
        tvTarifaEnvio = findViewById(R.id.tv_tarfia_precio)
        tvSubtotal = findViewById(R.id.tv_subtotal_precio)
        spinnerPaises = findViewById(R.id.opciones_list_item1)
        spinnerCiudades = findViewById(R.id.opciones_list_item2)

        // Configurar adapter para spinner de países
        val adapterPaises = ArrayAdapter.createFromResource(
            this,
            R.array.lista_paises,
            android.R.layout.simple_spinner_item
        )
        adapterPaises.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPaises.adapter = adapterPaises

        // Inicializar país y ciudad seleccionados para evitar valores vacíos
        paisSeleccionado = resources.getStringArray(R.array.lista_paises)[0]
        actualizarSpinnerCiudades(paisSeleccionado)

        // Listener para selección de país: actualiza lista de ciudades según país
        spinnerPaises.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                paisSeleccionado = parent.getItemAtPosition(position) as String
                actualizarSpinnerCiudades(paisSeleccionado)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Listener para selección de ciudad: actualiza tarifa y subtotal
        spinnerCiudades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                ciudadSeleccionada = parent.getItemAtPosition(position) as String
                actualizarTarifaEnvio()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Calcular total de productos y mostrar subtotal inicial
        calcularTotalProductos()
    }

    /**
     * Actualiza el spinner de ciudades según el país seleccionado.
     * También selecciona la primera ciudad por defecto y actualiza tarifa.
     */
    private fun actualizarSpinnerCiudades(pais: String) {
        val ciudadesArrayId = when (pais) {
            "Colombia" -> R.array.ciudades_colombia
            "Argentina" -> R.array.ciudades_argentina
            "Brasil" -> R.array.ciudades_brasil
            else -> R.array.ciudades_colombia
        }

        val adapterCiudades = ArrayAdapter.createFromResource(
            this,
            ciudadesArrayId,
            android.R.layout.simple_spinner_item
        )
        adapterCiudades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCiudades.adapter = adapterCiudades

        // Seleccionar la primera ciudad para evitar valores vacíos
        ciudadSeleccionada = resources.getStringArray(ciudadesArrayId)[0]
        actualizarTarifaEnvio()
    }

    /**
     * Calcula el total sumando (precio * cantidad) de todos los productos en el carrito.
     * Luego actualiza el subtotal con la tarifa de envío.
     */
    private fun calcularTotalProductos() {
        totalProductos = listaProductos.sumOf { it.precio * it.cantidad }
        actualizarSubtotal()
    }

    /**
     * Actualiza el TextView que muestra la tarifa de envío estimada según país y ciudad seleccionados.
     * También actualiza el subtotal para reflejar el cambio.
     */
    private fun actualizarTarifaEnvio() {
        val tarifa = tarifasEnvio[paisSeleccionado]?.get(ciudadSeleccionada)
        if (tarifa != null) {
            tvTarifaEnvio.text = "$ $tarifa"
        } else {
            tvTarifaEnvio.text = "No disponible"
        }
        actualizarSubtotal()
    }

    /**
     * Actualiza el TextView que muestra el subtotal: suma total productos + tarifa de envío.
     * También imprime un log para debug.
     */
    private fun actualizarSubtotal() {
        val tarifa = tarifasEnvio[paisSeleccionado]?.get(ciudadSeleccionada) ?: 0.0
        val subtotal = totalProductos + tarifa
        tvSubtotal.text = "Subtotal: $ $subtotal"

        // Log para debugging (puedes ver esto en Logcat)
        println("DEBUG: totalProductos=$totalProductos, tarifa=$tarifa, subtotal=$subtotal")
    }
}

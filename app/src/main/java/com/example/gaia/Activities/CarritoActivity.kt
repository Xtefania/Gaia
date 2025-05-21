package com.example.gaia.Activities

import ProductoCarritoAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaia.R
import com.example.gaia.db.DbCarrito
import com.example.gaia.models.ProductoCarrito

import java.text.NumberFormat
import java.util.Locale

class CarritoActivity : AppCompatActivity() {

    // RecyclerView y su Adapter para mostrar los productos en el carrito
    private lateinit var recyclerViewProducto: RecyclerView
    private lateinit var adapterProducto: ProductoCarritoAdapter
    private lateinit var listaProductos: MutableList<ProductoCarrito>

    // Selects (spinners) para seleccionar país y ciudad
    private lateinit var spinnerPaises: Spinner
    private lateinit var spinnerCiudades: Spinner
    private var paisSeleccionado: String = ""
    private var ciudadSeleccionada: String = ""

    // Mapa con tarifas de envío por país y ciudad
    private val tarifasEnvioUbicacion = mapOf(
        "Colombia" to mapOf(
            "Bogotá" to 1000,
            "Medellín" to 2000,
            "Cali" to 3000
        ),
        "Argentina" to mapOf(
            "Buenos Aires" to 1000,
            "Córdoba" to 2000,
            "Rosario" to 3000
        ),
        "Brasil" to mapOf(
            "São Paulo" to 1000,
            "Rio de Janeiro" to 2000,
            "Brasília" to 3000
        )
    )

    // Valores
    private lateinit var tvSubtotalProductos: TextView
    private lateinit var tvTarifaEnvioPrecio: TextView
    private lateinit var tvTotal: TextView

    val formatoCOP = NumberFormat.getNumberInstance(Locale("es", "CO"))

    override fun onCreate(savedInstanceState: Bundle?) {
        // Renderizar vista
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        // Inicializa variables
        initViews()

        // Renderiza productos
//        recyclerViewProductosCarrito()

        // Renderiza listas ubicación para la tarifa
        spinnerPais()

        // Calcula valores
        calcularSubtotalProductos()
        calcularTarifaEnvio()
    }

    // Inicializa referencias a los elementos del layout
    private fun initViews() {
//        recyclerViewProducto = findViewById(R.id.recyclerViewCarrito)
        spinnerPaises = findViewById(R.id.opciones_list_item1)
        spinnerCiudades = findViewById(R.id.opciones_list_item2)
        tvSubtotalProductos = findViewById(R.id.tv_subtotal_precio)
        tvTarifaEnvioPrecio = findViewById(R.id.tv_tarfia_precio)
        tvTotal = findViewById(R.id.tv_total_precio)
    }

    // RecyclerView Productos Carrito
//    private fun recyclerViewProductosCarrito() {
//        // Establecer el LayoutManager para definir cómo se muestran los elementos (lista vertical)
//        recyclerViewProducto.layoutManager = LinearLayoutManager(this)
//        val dbCarrito = DbCarrito(this)
//        // Lista mutable para poder modificarla si es necesario
//        listaProductos = dbCarrito.obtenerProductosCarrito().toMutableList()
//        adapterProducto = ProductoCarritoAdapter(listaProductos, this)
//        // Asignar el adaptador al RecyclerView para que pueda mostrar los productos
//        recyclerViewProducto.adapter = adapterProducto
//    }

    // Configura el Spinner de países con su lista y comportamiento
    private fun spinnerPais() {
        // Inicializar la variable con el primer país de la lista para un valor por defecto
        paisSeleccionado = resources.getStringArray(R.array.lista_paises)[0]

        // Crear un adaptador que carga el array de países desde recursos
        val adapterPaises = ArrayAdapter.createFromResource(
            this,
            R.array.lista_paises,
            android.R.layout.simple_spinner_item // Layout para el ítem visible del spinner
        )

        // Definir el layout para los elementos desplegables del spinner
        adapterPaises.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Asignar el adaptador al spinner para mostrar la lista de países
        spinnerPaises.adapter = adapterPaises

        // Definir la acción cuando el usuario selecciona un país
        spinnerPaises.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                // Actualizar la variable con el país seleccionado
                paisSeleccionado = parent.getItemAtPosition(position) as String
                // Actualizar el spinner de ciudades en función del país seleccionado
                spinnerCiudades(paisSeleccionado)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada si no se selecciona ningún país
            }
        }
    }

    // Configura el Spinner de ciudades según el país seleccionado
    private fun spinnerCiudades(pais: String) {
        // Elegir el array de ciudades correcto según el país
        val ciudadesArrayId = when (pais) {
            "Colombia" -> R.array.ciudades_colombia
            "Argentina" -> R.array.ciudades_argentina
            "Brasil" -> R.array.ciudades_brasil
            else -> R.array.ciudades_colombia // Valor por defecto
        }

        // Crear un adaptador para las ciudades correspondiente al país seleccionado
        val adapterCiudades = ArrayAdapter.createFromResource(
            this,
            ciudadesArrayId,
            android.R.layout.simple_spinner_item
        )

        // Definir el layout para la lista desplegable del spinner de ciudades
        adapterCiudades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Asignar el adaptador al spinner de ciudades para mostrar la lista
        spinnerCiudades.adapter = adapterCiudades

        // Inicializar con la primera ciudad para evitar valores vacíos
        ciudadSeleccionada = resources.getStringArray(ciudadesArrayId)[0]

        // Definir la acción cuando el usuario selecciona una ciudad
        spinnerCiudades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                // Actualizar la variable con la ciudad seleccionada
                ciudadSeleccionada = parent.getItemAtPosition(position) as String
                // Aquí puedes agregar lógica para actualizar tarifas, subtotales, etc.
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada si no se selecciona ninguna ciudad
            }
        }
    }

    // Calcula el total de los productos (precio * cantidad)
    private fun calcularSubtotalProductos() {
        val subtotal = listaProductos.sumOf { it.precio * it.cantidad }
        tvSubtotalProductos.text = "$ ${formatoCOP.format(subtotal)}"
    }

    // Calcula el valor de la tarifa según la ubicación
    private fun calcularTarifaEnvio() {
        findViewById<Button>(R.id.btn_calcular).setOnClickListener {
            val tarifa = tarifasEnvioUbicacion[paisSeleccionado]?.get(ciudadSeleccionada)
            if (tarifa != null) {
                tvTarifaEnvioPrecio.text = "$ ${formatoCOP.format(tarifa)}"
            } else {
                tvTarifaEnvioPrecio.text = "No disponible"
            }
            calcularTotal()
        }
    }

    // Calcula el total del pedido (Productos + Tarifa de envio)
    private fun calcularTotal() {
        // Eliminar símbolos y espacios antes de convertir a Int
        val subtotal =
            tvSubtotalProductos.text.toString().filter { it.isDigit() }.toIntOrNull() ?: 0
        val tarifaEnvio =
            tvTarifaEnvioPrecio.text.toString().filter { it.isDigit() }.toIntOrNull() ?: 0

        val total = subtotal + tarifaEnvio

        // Mostrar total con símbolo $ y formato simple
        tvTotal.text = "$ ${formatoCOP.format(total)}"
    }
}

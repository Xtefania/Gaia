package com.example.gaia.Activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UbicacionActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map) // Asegúrate que el archivo se llama así

        // Inicializar el fragmento del mapa
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Configurar el botón con la dirección
        val direccionBtn = findViewById<TextView>(R.id.text_direccion)
        direccionBtn.setOnClickListener {
            // Coordenadas de Unicentro Bogotá
            val ubicacion = LatLng(4.7036, -74.0421)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 17f))
            mMap.addMarker(
                MarkerOptions()
                    .position(ubicacion)
                    .title("Unicentro Bogotá")
            )
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Marcador inicial en Bogotá (puedes cambiarlo si lo deseas)
        val bogota = LatLng(4.6482837, -74.2478946)
        mMap.addMarker(MarkerOptions().position(bogota).title("Tienda en Bogotá"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bogota, 15f))
    }
}

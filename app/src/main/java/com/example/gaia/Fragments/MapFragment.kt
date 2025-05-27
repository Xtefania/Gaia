package com.example.gaia.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gaia.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var direccionView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        direccionView = view.findViewById(R.id.text_direccion)

        // Fragmento del mapa
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Coordenadas de la tienda
        val ubicacion = LatLng(4.7036, -74.0421)

        // Agregar marcador
        mMap.addMarker(MarkerOptions().position(ubicacion).title("Unicentro Bogotá"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 14f))

        // Habilitar controles de zoom
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true

        // Acción al tocar la dirección
        direccionView.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 17f))
        }
    }
}


package com.example.gaia.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.gaia.R
import com.example.gaia.utils.PhotoHelper

class ListProductActivity : AppCompatActivity() {

    private lateinit var photoHelper: PhotoHelper
    private lateinit var takePhotoLauncher: ActivityResultLauncher<android.net.Uri>
    private lateinit var pickGalleryLauncher: ActivityResultLauncher<String>
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_productos)

        // Inicializar PhotoHelper
        photoHelper = PhotoHelper(this)

        val imageView = findViewById<ImageView>(R.id.imageView)

        // Lanzador para tomar foto
        takePhotoLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                val photoUri = photoHelper.photoUri
                Toast.makeText(this, "Foto tomada: $photoUri", Toast.LENGTH_SHORT).show()
                imageView.setImageURI(photoUri)
            }
        }

        // Lanzador para seleccionar imagen de galería
        pickGalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                Toast.makeText(this, "Imagen seleccionada: $uri", Toast.LENGTH_SHORT).show()
                imageView.setImageURI(uri)
            }
        }

        // Lanzador para pedir permisos
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            val allGranted = result.all { it.value }
            if (allGranted) {
                Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permisos denegados", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón ubicación
        val btnUbicacion = findViewById<ImageView>(R.id.btn_location)
        btnUbicacion.setOnClickListener {
            val intent = Intent(this, UbicacionActivity::class.java)
            startActivity(intent)
        }

        // Botón carrito
        val carrito = findViewById<ImageView>(R.id.btn_cart)
        carrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        // Botón cámara productos
        val btnCamara = findViewById<Button>(R.id.btnTakePhoto)
        btnCamara.setOnClickListener {
            if (photoHelper.hasPermissions()) {
                val uri = photoHelper.preparePhotoUri()
                if (uri != null) {
                    takePhotoLauncher.launch(uri)
                } else {
                    Toast.makeText(this, "No se pudo preparar el archivo", Toast.LENGTH_SHORT).show()
                }
            } else {
                permissionLauncher.launch(photoHelper.createPermissionsArray())
            }
        }

        // Botón galería productos
        val btnGaleria = findViewById<Button>(R.id.btnOpenGallery)
        btnGaleria.setOnClickListener {
            if (photoHelper.hasPermissions()) {
                pickGalleryLauncher.launch("image/*")
            } else {
                permissionLauncher.launch(photoHelper.createPermissionsArray())
            }
        }

        // Arreglo de cards de productos
        val cardsIds = listOf(
            R.id.cl_producto_1,
            R.id.cl_producto_2,
            R.id.cl_producto_3,
            R.id.cl_producto_4,
        )

        for (id in cardsIds) {
            val card = findViewById<ConstraintLayout>(id)
            card.setOnClickListener {
                val intent = Intent(this, InfoProductActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

package com.example.gaia.Activities

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.R
import com.example.gaia.utils.PhotoHelper

class CameraActivity : AppCompatActivity() {

    private lateinit var photoHelper: PhotoHelper
    private lateinit var takePhotoLauncher: ActivityResultLauncher<Uri>
    private lateinit var pickGalleryLauncher: ActivityResultLauncher<String>
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private lateinit var imageView: ImageView
    private lateinit var btnTakePhoto: Button
    private lateinit var btnOpenGallery: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        photoHelper = PhotoHelper(this)

        imageView = findViewById(R.id.imageView)
        btnTakePhoto = findViewById(R.id.btnTakePhoto)
        btnOpenGallery = findViewById(R.id.btnOpenGallery)

        // Lanzador para tomar foto con la cámara
        takePhotoLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                val photoUri = photoHelper.photoUri
                imageView.setImageURI(photoUri)
                Toast.makeText(this, "Foto tomada correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al tomar la foto", Toast.LENGTH_SHORT).show()
            }
        }

        // Lanzador para seleccionar imagen de la galería
        pickGalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                imageView.setImageURI(it)
                Toast.makeText(this, "Imagen seleccionada", Toast.LENGTH_SHORT).show()
            }
        }

        // Lanzador para pedir permisos
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val allGranted = permissions.all { it.value }
            if (allGranted) {
                Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_SHORT).show()
                // Intentamos lanzar la acción que el usuario pidió (tomar foto o abrir galería)
                // En este ejemplo, simplemente habilitamos botones
            } else {
                Toast.makeText(this, "Permisos denegados", Toast.LENGTH_SHORT).show()
            }
        }

        btnTakePhoto.setOnClickListener {
            if (photoHelper.hasPermissions()) {
                val uri = photoHelper.preparePhotoUri()
                if (uri != null) {
                    takePhotoLauncher.launch(uri)
                } else {
                    Toast.makeText(this, "No se pudo preparar el archivo para la foto", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Pedir permisos necesarios (CAMERA y WRITE_EXTERNAL_STORAGE si aplican)
                permissionLauncher.launch(photoHelper.createPermissionsArray())
            }
        }

        btnOpenGallery.setOnClickListener {
            if (photoHelper.hasPermissions()) {
                pickGalleryLauncher.launch("image/*")
            } else {
                permissionLauncher.launch(photoHelper.createPermissionsArray())
            }
        }
    }
}

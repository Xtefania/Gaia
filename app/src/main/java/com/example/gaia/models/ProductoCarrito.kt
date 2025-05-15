package com.example.gaia.models

data class ProductoCarrito(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val ingredientes: String,
    val imagen: String,
    val subcategoriaId: Int,
    var cantidad: Int

//    val nombre: String,
//    val precio: Double,
//    val imagen: String // solo el nombre de la imagen sin extensión
)

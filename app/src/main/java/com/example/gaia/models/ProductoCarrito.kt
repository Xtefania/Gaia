package com.example.gaia.models

data class ProductoCarrito(
    val id: Int,
    val imagen: String,
    val nombre: String,
    val precio: Int,
    var cantidad: Int
//    val descripcion: String,
//    val ingredientes: String,
//    val subcategoriaId: Int,
)

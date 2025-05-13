package com.example.gaia.models

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: String,
    val ingredientes: String,
    val imagenResId: Int? = null
)



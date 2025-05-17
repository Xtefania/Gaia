package com.example.gaia.models

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Int,
    val ingredientes: String,
    val imagen: String,
    val subcategoriaId: Int
)
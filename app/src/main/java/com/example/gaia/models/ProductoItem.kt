package com.example.gaia.models

sealed class ProductoItem {
    data class Titulo(val texto: String) : ProductoItem()
    data class ProductoVisual(
        val id: Int,
        val nombre: String,
        val precio: String,
        val imagenResId: Int
    ) : ProductoItem()
}

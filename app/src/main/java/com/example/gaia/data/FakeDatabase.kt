package com.example.gaia.data

import com.example.gaia.models.Producto
import com.example.gaia.R

object FakeDatabase {

    //    TABLAS
    //    region - Tabla Productos
    val productos = listOf(
        Producto(
            1,
            "Perfume A",
            "Formulado con una base 100% vegetal y enriquecido con la nutritiva manteca de Karité. " +
                    "\n \nEstá formulado sin aceite de Palma y enriquecido con extracto de Karité, el cual tiene propiedades suavizantes y nutritivas." +
                    "\n \n99% ingredientes de origen natural." +
                    "\n \nProducto para toda la familia." +
                    "\n \nEstá formulado sin aceite de Palma y enriquecido con extracto de Karité, el cual tiene propiedades suavizantes y nutritivas." +
                    "\n \n99% ingredientes de origen natural." +
                    "\n \nProducto para toda la familia." +
                    "\n \nEstá formulado sin aceite de Palma y enriquecido con extracto de Karité, el cual tiene propiedades suavizantes y nutritivas." +
                    "\n \n99% ingredientes de origen natural." +
                    "\n \nProducto para toda la familia." +
                    "\n \nEstá formulado sin aceite de Palma y enriquecido con extracto de Karité, el cual tiene propiedades suavizantes y nutritivas." +
                    "\n \n99% ingredientes de origen natural." +
                    "\n \nProducto para toda la familia.",
            "$49.99",
            "Agua, alcohol, fragancia",
            R.drawable.fragancia1
        ), Producto(
            2, "Perfume B", "Intenso y amaderado", "$59.99", "Aceite esencial, alcohol, agua", null
        ), Producto(3, "Perfume C", "Ligero y cítrico", "$39.99", "Cítricos, agua, alcohol", null)
    )
    //endregion

    //    FUNCIONES
    //    region - Funciones Productos
    fun obtenerProductoPorId(id: Int): Producto? {
        return productos.find { it.id == id }
    }
    //endregion
}

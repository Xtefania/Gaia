package com.example.gaia.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.gaia.models.Producto;
import com.example.gaia.models.ProductoItem;

import java.util.ArrayList;
import java.util.List;

public class DbProductos extends DbHelper {

    private final Context context;
    private static final String TAG = "DbProductos";

    public DbProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    // Llamar Metodo en otras visuales
    // import com.example.gaia.db.DbProductos
    // import com.example.gaia.models.Producto
    // val dbProductos = DbProductos(this)
    // variableResultado = dbProductos.metodo(parametro)

    public List<ProductoItem> obtenerProductosConCategorias() {
        List<ProductoItem> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Consulta todas las categorías
        String queryCategorias = "SELECT id, nombre FROM " + TABLE_CATEGORIAS;
        Cursor cursorCategorias = db.rawQuery(queryCategorias, null);

        if (cursorCategorias.moveToFirst()) {
            do {
                int categoriaId = cursorCategorias.getInt(0);
                String nombreCategoria = cursorCategorias.getString(1);

                // Agrega el título de la categoría como sección
                lista.add(new ProductoItem.Titulo(nombreCategoria));

                // Consulta los productos asociados directamente a esta categoría
                String queryProductos = "SELECT id, nombre, precio, imagen FROM " + TABLE_PRODUCTOS + " WHERE categoria_id = ?";
                Cursor cursorProd = db.rawQuery(queryProductos, new String[]{String.valueOf(categoriaId)});

                if (cursorProd.moveToFirst()) {
                    do {
                        int id = cursorProd.getInt(0); // ID del producto
                        String nombre = cursorProd.getString(1);
                        String precio = String.valueOf(cursorProd.getInt(2));
                        String imagen = cursorProd.getString(3); // nombre del recurso drawable

                        // Obtiene el ID del recurso drawable
                        int imageResId = context.getResources().getIdentifier(imagen, "drawable", context.getPackageName());

                        // Agrega el producto a la lista con todos los parámetros necesarios
                        lista.add(new ProductoItem.ProductoVisual(id, nombre, "$" + precio, imageResId));

                    } while (cursorProd.moveToNext());
                }

                cursorProd.close();

            } while (cursorCategorias.moveToNext());
        }

        cursorCategorias.close();
        return lista;
    }

    public Producto getProductById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Producto producto = null;

        String query = "SELECT id, nombre, descripcion, precio, ingredientes, imagen, categoria_id FROM " + TABLE_PRODUCTOS + " WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            producto = new Producto(
                    cursor.getInt(0),    // id
                    cursor.getString(1), // nombre
                    cursor.getString(2), // descripcion
                    cursor.getInt(3),    // precio
                    cursor.getString(4), // ingredientes
                    cursor.getString(5), // imagen
                    cursor.getInt(6)     // categoriaId
            );
        }
        cursor.close();
        db.close();
        return producto;
    }
}

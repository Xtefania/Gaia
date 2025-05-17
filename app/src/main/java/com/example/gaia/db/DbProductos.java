package com.example.gaia.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.gaia.models.Producto;

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

    // Metodo obtener todos los producto de una categoria
    public List<Producto> getProductsBySubcategory(int idSubcategory) {
        List<Producto> productos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE subcategoria_id = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(idSubcategory)});

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Producto producto = new Producto(
                            cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                            cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("precio")),
                            cursor.getString(cursor.getColumnIndexOrThrow("ingredientes")),
                            cursor.getString(cursor.getColumnIndexOrThrow("imagen")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("subcategoria_id"))
                    );
                    productos.add(producto);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al obtener productos por subcategoria", e);
        } finally {
            if (cursor != null) cursor.close();
            if (db != null && db.isOpen()) db.close();
        }

        return productos;
    }

    // Metodo obtener producto por ID
    public Producto getProductById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        Producto producto = null;

        try {
            db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE id = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

            if (cursor != null && cursor.moveToFirst()) {
                producto = new Producto(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                        cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("precio")),
                        cursor.getString(cursor.getColumnIndexOrThrow("ingredientes")),
                        cursor.getString(cursor.getColumnIndexOrThrow("imagen")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("subcategoria_id"))
                );
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al obtener el producto por ID", e);
        } finally {
            if (cursor != null) cursor.close();
            if (db != null && db.isOpen()) db.close();
        }

        return producto;
    }
}

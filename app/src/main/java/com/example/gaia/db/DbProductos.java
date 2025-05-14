package com.example.gaia.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.gaia.models.Producto;

public class DbProductos extends DbHelper {

    private final Context context;
    private static final String TAG = "DbProductos";

    public DbProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    /**
     * Obtiene un producto de la base de datos por su ID.
     *
     * @param id El ID del producto.
     * @return Objeto Producto o null si no se encuentra.
     */
    public Producto getProductoById(int id) {
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
                        cursor.getDouble(cursor.getColumnIndexOrThrow("precio")),
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

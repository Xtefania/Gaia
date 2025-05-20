package com.example.gaia.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.gaia.models.ProductoCarrito;

import java.util.ArrayList;
import java.util.List;

public class DbCarrito extends DbHelper {

    private final Context context;
    private static final String TAG = "DbCarrito";

    public DbCarrito(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    // Agrega un producto por su ID a la tabla carrito
    public void agregarProductoAlCarrito(int productoId) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT cantidad FROM t_carrito WHERE producto_id = ?", new String[]{String.valueOf(productoId)});

        if (cursor.moveToFirst()) {
            int cantidadActual = cursor.getInt(0);
            ContentValues valores = new ContentValues();
            valores.put("cantidad", cantidadActual + 1);

            db.update("t_carrito", valores, "producto_id = ?", new String[]{String.valueOf(productoId)});
        } else {
            ContentValues valores = new ContentValues();
            valores.put("producto_id", productoId);
            valores.put("cantidad", 1);

            db.insert("t_carrito", null, valores);
        }
        cursor.close();
        db.close();
    }

    // Obtiene los productos guardados en el carrito
    public List<ProductoCarrito> obtenerProductosCarrito() {
        List<ProductoCarrito> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT p.id, p.nombre, p.precio, p.imagen, c.cantidad " + "FROM t_productos p " + "INNER JOIN t_carrito c ON p.id = c.producto_id";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ProductoCarrito producto = new ProductoCarrito(cursor.getInt(cursor.getColumnIndexOrThrow("id")), cursor.getString(cursor.getColumnIndexOrThrow("imagen")), cursor.getString(cursor.getColumnIndexOrThrow("nombre")), cursor.getInt(cursor.getColumnIndexOrThrow("precio")), cursor.getInt(cursor.getColumnIndexOrThrow("cantidad")));

                lista.add(producto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lista;
    }


//    public List<ProductoCarrito> obtenerProductosCarrito() {
//        List<ProductoCarrito> lista = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String query = "SELECT p.id, p.nombre, p.descripcion, p.precio, p.ingredientes, p.imagen, c.cantidad " +
//                "FROM t_productos p " +
//                "INNER JOIN t_carrito c ON p.id = c.producto_id";
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                ProductoCarrito producto = new ProductoCarrito(
//                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
//                        cursor.getString(cursor.getColumnIndexOrThrow("imagen")),
//                        cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
//                        cursor.getInt(cursor.getColumnIndexOrThrow("precio")),
//                        cursor.getInt(cursor.getColumnIndexOrThrow("cantidad"))

    /// /                        cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
    /// /                        cursor.getString(cursor.getColumnIndexOrThrow("ingredientes")),
    /// /                        cursor.getInt(cursor.getColumnIndexOrThrow("subcategoria_id")),
//                );
//
//                lista.add(producto);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//        return lista;
//    }

    // Actualiza la cantidad de un producto específico en el carrito
    public boolean actualizarCantidadProducto(int productoId, int nuevaCantidad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("cantidad", nuevaCantidad);

        int filasAfectadas = db.update("t_carrito", valores, "producto_id = ?", new String[]{String.valueOf(productoId)});
        db.close();
        return filasAfectadas > 0;
    }


    // Elimina un producto del carrito por su ID
    public void quitarProductoDelCarrito(int productoId) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Elimina el producto cuyo producto_id coincida
        db.delete("t_carrito", "producto_id = ?", new String[]{String.valueOf(productoId)});

        db.close();
    }
}

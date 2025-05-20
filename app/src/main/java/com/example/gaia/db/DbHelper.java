package com.example.gaia.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "gaia.db";

    // Nombres de las tablas
    public static final String TABLE_CATEGORIAS = "t_categorias";
    public static final String TABLE_SUBCATEGORIAS = "t_subcategorias";
    public static final String TABLE_PRODUCTOS = "t_productos";
    public static final String TABLE_CARRITO = "t_carrito";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        //region CREAR TABLAS
        db.execSQL("CREATE TABLE " + TABLE_CATEGORIAS + " (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nombre TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABLE_PRODUCTOS + " (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nombre TEXT NOT NULL, " + "descripcion TEXT, " + "precio INTEGER, " + "ingredientes TEXT, " + "imagen TEXT, " + "categoria_id INTEGER NOT NULL, " + "FOREIGN KEY (categoria_id) REFERENCES " + TABLE_CATEGORIAS + "(id));");

        db.execSQL("CREATE TABLE IF NOT EXISTS t_carrito (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "producto_id INTEGER NOT NULL, " + "cantidad INTEGER NOT NULL DEFAULT 1, " + "FOREIGN KEY(producto_id) REFERENCES t_productos(id)" + ");");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS idx_carrito_producto_id ON t_carrito(producto_id);");
        //endregion

        //region CREAR REGISTRO TABLAS
        //region - CATEGORIAS
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Maquillaje');");      // id = 1
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Cuidado diario');");  // id = 2
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Fragancias');");      // id = 3
        //endregion

        //region PRODUCTOS

        // ==================================
        // REGION PRODUCTOS MAQUILLAJE
        // ==================================

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Labial de Remolacha', 'Labial con color natural extraído de vegetales, hidratante y nutritivo.', 1200, 'Aceite de coco, cera de abejas, extracto de remolacha', 'labial_remolacha', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Rubor Mineral Rosa', 'Textura ligera con pigmentos naturales para un acabado fresco.', 1400, 'Arcilla rosa, óxido de zinc, mica natural', 'rubor_rosaIMG', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Sombras Brillantes', 'Acabado metálico con ingredientes naturales.', 1500, 'Mica, aceite de jojoba, arcilla diferentes colores', 'sombrasIMG', 1);");


        // ==================================
        // REGION PRODUCTOS CUIDADO DIARIO
        // ==================================

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Crema Hidratante Facial', 'Crema ligera para hidratar y proteger la piel todo el día.', 1800, 'Aloe vera, aceite de jojoba, manteca de karité', 'crema_hidratanteIMG', 2);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Gel Limpiador Suave', 'Gel facial con ingredientes naturales que limpia sin resecar.', 1200, 'Extracto de pepino, aloe vera, aceite de almendra', 'gel_limpiadorIMG', 2);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Tónico Facial Natural', 'Tónico refrescante que equilibra y tonifica la piel.', 1400, 'Agua de rosas, hamamelis, extracto de manzanilla', 'tonico_facialIMG', 2);");


        // ==================================
        // REGION PRODUCTOS FRAGANCIAS
        // ==================================

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Perfume Floral Natural', 'Fragancia fresca con notas de flores silvestres y cítricos.', 2500, 'Aceite esencial de lavanda, bergamota, rosa', 'perfume_floralIMG', 3);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Eau de Cologne Cítrica', 'Fragancia ligera y refrescante con aroma a limón y menta.', 2200, 'Aceite esencial de limón, menta, verbena', 'cologne_citricaIMG', 3);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Aromático Amaderado', 'Perfume con notas cálidas y naturales de madera y especias.', 2800, 'Aceite de cedro, sándalo, canela', 'aromatico_amaderadoIMG', 3);");

        //endregion
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar tablas si existen y volver a crearlas
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARRITO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIAS);
        onCreate(db);
    }
}

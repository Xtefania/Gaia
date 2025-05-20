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

        db.execSQL("CREATE TABLE " + TABLE_CARRITO + " (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "producto_id INTEGER NOT NULL, " + "cantidad INTEGER NOT NULL DEFAULT 1, " + "FOREIGN KEY(producto_id) REFERENCES " + TABLE_PRODUCTOS + "(id));");
        //endregion

        //region CREAR REGISTRO TABLAS
        //region - CATEGORIAS
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Maquillaje');");      // id = 1
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Cuidado diario');");  // id = 2
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Fragancias');");      // id = 3
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Item 4');");      // id =
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Item 5');");      // id =
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Item 6');");      // id =
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Item 7');");      // id =
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Item 8');");      // id =
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Item 9');");      // id =
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Item 10');");      // id =
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Item 11');");      // id =
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Item 12');");      // id =
        //endregion

        //region PRODUCTOS

        // ===========================
        // REGION PRODUCTOS MAQUILLAJE
        // ===========================

        // Labiales (Subcategoría ID: 1)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Labial de Remolacha', 'Color natural extraído de vegetales.', 1200, 'Aceite de coco, cera de abejas, extracto de remolacha', 'labial_remolacha', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Labial Nude', 'Labial orgánico con acabado suave y natural.', 1500, 'Aceite de jojoba, manteca de cacao, cera de abejas', 'labial_nude', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Labial Rosa Pálido', 'Labial con pigmentos naturales para un toque sutil.', 1400, 'Manteca de karité, aceite de argán, cera de abejas', 'labial_rosa', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Labial Rojo Intenso', 'Color vibrante con fórmula natural y duradera.', 1600, 'Cera de abejas, aceite de oliva, extracto de remolacha', 'labial_rojo', 1);");

        // Rubores (Subcategoría ID: 2)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Rubor Mineral Rosa', 'Rubor de textura ligera con pigmentos naturales.', 1400, 'Arcilla rosa, óxido de zinc, mica natural', 'rubor_rosa', 2);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Rubor Natural Coral', 'Rubor en polvo de origen mineral para un acabado suave.', 1350, 'Mica, arcilla roja, aceite de jojoba', 'rubor_coral', 2);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Rubor Terracota', 'Rubor compacto con tono cálido y natural.', 15000, 'Aceite de jojoba, óxido de hierro, arcilla', 'rubor_terracota', 2);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Rubor Natural Rosa Claro', 'Rubor suave con ingredientes orgánicos.', 1200, 'Arcilla rosa, aceite de oliva, mica', 'rubor_claro', 2);");

        // Sombras (Subcategoría ID: 3)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Sombra de Ojos Orgánica', 'Sombra de ojos mineral con pigmentos naturales.', 1200, 'Mica, arcilla, óxido de zinc', 'sombra_organica', 3);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Sombra en Polvo Rosa', 'Sombra suave con acabado mate.', 13.00, 'Mica, almidón de maíz, arcilla roja', 'sombra_rosa', 3);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Sombra Natural Marrón', 'Sombra mate en tonos naturales para un look diario.', 12000, 'Mica, arcilla marrón, óxido de hierro', 'sombra_marron', 3);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Sombra Brillante Dorada', 'Sombra brillante con acabado metálico y orgánico.', 15.00, 'Mica, aceite de jojoba, arcilla dorada', 'sombra_dorada', 3);");

        // Delineadores (Subcategoría ID: 4)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Delineador Natural Negro', 'Delineador líquido a base de ingredientes naturales para ojos intensos.', 12000, 'Aceite de ricino, cera de abejas, pigmento mineral', 'delineador_negro', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Delineador Marrón Suave', 'Delineador suave para un look natural.', 15000, 'Aceite de ricino, arcilla marrón, pigmento mineral', 'delineador_marron', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Delineador Líquido Verde', 'Delineador ecológico de acabado brillante.', 16.00, 'Cera de abejas, aceite de oliva, pigmento mineral', 'delineador_verde', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Delineador de Ojos Azul', 'Delineador mineral de tono azul para un toque vibrante.', 15.50, 'Aceite de ricino, cera de abejas, pigmentos naturales', 'delineador_azul', 1);");

        // ===========================
        // REGION PRODUCTOS CUIDADO DIARIO
        // ===========================

        // Jabones (categoria_id = 5)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Jabón de Lavanda', 'Jabón natural con propiedades relajantes.', 5.00, 'Aceite esencial de lavanda, aceite de oliva, manteca de karité', 'jabon1', 5);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Jabón de Miel', 'Jabón suave con propiedades hidratantes.', 4.50, 'Miel orgánica, aceite de coco, aceite esencial de manzanilla', 'jabon2', 5);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Jabón de Rosa Mosqueta', 'Jabón natural regenerador para la piel.', 6.00, 'Aceite de rosa mosqueta, aceite de oliva, cera de abejas', 'jabon3', 5);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Jabón de Té Verde', 'Jabón antioxidante con extracto de té verde.', 5.50, 'Extracto de té verde, aceite de oliva, manteca de karité', 'jabon4', 5);");

        // Desodorantes (categoria_id = 6)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Desodorante Natural de Lavanda', 'Desodorante natural sin aluminio.', 7.00, 'Aceite de coco, bicarbonato de sodio, aceite esencial de lavanda', 'desodorante1', 6);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Desodorante de Árbol de Té', 'Desodorante antibacteriano y natural.', 6.50, 'Aceite de coco, bicarbonato de sodio, aceite esencial de árbol de té', 'desodorante2', 6);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Desodorante de Cítricos', 'Desodorante refrescante con extracto de cítricos.', 6.00, 'Aceite de coco, bicarbonato de sodio, extracto de limón', 'desodorante3', 6);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Desodorante de Romero', 'Desodorante natural con propiedades desinfectantes.', 7.50, 'Aceite de coco, bicarbonato de sodio, aceite esencial de romero', 'desodorante4', 6);");

        // Exfoliantes (categoria_id = 7)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Exfoliante de Café', 'Exfoliante natural con café para rejuvenecer la piel.', 9.00, 'Café molido, aceite de oliva, azúcar moreno', 'exfoliante1', 7);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Exfoliante de Azúcar', 'Exfoliante suave con azúcar para todo tipo de piel.', 8.50, 'Azúcar, aceite de almendras, extracto de pepita de uva', 'exfoliante2', 7);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Exfoliante de Sal Marina', 'Exfoliante corporal con sal marina y aceites esenciales.', 10.00, 'Sal marina, aceite de coco, aceite esencial de menta', 'exfoliante3', 7);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Exfoliante de Avena', 'Exfoliante suave con avena para piel sensible.', 8.00, 'Avena molida, aceite de oliva, miel', 'exfoliante4', 7);");

        // Cremas corporales (categoria_id = 8)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Crema Corporal de Lavanda', 'Crema hidratante con aceite esencial de lavanda.', 1200, 'Aceite esencial de lavanda, manteca de karité, aceite de coco', 'crema1', 8);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Crema Corporal de Rosa Mosqueta', 'Crema regeneradora para la piel.', 13.00, 'Aceite de rosa mosqueta, manteca de karité, aceite de oliva', 'crema2', 8);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Crema Corporal de Manteca de Karité', 'Crema ultra hidratante con manteca de karité.', 12000, 'Manteca de karité, aceite de oliva, aceite de coco', 'crema3', 8);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Crema Corporal de Naranja', 'Crema energizante con extracto de naranja.', 12.50, 'Aceite de oliva, manteca de karité, extracto de naranja', 'crema4', 8);");


        // ===========================
        // REGION PRODUCTOS FRAGANCIAS
        // ===========================

        // Perfumes  (Subcategoría ID: 9)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Perfume Floral de Lavanda', 'Un perfume fresco con notas de lavanda y aceites esenciales.', 20.00, 'Aceite esencial de lavanda, aceite de jojoba, agua destilada', 'perfume_lavanda', 9);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Perfume Amaderado', 'Perfume con un toque cálido y terroso.', 22.00, 'Aceite esencial de sándalo, aceite de cedro, aceite de oliva', 'perfume_amaderado', 9);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Perfume Cítrico Refrescante', 'Una mezcla revitalizante de cítricos y aceites naturales.', 18.00, 'Aceite esencial de naranja, aceite de limón, aceite de coco', 'perfume_citrico', 9);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Perfume Floral de Rosa', 'Un perfume delicado con extracto de rosa y aceites florales.', 21.00, 'Aceite esencial de rosa, aceite de almendra, agua de rosas', 'perfume_rosa', 9);");

        // Brumas Corporales (Subcategoría ID: 10)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Bruma Corporal de Lavanda', 'Bruma refrescante con notas de lavanda y aceites orgánicos.', 15.00, 'Aceite esencial de lavanda, agua de rosa mosqueta, agua destilada', 'bruma_lavanda', 10);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Bruma Corporal Cítrica', 'Bruma ligera y energizante con aceites cítricos.', 14.50, 'Aceite esencial de limón, aceite esencial de naranja, agua destilada', 'bruma_citrica', 10);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Bruma Corporal de Rosa Mosqueta', 'Hidrata y refresca con extracto de rosa mosqueta.', 16.00, 'Agua de rosa mosqueta, aceite de jojoba, aceites esenciales', 'bruma_rosa_mosqueta', 10);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Bruma Corporal de Almendra', 'Suave bruma corporal con notas dulces de almendra.', 17.00, 'Aceite de almendra, agua destilada, aceite esencial de vainilla', 'bruma_almendra', 10);");

        // Aceites Esenciales (Subcategoría ID: 11)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Aceite Esencial de Lavanda', 'Aceite esencial puro de lavanda para relajación.', 1200, 'Aceite esencial de lavanda', 'aceite_lavanda', 11);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Aceite Esencial de Eucalipto', 'Aceite esencial purificante con aroma fresco.', 15000, 'Aceite esencial de eucalipto', 'aceite_eucalipto', 11);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Aceite Esencial de Menta', 'Aceite refrescante ideal para la mente y el cuerpo.', 11.50, 'Aceite esencial de menta', 'aceite_menta', 11);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Aceite Esencial de Árbol de Té', 'Aceite esencial con propiedades antisépticas y purificantes.', 12000, 'Aceite esencial de árbol de té', 'aceite_arbol_te', 11);");

        // Colonias (Subcategoría ID: 12)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Colonia Fresca de Naranja', 'Colonia revitalizante con un toque cítrico.', 16.00, 'Aceite esencial de naranja, agua destilada, aceite de jojoba', 'colonia_naranja', 12);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Colonia Floral de Jazmín', 'Colonia suave con extracto de jazmín para un aroma fresco.', 18.00, 'Extracto de jazmín, aceite de almendra, agua destilada', 'colonia_jazmin', 12);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Colonia de Lavanda Relajante', 'Colonia fresca con notas florales de lavanda.', 17.00, 'Aceite esencial de lavanda, agua de rosas, aceite de oliva', 'colonia_lavanda', 12);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, categoria_id) VALUES (" + "'Colonia Refrescante de Limón', 'Una colonia refrescante y energética con extracto de limón.', 15.00, 'Aceite esencial de limón, agua destilada, aceite de coco', 'colonia_limon', 12);");

        //endregion
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

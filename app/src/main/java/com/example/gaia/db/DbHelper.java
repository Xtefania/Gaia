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

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        //region CREAR TABLAS
        db.execSQL("CREATE TABLE " + TABLE_CATEGORIAS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABLE_SUBCATEGORIAS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "categoria_id INTEGER NOT NULL, " +
                "FOREIGN KEY (categoria_id) REFERENCES " + TABLE_CATEGORIAS + "(id));");

        db.execSQL("CREATE TABLE " + TABLE_PRODUCTOS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "descripcion TEXT, " +
                "precio REAL, " +
                "ingredientes TEXT, " +
                "imagen TEXT, " +
                "subcategoria_id INTEGER NOT NULL, " +
                "FOREIGN KEY (subcategoria_id) REFERENCES " + TABLE_SUBCATEGORIAS + "(id));");
        //endregion

        //region CATEGORIAS
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Maquillaje');");      // id = 1
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Cuidado diario');");  // id = 2
        db.execSQL("INSERT INTO t_categorias (nombre) VALUES ('Fragancias');");      // id = 3
        //endregion

        //region SUBCATEGORIAS MAQUILLAJE
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Labiales', 1);");       // id = 1
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Rubores', 1);");        // id = 2
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Sombras', 1);");        // id = 3
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Delineadores', 1);");   // id = 4
        //endregion

        //region SUBCATEGORIAS CUIDADO DIARIO
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Jabones', 2);");          // id = 5
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Desodorantes', 2);");     // id = 6
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Exfoliantes', 2);");      // id = 7
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Cremas corporales', 2);");// id = 8
        //endregion

        //region SUBCATEGORIAS FRAGANCIAS
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Perfumes', 3);");           // id = 9
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Brumas corporales', 3);"); // id = 10
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Aceites esenciales', 3);"); // id = 11
        db.execSQL("INSERT INTO t_subcategorias (nombre, categoria_id) VALUES ('Colonias', 3);");           // id = 12
        //endregion

        //region PRODUCTOS

        // ===========================
        // REGION PRODUCTOS MAQUILLAJE
        // ===========================

        // Labiales (Subcategoría ID: 1)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Labial de Remolacha', 'Color natural extraído de vegetales.', 12.50, 'Aceite de coco, cera de abejas, extracto de remolacha', 'labial_remolacha.png', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Labial Nude', 'Labial orgánico con acabado suave y natural.', 15.00, 'Aceite de jojoba, manteca de cacao, cera de abejas', 'labial_nude.png', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Labial Rosa Pálido', 'Labial con pigmentos naturales para un toque sutil.', 14.00, 'Manteca de karité, aceite de argán, cera de abejas', 'labial_rosa.png', 1);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Labial Rojo Intenso', 'Color vibrante con fórmula natural y duradera.', 16.00, 'Cera de abejas, aceite de oliva, extracto de remolacha', 'labial_rojo.png', 1);");

        // Rubores (Subcategoría ID: 2)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Rubor Mineral Rosa', 'Rubor de textura ligera con pigmentos naturales.', 14.00, 'Arcilla rosa, óxido de zinc, mica natural', 'rubor_rosa.png', 2);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Rubor Natural Coral', 'Rubor en polvo de origen mineral para un acabado suave.', 13.50, 'Mica, arcilla roja, aceite de jojoba', 'rubor_coral.png', 2);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Rubor Terracota', 'Rubor compacto con tono cálido y natural.', 15.00, 'Aceite de jojoba, óxido de hierro, arcilla', 'rubor_terracota.png', 2);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Rubor Natural Rosa Claro', 'Rubor suave con ingredientes orgánicos.', 12.00, 'Arcilla rosa, aceite de oliva, mica', 'rubor_claro.png', 2);");

        // Sombras (Subcategoría ID: 3)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Sombra de Ojos Orgánica', 'Sombra de ojos mineral con pigmentos naturales.', 12.00, 'Mica, arcilla, óxido de zinc', 'sombra_organica.png', 3);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Sombra en Polvo Rosa', 'Sombra suave con acabado mate.', 13.00, 'Mica, almidón de maíz, arcilla roja', 'sombra_rosa.png', 3);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Sombra Natural Marrón', 'Sombra mate en tonos naturales para un look diario.', 14.00, 'Mica, arcilla marrón, óxido de hierro', 'sombra_marron.png', 3);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Sombra Brillante Dorada', 'Sombra brillante con acabado metálico y orgánico.', 15.00, 'Mica, aceite de jojoba, arcilla dorada', 'sombra_dorada.png', 3);");

        // Delineadores (Subcategoría ID: 4)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Delineador Natural Negro', 'Delineador líquido a base de ingredientes naturales para ojos intensos.', 14.00, 'Aceite de ricino, cera de abejas, pigmento mineral', 'delineador_negro.png', 4);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Delineador Marrón Suave', 'Delineador suave para un look natural.', 13.50, 'Aceite de ricino, arcilla marrón, pigmento mineral', 'delineador_marron.png', 4);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Delineador Líquido Verde', 'Delineador ecológico de acabado brillante.', 16.00, 'Cera de abejas, aceite de oliva, pigmento mineral', 'delineador_verde.png', 4);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Delineador de Ojos Azul', 'Delineador mineral de tono azul para un toque vibrante.', 15.50, 'Aceite de ricino, cera de abejas, pigmentos naturales', 'delineador_azul.png', 4);");

        // ===========================
        // REGION PRODUCTOS CUIDADO DIARIO
        // ===========================

        // Jabones (subcategoria_id = 5)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Jabón de Lavanda', 'Jabón natural con propiedades relajantes.', 5.00, 'Aceite esencial de lavanda, aceite de oliva, manteca de karité', 'jabon1.png', 5);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Jabón de Miel', 'Jabón suave con propiedades hidratantes.', 4.50, 'Miel orgánica, aceite de coco, aceite esencial de manzanilla', 'jabon2.png', 5);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Jabón de Rosa Mosqueta', 'Jabón natural regenerador para la piel.', 6.00, 'Aceite de rosa mosqueta, aceite de oliva, cera de abejas', 'jabon3.png', 5);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Jabón de Té Verde', 'Jabón antioxidante con extracto de té verde.', 5.50, 'Extracto de té verde, aceite de oliva, manteca de karité', 'jabon4.png', 5);");

        // Desodorantes (subcategoria_id = 6)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Desodorante Natural de Lavanda', 'Desodorante natural sin aluminio.', 7.00, 'Aceite de coco, bicarbonato de sodio, aceite esencial de lavanda', 'desodorante1.png', 6);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Desodorante de Árbol de Té', 'Desodorante antibacteriano y natural.', 6.50, 'Aceite de coco, bicarbonato de sodio, aceite esencial de árbol de té', 'desodorante2.png', 6);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Desodorante de Cítricos', 'Desodorante refrescante con extracto de cítricos.', 6.00, 'Aceite de coco, bicarbonato de sodio, extracto de limón', 'desodorante3.png', 6);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Desodorante de Romero', 'Desodorante natural con propiedades desinfectantes.', 7.50, 'Aceite de coco, bicarbonato de sodio, aceite esencial de romero', 'desodorante4.png', 6);");

        // Exfoliantes (subcategoria_id = 7)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Exfoliante de Café', 'Exfoliante natural con café para rejuvenecer la piel.', 9.00, 'Café molido, aceite de oliva, azúcar moreno', 'exfoliante1.png', 7);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Exfoliante de Azúcar', 'Exfoliante suave con azúcar para todo tipo de piel.', 8.50, 'Azúcar, aceite de almendras, extracto de pepita de uva', 'exfoliante2.png', 7);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Exfoliante de Sal Marina', 'Exfoliante corporal con sal marina y aceites esenciales.', 10.00, 'Sal marina, aceite de coco, aceite esencial de menta', 'exfoliante3.png', 7);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Exfoliante de Avena', 'Exfoliante suave con avena para piel sensible.', 8.00, 'Avena molida, aceite de oliva, miel', 'exfoliante4.png', 7);");

        // Cremas corporales (subcategoria_id = 8)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Crema Corporal de Lavanda', 'Crema hidratante con aceite esencial de lavanda.', 12.00, 'Aceite esencial de lavanda, manteca de karité, aceite de coco', 'crema1.png', 8);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Crema Corporal de Rosa Mosqueta', 'Crema regeneradora para la piel.', 13.00, 'Aceite de rosa mosqueta, manteca de karité, aceite de oliva', 'crema2.png', 8);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Crema Corporal de Manteca de Karité', 'Crema ultra hidratante con manteca de karité.', 14.00, 'Manteca de karité, aceite de oliva, aceite de coco', 'crema3.png', 8);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Crema Corporal de Naranja', 'Crema energizante con extracto de naranja.', 12.50, 'Aceite de oliva, manteca de karité, extracto de naranja', 'crema4.png', 8);");


        // ===========================
        // REGION PRODUCTOS FRAGANCIAS
        // ===========================

        // Perfumes  (Subcategoría ID: 9)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Perfume Floral de Lavanda', 'Un perfume fresco con notas de lavanda y aceites esenciales.', 20.00, 'Aceite esencial de lavanda, aceite de jojoba, agua destilada', 'perfume_lavanda.png', 9);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Perfume Amaderado', 'Perfume con un toque cálido y terroso.', 22.00, 'Aceite esencial de sándalo, aceite de cedro, aceite de oliva', 'perfume_amaderado.png', 9);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Perfume Cítrico Refrescante', 'Una mezcla revitalizante de cítricos y aceites naturales.', 18.00, 'Aceite esencial de naranja, aceite de limón, aceite de coco', 'perfume_citrico.png', 9);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Perfume Floral de Rosa', 'Un perfume delicado con extracto de rosa y aceites florales.', 21.00, 'Aceite esencial de rosa, aceite de almendra, agua de rosas', 'perfume_rosa.png', 9);");

        // Brumas Corporales (Subcategoría ID: 10)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Bruma Corporal de Lavanda', 'Bruma refrescante con notas de lavanda y aceites orgánicos.', 15.00, 'Aceite esencial de lavanda, agua de rosa mosqueta, agua destilada', 'bruma_lavanda.png', 10);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Bruma Corporal Cítrica', 'Bruma ligera y energizante con aceites cítricos.', 14.50, 'Aceite esencial de limón, aceite esencial de naranja, agua destilada', 'bruma_citrica.png', 10);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Bruma Corporal de Rosa Mosqueta', 'Hidrata y refresca con extracto de rosa mosqueta.', 16.00, 'Agua de rosa mosqueta, aceite de jojoba, aceites esenciales', 'bruma_rosa_mosqueta.png', 10);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Bruma Corporal de Almendra', 'Suave bruma corporal con notas dulces de almendra.', 17.00, 'Aceite de almendra, agua destilada, aceite esencial de vainilla', 'bruma_almendra.png', 10);");

        // Aceites Esenciales (Subcategoría ID: 11)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Aceite Esencial de Lavanda', 'Aceite esencial puro de lavanda para relajación.', 12.00, 'Aceite esencial de lavanda', 'aceite_lavanda.png', 11);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Aceite Esencial de Eucalipto', 'Aceite esencial purificante con aroma fresco.', 13.50, 'Aceite esencial de eucalipto', 'aceite_eucalipto.png', 11);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Aceite Esencial de Menta', 'Aceite refrescante ideal para la mente y el cuerpo.', 11.50, 'Aceite esencial de menta', 'aceite_menta.png', 11);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Aceite Esencial de Árbol de Té', 'Aceite esencial con propiedades antisépticas y purificantes.', 14.00, 'Aceite esencial de árbol de té', 'aceite_arbol_te.png', 11);");

        // Colonias (Subcategoría ID: 12)
        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Colonia Fresca de Naranja', 'Colonia revitalizante con un toque cítrico.', 16.00, 'Aceite esencial de naranja, agua destilada, aceite de jojoba', 'colonia_naranja.png', 12);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Colonia Floral de Jazmín', 'Colonia suave con extracto de jazmín para un aroma fresco.', 18.00, 'Extracto de jazmín, aceite de almendra, agua destilada', 'colonia_jazmin.png', 12);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Colonia de Lavanda Relajante', 'Colonia fresca con notas florales de lavanda.', 17.00, 'Aceite esencial de lavanda, agua de rosas, aceite de oliva', 'colonia_lavanda.png', 12);");

        db.execSQL("INSERT INTO t_productos (nombre, descripcion, precio, ingredientes, imagen, subcategoria_id) VALUES (" +
                "'Colonia Refrescante de Limón', 'Una colonia refrescante y energética con extracto de limón.', 15.00, 'Aceite esencial de limón, agua destilada, aceite de coco', 'colonia_limon.png', 12);");

        //endregion
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar tablas si existen y volver a crearlas
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBCATEGORIAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIAS);
        onCreate(db);
    }
}

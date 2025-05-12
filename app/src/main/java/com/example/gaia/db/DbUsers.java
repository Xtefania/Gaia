package com.example.gaia.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsers extends DbHelper{

    Context context;
    public DbUsers(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertUser(String nombre, String rol) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("rol", rol);

            id = db.insert(TABLE_USERS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }
}

package com.jhon.usosql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "miBaseDeDatos.db";
    private static final int DATABASE_VERSION = 1;
    // Constructor
    public MiDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Crear tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "edad INTEGER)";
        db.execSQL(CREATE_TABLE);
    }
    // Actualizar base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
    public void insertarUsuario(String nombre, int edad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("edad", edad);
        db.insert("usuarios", null, valores);
        db.close();
    }
    public Cursor obtenerUsuarios() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM usuarios", null);
    }
    public void mostrarUsuarios() {
        Cursor cursor = obtenerUsuarios();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String nombre =
                        cursor.getString(cursor.getColumnIndex("nombre"));
                @SuppressLint("Range") int edad = cursor.getInt(cursor.getColumnIndex("edad"));
                System.out.println("Nombre: " + nombre + ", Edad: " + edad);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    public void eliminarUsuario(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("usuarios", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}

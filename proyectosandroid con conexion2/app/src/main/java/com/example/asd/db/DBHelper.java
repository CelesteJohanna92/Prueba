package com.example.asd.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Coctelis.db";
    private static final String TABLE_USUARIO = "usuarios";
    private static final String TABLE_CATEGORIA ="categorias";
    private static final String TABLE_RECETA = "recetas";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USUARIO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "correo TEXT NOT NULL," +
                "contrasena TEXT NOT NULL)");


        //TABLA RECETAS
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_RECETA + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_categoria INTEGER NOT NULL," +
                "id_usuario INTEGER NOT NULL," +
                "nombre TEXT NOT NULL," +
                "ingredientes TEXT NOT NULL," +
                "instrucciones TEXT NOT NULL," +
                "imagen BLOB NOT NULL,"+
                "FOREIGN KEY (id_categoria) REFERENCES categoria(id),"+
                "FOREIGN KEY (id_usuario) REFERENCES usuario(id))");

        //TABLA CATEGORIAS
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS categoria (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECETA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIA);
        onCreate(sqLiteDatabase);
        
    }
    public void agregarUsuarios(String nombre, String apellido, String correo, String contrasena){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd != null){
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("correo", correo);
            values.put("contrasena", contrasena);

            long result = bd.insert(TABLE_USUARIO, null, values);

            bd.close();
        }
    }
    public void agregarReceta(int idCategoria, int idUsuario, String nombre, String ingredientes, String instrucciones, byte[] imagen) {
        SQLiteDatabase bd=getWritableDatabase();
        if (bd != null) {
            ContentValues values = new ContentValues();
            values.put("id_categoria", idCategoria);
            values.put("id_usuario", idUsuario);
            values.put("nombre", nombre);
            values.put("ingredientes", ingredientes);
            values.put("instrucciones", instrucciones);
            values.put("imagen", imagen);

            long result = bd.insert(TABLE_RECETA, null, values);

            bd.close();

        }
    }
}



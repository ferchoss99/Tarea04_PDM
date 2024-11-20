package com.example.tarea2menus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Usuarios
    //String sqlCreate = "CREATE TABLE Usuarios (codigo INTEGER, nombre TEXT)";
    String sqlCreate = "CREATE TABLE IF NOT EXISTS Usuarios (" +
            "codigo TEXT , " +
            "nombre TEXT NOT NULL  PRIMARY KEY, " +
            "email TEXT );";
    public UsuariosSQLiteHelper(Context contexto, String nombre,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {
        Log.i("finalll", "Entro onUpgrade ");
    /*/NOTA: Por simplicidad del ejemplo aquí utilizamos directamente
    //la opción de eliminar la tabla anterior y crearla de nuevo
    //vacía con el nuevo formato.
    //Sin embargo lo normal será que haya que migrar datos de la
    //tabla antigua a la nueva, por lo que este método debería
    //ser más elaborado.
    //Se elimina la versión anterior de la tabla */
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}
package com.example.tarea2menus;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

public class DatosUsuario extends BaseNavBar {

    List<String> lista = new ArrayList<>();
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_datos_usuario);
        getLayoutInflater().inflate(R.layout.activity_datos_usuario, findViewById(R.id.container));


        // Crear una instancia del helper
        //Importante tener todas las instancias que ocupemos la misma version.
        UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "DBUsuarios", null, 12);

        // Abrir la base de datos en modo escritura
        SQLiteDatabase db = usdbh.getWritableDatabase();


        SharedPreferences sharedPreferences = getSharedPreferences("UsuarioActual", MODE_PRIVATE);
        String nombre = sharedPreferences.getString("usuario", "Desconocido");

        String query = "SELECT Usuarios.nombre,Usuarios.email,Datos.genero ,Datos.meta ,Datos.comentario  " +
                "FROM Usuarios " +
                "INNER JOIN Datos ON Usuarios.nombre = Datos.nombre " +
                "WHERE Usuarios.nombre = ?";

        String[] args = {nombre}; // Argumentos para el WHERE
        //Nota importante se la funcion de los argumentos es evitar la inyección de SQL

        Cursor cursor = db.rawQuery(query, args);

        String nombre1 = "";
        String email1 = "";
        String genero1 = "";
        String meta1 = "";
        String comentario1 = "";


        // Procesar los resultados
        if (cursor.moveToFirst()) {
            do {
                nombre1 = cursor.getString(0); // Primera columna: Usuarios.nombre
                email1 = cursor.getString(1); // Segunda columna: Pedidos.producto
                genero1 = cursor.getString(2); // Tercera columna: Pedidos.producto
                meta1 = cursor.getString(3); // Cuarta columna: Pedidos.producto
                comentario1 = cursor.getString(4); // Quinta columna: Pedidos.producto


                Log.d("Resultado", "Nombre: " + nombre1 + ", email: " + email1 + ", genero: " + genero1
                        + ", meta: " + meta1+ ", comentario: " + comentario1);

            } while (cursor.moveToNext());
        }


        // Cerrar cursor y base de datos
        cursor.close();

        String query2 = "SELECT Usuarios.nombre,Dias.dia  " +
                "FROM Usuarios " +
                "INNER JOIN Dias ON Usuarios.nombre = Dias.nombre " +
                "WHERE Usuarios.nombre = ?";

        String[] args2 = {nombre}; // Argumentos para el WHERE
        //Nota importante se la funcion de los argumentos es evitar la inyección de SQL

        Cursor cursor2 = db.rawQuery(query2, args2);

        // Procesar los resultados
        if (cursor2.moveToFirst()) {
            do {

                String dias1= cursor2.getString(1); // Segunda columna: Pedidos.producto

                Log.i("ddd", "onCreate: "+dias1);
                lista.add(dias1); // Agrega un valor en cada iteración



            } while (cursor2.moveToNext());
        }

        cursor2.close();




        TextView textView = findViewById(R.id.text1);
        String resultado = String.join(", ", lista);
        textView.setText("Nombre: " + nombre + "\n Email: " + email1 + "\n Genero: " + genero1
                + "\n Meta: " + meta1+ "\n Comentario: " + comentario1+"\n Dias de entrenamiento "+resultado);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.datos), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }



}
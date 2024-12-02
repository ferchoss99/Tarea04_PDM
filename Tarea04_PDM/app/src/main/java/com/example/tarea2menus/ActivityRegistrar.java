package com.example.tarea2menus;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityRegistrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);

        // Crear una instancia del helper
        //Importante tener todas las instancias que ocupemos la misma version.
        UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "DBUsuarios", null, 12);

        // Abrir la base de datos en modo escritura
        SQLiteDatabase db = usdbh.getWritableDatabase();


        final Button btnRegistrado = (Button)findViewById(R.id.BtnRegistrado);
        //Implementamos el evento "click" del boton registrar
        btnRegistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtenemos los datos del usuario
                EditText nombre = (EditText)findViewById(R.id.TxtUsuario2);
                EditText contrasenia = (EditText)findViewById(R.id.TxtPassword2);
                EditText email = (EditText)findViewById(R.id.emailInput);

                ContentValues values = new ContentValues();

                try {


                    values.put("codigo",contrasenia.getText().toString());
                    //Log.i("dfsfdsdffd", contrasenia.getText().toString());
                    values.put("nombre", nombre.getText().toString());
                    values.put("email", email.getText().toString());
                    // Inserta o lanza error , depende si los valores ingresados son correctos
                    db.insertOrThrow("Usuarios", null, values);  // Usa insertOrThrow para lanzar una excepción en caso de error

                } catch (SQLiteConstraintException e) {
                    Log.e("SQLite", e.toString() + values);
                }


                /*
                // Consultar datos de la tabla 'Usuarios' , rawquery : Directamente codigo sql
                //Vamos a ver todos los datos
                Cursor c = db.rawQuery("SELECT * FROM Usuarios", null);

                if (c.moveToFirst()) {
                    do {
                        String codigo1 = c.getString(0);
                        String nombre1 = c.getString(1);
                        String  email1= c.getString(2);


                        Log.d("DB", "codigo: " + codigo1 + ", Nombre: " + nombre1 +", email: " + email1);
                    } while (c.moveToNext());
                }else{
                    Log.d("DB", "vacio");

                }

                // Cerrar el cursor y la base de datos
                c.close();
                 */




                db.close();

                //Creamos el Intent y regresamos a la pagina de Bienvenida
                Intent intent =
                        new Intent(ActivityRegistrar.this, MainActivity.class);

                //Iniciamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registrar), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
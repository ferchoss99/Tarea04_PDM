package com.example.tarea2menus;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Para la base de datos
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 12);
        //db permite hacer operaciones en la base de datos
        SQLiteDatabase db = usdbh.getWritableDatabase();


        //Cuanta la cantidad de entradas que tiene la tabla completa
        //long count = DatabaseUtils.queryNumEntries(db, "Usuarios");
        //Log.d("SQLite", "Número de filas: " + count);


        //Implementamos el evento "click" del botón "Aceptar"
        final Button btnHola = (Button)findViewById(R.id.BtnAceptar);

        btnHola.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //Recuperamos los datos de entrada
                EditText nombre = (EditText)findViewById(R.id.TxtUsuario);
                EditText contrasenia = (EditText)findViewById(R.id.TxtPassword);


                //Recarga la actividad si no tiene  algun campo
                if (nombre.getText().toString().isEmpty()||contrasenia.getText().toString().isEmpty() ) {
                    Intent intent =
                            new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }


                //Pide a la base de datos los elementos que coincidan con el nombre
                //nombre es la la llave y es unica
                String[] campos = new String[] {"codigo", "nombre"};
                String[] args = new String[] {nombre.getText().toString()};


                //Utiliza el cursor para recorrer en las filas encontradas
                Cursor c = db.query("Usuarios", campos, "nombre=?", args, null, null, null);
                if (c.moveToFirst()) {
                    String codigo1 = c.getString(0);
                    String nombre1 = c.getString(1);
                    do {

                        //Si el nombre y la contrasenia coinciden entonces entra a la aplicación
                        if (nombre1.equals(nombre.getText().toString())&&codigo1.equals(contrasenia.getText().toString())){

                            c.close();
                            db.close();

                            //Creamos el Intent y le mandamos el nombre para aparezca en la pagina principal
                            Intent intent =
                                    new Intent(MainActivity.this, Inicio1Activity.class);

                            //Creamos la información a pasar entre actividades
                            Bundle b = new Bundle();
                            b.putString("NOMBRE", nombre.getText().toString());
                            //Añadimos la información al intent
                            intent.putExtras(b);
                            //Iniciamos la nueva actividad
                            startActivity(intent);
                            //Aqui es nuy util para que ya no regrese a la actividad la cierra por completo
                            // asi que la saca de la aplicación y obliga a ingresar de nuevo
                            finish();

                        }
                        else{
                            c.close();
                            //db.close();
                            Intent intent =
                                    new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }


                        //Log.d("DB", "codigo: " + codigo1 + ", Nombre: " + nombre1 );
                    } while (c.moveToNext());


                }else{

                    //Sirve para dar una breve notificación
                    Toast.makeText(MainActivity.this, "Necesitas registrar un usuario", Toast.LENGTH_SHORT).show();
                    //Cerramos el cursor y reiniciamos la actividad
                    c.close();
                   // db.close();
                    Intent intent =
                            new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);




                }
            }
        });


        //Boton del registro
        final Button btnRegistrar = (Button)findViewById(R.id.BtnRegistar);
        //Implementamos el evento "click" del boton registrar
        btnRegistrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(MainActivity.this, ActivityRegistrar.class);

                //Iniciamos la nueva actividad
                startActivity(intent);
                finish();


            }
        });

        //Esto ya venia
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
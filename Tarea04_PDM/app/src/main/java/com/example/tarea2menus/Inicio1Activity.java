package com.example.tarea2menus;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class Inicio1Activity extends BaseNavBar {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

//        setContentView(R.layout.activity_inicio1);
        //Hace lo mismo que el setContent pero dentro de un contenedor
        getLayoutInflater().inflate(R.layout.activity_inicio1, findViewById(R.id.container));



        // Crear una instancia del helper
        //Importante tener todas las instancias que ocupemos la misma version.
        UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "DBUsuarios", null, 12);

        // Abrir la base de datos en modo escritura
        SQLiteDatabase db = usdbh.getWritableDatabase();



        //Localizar los controles
        TextView txtSaludo = (TextView)findViewById(R.id.TextViewMain);
        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();

        //Construimos el mensaje a mostrar
        txtSaludo.setText("Hola " + bundle.getString("NOMBRE"));


        //Para guardar el nombre de usuario Actual

        SharedPreferences sharedPreferences = getSharedPreferences("UsuarioActual", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("usuario", bundle.getString("NOMBRE"));
        editor.apply(); // Guarda los cambios de forma asíncrona


        //Validar si ya existe el usuario y si ya  tiene registro de dias  o genero

        String[] campos = new String[] {"nombre","genero"};
        String[] args = new String[] {bundle.getString("NOMBRE")};
        //String[] args = new String[]{};
        Cursor c = db.query("Datos", campos, "nombre=? AND genero != '' ", args, null, null, null);


        String[] campos2 = new String[] {"nombre","dia"};
        String[] args2 = new String[] {bundle.getString("NOMBRE")};
        Cursor c2 = db.query("Dias", campos2, "nombre=?", args2, null, null, null);

        if(c.moveToFirst() || c2.moveToFirst()){
        //if(c.moveToFirst() ){
         c2.close();
         c.close();

            Intent intent = new Intent(Inicio1Activity.this, ExercisesGymActivity.class);
            startActivity(intent);

        }else{
            c2.close();
            c.close();

        }


        final String[] datos =
                new String[]{"Estar mas delgad@ ","Ser mas fuerte ","Salud","Evitar estres","Estetica","Aburrimiento","Otro"};
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        R.layout.spinner_item, datos);


        // Se utiliza para mostrar una lista desplegable
        Spinner cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
        adaptador.setDropDownViewResource(
                R.layout.spinner_item);
        cmbOpciones.setAdapter(adaptador);

        final Button btnHola = (Button)findViewById(R.id.BtnEnviar);

        //Implementamos el evento "click" del botón
        //Nota : poner el boton , antes del ViewCompart(Lo que ya estaba )
        btnHola.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                ///// Guardar info en la base de datos


                // Radio Boton del genero
                RadioGroup radioGroup = findViewById(R.id.gruporb);

                // Obtener el ID del RadioButton seleccionado
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                String selectedText="";
                // Verificar si hay una opción seleccionada
                if (selectedRadioButtonId != -1) {
                    // Obtener el RadioButton seleccionado
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                    // Obtener el texto del RadioButton seleccionado
                     selectedText = selectedRadioButton.getText().toString();

                    // Mostrar el valor seleccionado (puedes usarlo como desees)
                    //Toast.makeText(this, "Seleccionado: " + selectedText, Toast.LENGTH_SHORT).show();
                }
                //else {
                    // No se seleccionó ninguna opción
                  //  Toast.makeText(this, "No se ha seleccionado ninguna opción", Toast.LENGTH_SHORT).show();
                //}







                /////// EditText del comentario Personal
                // Obtener la referencia al EditText
                EditText editText = findViewById(R.id.TxtUsuario);

                // Obtener el texto ingresado (como Editable)
                String inputText = editText.getText().toString();

                // Mostrar el texto en un Toast o usarlo de otra forma
                //Toast.makeText(this, "Texto ingresado: " + inputText, Toast.LENGTH_SHORT).show();








                /////  Del spinner (lista desplegable)
                // Obtener la referencia al Spinner
                Spinner spinner = findViewById(R.id.CmbOpciones);

                // Obtener el valor seleccionado
                String selectedItem = spinner.getSelectedItem().toString();

                // Mostrar el valor seleccionado en un Toast o usarlo de otra forma
                //Toast.makeText(this, "Opción seleccionada: " + selectedItem, Toast.LENGTH_SHORT).show();







                ContentValues values = new ContentValues();

                try {

                    values.put("nombre", bundle.getString("NOMBRE"));
                    values.put("genero",selectedText);
                    values.put("meta", selectedItem);
                    values.put("comentario", inputText);

                    if(!selectedText.isEmpty()){
                        db.insertOrThrow("Datos", null, values);  // Usa insertOrThrow para lanzar una excepción en caso de error

                    }

                    // Inserta o lanza error , depende si los valores ingresados son correctos

                } catch (SQLiteConstraintException e) {
                    Log.e("SQLite", e.toString() + values);
                }

                //no es necesario borrar amenos que vaya a reocupar el objeto
                values.clear();  // Borra todos los valores de ContentValues.



                ////Para obtener la información de los checkbox ,Utiliza un for , para recorrer todos
                //los hijos(checkbox) del layout
                LinearLayout layout = findViewById(R.id.grupoCheckBoxs); // Contenedor de los CheckBox
                List<String> selectedOptions = new ArrayList<>();

                for (int i = 0; i < layout.getChildCount(); i++) {
                    View child = layout.getChildAt(i);
                    if (child instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) child;
                        if (checkBox.isChecked()) {
                            //selectedOptions.add(checkBox.getText().toString());
                            //aqui seria agregarlo a la base de datos
                            values.put("nombre", bundle.getString("NOMBRE"));
                            values.put("dia",checkBox.getText().toString());

                            try {

                                // Inserta o lanza error , depende si los valores ingresados son correctos
                                db.insertOrThrow("Dias", null, values);  // Usa insertOrThrow para lanzar una excepción en caso de error
                                printContentValues(values);
                                values.clear();  // Borra todos los valores de ContentValues.

                            } catch (SQLiteConstraintException e) {
                                Log.e("SQLite", e.toString() + values+ "hay un error grabe ");
                                values.clear();  // Borra todos los valores de ContentValues.

                            }



                        }
                    }
                }




                // Mostrar las opciones seleccionadas
                // Toast.makeText(this, "Opciones seleccionadas: " + selectedOptions.toString(), Toast.LENGTH_SHORT).show();







                // Consultar datos de la tabla 'Usuarios' , rawquery : Directamente codigo sql
                //Vamos a ver todos los datos
                Cursor c = db.rawQuery("SELECT * FROM Datos", null);

                if (c.moveToFirst()) {
                    do {
                        String nombre = c.getString(0);
                        String genero = c.getString(1);
                        String  meta= c.getString(2);
                        String  comentario= c.getString(3);


                        Log.d("DB", "nombre: " + nombre + ", genero: " + genero +", meta " + meta + "comentario " +comentario);
                    } while (c.moveToNext());
                }else{
                    Log.d("DB", "vacio");

                }

                // Cerrar el cursor y la base de datos
                c.close();





                // IMPRIMIR LOS DIAS

                // Consultar datos de la tabla 'Usuarios' , rawquery : Directamente codigo sql
                //Vamos a ver todos los datos
                 c = db.rawQuery("SELECT * FROM Dias", null);

                if (c.moveToFirst()) {
                    do {
                        String nombre = c.getString(0);
                        String dia = c.getString(1);



                        Log.d("DB", "nombre: " + nombre + ", dia: " + dia );
                    } while (c.moveToNext());
                }else{
                    Log.d("DB", "vacio");

                }

                // Cerrar el cursor y la base de datos
                c.close();




               // db.close();







                ///////////////////////



                //Creamos el Intent
                Intent intent = new Intent(Inicio1Activity.this, PerfilActivity.class);

                startActivity(intent);
            }

        });




        //Esto ya estaba
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }






    public void printContentValues(ContentValues contentValues) {
        // Itera sobre las claves y valores del ContentValues
        for (String key : contentValues.keySet()) {
            // Obtiene el valor asociado a la clave
            Object value = contentValues.get(key);
            // Imprime la clave y el valor
            System.out.println("Clave: " + key + " - Valor: " + value);
            Log.e("SQLite en valores", key + value);
        }
    }





}
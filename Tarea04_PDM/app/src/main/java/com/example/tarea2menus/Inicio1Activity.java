package com.example.tarea2menus;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;


public class Inicio1Activity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio1);
        //Localizar los controles
        TextView txtSaludo = (TextView)findViewById(R.id.TextViewMain);
        //Recuperamos la información pasada en el intent
                Bundle bundle = this.getIntent().getExtras();

        //Construimos el mensaje a mostrar
                txtSaludo.setText("Hola " + bundle.getString("NOMBRE"));

        final String[] datos =
                new String[]{"Estar mas delgad@ ","Ser mas fuerte ","Salud","Evitar estres","Estetica","Aburrimiento","Otro"};
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        R.layout.spinner_item, datos);

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
                //Creamos el Intent
                Intent intent = new Intent(Inicio1Activity.this, DesarrolloActivity.class);

                startActivity(intent);
            }

        });

        ///  lo nuevo

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Configurar el toggle para abrir y cerrar el drawer
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configurar listener para los elementos del NavigationView
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            //Suprime las advertencias de que el identificador no se conoce en tiempo de compilacion
            //
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(menuItem.getItemId()==R.id.menu_settings){
                    Log.i("ActionBar", "settings 2!");

                }
                if(menuItem.getItemId()==R.id.menu_info){
                    Log.i("ActionBar", "info 2!");
                    //Creamos el Intent
                    Intent intent = new Intent(Inicio1Activity.this, CalendarActivity.class);
                    startActivity(intent);

                }
                if(menuItem.getItemId()==R.id.menu_modify_Plan){
                    Log.i("ActionBar", "info 2!");
                    //Creamos el Intent
                    Intent intent = new Intent(Inicio1Activity.this, ComidaActivity.class);
                    startActivity(intent);

                }
                if(menuItem.getItemId()==R.id.menu_profile){
                    Log.i("ActionBar", "info 2!");
                    //Creamos el Intent
                    Intent intent = new Intent(Inicio1Activity.this, PerfilActivity.class);
                    startActivity(intent);

                }
                if(menuItem.getItemId()==R.id.menu_share){
                    Log.i("ActionBar", "info 2!");
                    //Creamos el Intent
                    Intent intent = new Intent(Inicio1Activity.this, ExercisesHomeActivity.class);
                    startActivity(intent);

                }
                if(menuItem.getItemId()==R.id.menu_exit){
                    Log.i("ActionBar", "info 2!");
                    //Creamos el Intent
                    Intent intent = new Intent(Inicio1Activity.this, ExercisesGymActivity.class);
                    startActivity(intent);

                }

                drawerLayout.closeDrawer(GravityCompat.START); // Cierra el drawer después de la selección
                return true;
            }
        });

        //Esto ya estaba
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it i  present.
                getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Tuve que usar if , porque switch necesita valores conocidos en tiempo de compilacion y
        * alparecer los id no son conocidos en ese momento*/
        /*switch (item.getItemId()) {
            case R.id.menu_save:
                Log.i("ActionBar", "Nuevo!");
                return true;
            case R.id.menu_new:
                Log.i("ActionBar", "Guardar!");;
                return true;

            case R.id.menu_settings:
                Log.i("ActionBar", "Settings!");;
                return true;
            default:
                return super.onOptionsItemSelected(item);

            */
         if (item.getItemId()==R.id.menu_info){
             Log.i("ActionBar", "calendar!");
             // Ahora lanzamos CalendarActivity
             Intent intent = new Intent(Inicio1Activity.this, Share2Activity.class);
             startActivity(intent);
             return true;

         }

        if (item.getItemId()==R.id.menu_share){
            Log.i("ActionBar", "Share!");;
            Intent intent = new Intent(Inicio1Activity.this, Share2Activity.class);
            startActivity(intent);
            return true;
        }
        if( R.id.menu_settings==item.getItemId()){
            Log.i("ActionBar", "Settings!");;
            return true;
        }

        if( R.id.menu_profile==item.getItemId()){
            Log.i("ActionBar", "Profile!");;
            return true;
        }
        if( R.id.menu_modify_Plan==item.getItemId()){
            Log.i("ActionBar", "Modify Plan!");;
            Intent intent = new Intent(Inicio1Activity.this, ComidaActivity.class);
            startActivity(intent);
            return true;
        }

        if( R.id.menu_exit==item.getItemId()){
            Log.i("ActionBar", "Exit!");;
            return true;
        }

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
       return super.onOptionsItemSelected(item);
    }

}
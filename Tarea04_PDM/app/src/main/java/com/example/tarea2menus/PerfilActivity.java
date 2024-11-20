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
import android.widget.CalendarView;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class PerfilActivity extends AppCompatActivity {

    private TextView profileName;
    private TextView profileEmail;
    private TextView profilePhone;
    private Button changeDataButton;
    private Button changePasswordButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Vinculamos los elementos de la interfaz con el código
        ImageView iconImage = findViewById(R.id.icon_image);
        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        profilePhone = findViewById(R.id.profile_phone);
        changeDataButton = findViewById(R.id.change_data_button);
        changePasswordButton = findViewById(R.id.change_password_button);
        logoutButton = findViewById(R.id.logout_button);

        // Obtenemos los datos del intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String nombreUsuario = extras.getString("NOMBRE");
            if (nombreUsuario != null) {
                profileName.setText(nombreUsuario);
            } else {
                Toast.makeText(this, "No se recibió el nombre del usuario", Toast.LENGTH_SHORT).show();
            }
        }

        // Acciones para los botones
        changeDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acciones para cambiar datos del usuario
                Toast.makeText(PerfilActivity.this, "Cambiar datos", Toast.LENGTH_SHORT).show();
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acciones para cambiar contraseña
                Toast.makeText(PerfilActivity.this, "Cambiar contraseña", Toast.LENGTH_SHORT).show();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para cerrar sesión
                Toast.makeText(PerfilActivity.this, "Cerrando sesión", Toast.LENGTH_SHORT).show();

                // Cierra todas las actividades
                finishAffinity(); // Finaliza todas las actividades asociadas a esta aplicación.

                // Opción: si quieres redirigir a la pantalla de inicio antes de cerrar
                Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                // Finaliza esta actividad también
                finish();
            }
        });

    }
}


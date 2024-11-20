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

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView selectedDateTextView;
    private EditText eventNameEditText;
    private Button saveEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Inicializar vistas
        calendarView = findViewById(R.id.calendar_view);
        selectedDateTextView = findViewById(R.id.selected_date);
        eventNameEditText = findViewById(R.id.event_name);
        saveEventButton = findViewById(R.id.save_event_button);
        setContentView(R.layout.activity_calendar);


        // Configurar evento al seleccionar una fecha
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            selectedDateTextView.setText(selectedDate);
        });

        // Configurar botón para guardar eventos
        saveEventButton.setOnClickListener(v -> {
            String eventName = eventNameEditText.getText().toString();
            String selectedDate = selectedDateTextView.getText().toString();

            if (eventName.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un nombre para el evento", Toast.LENGTH_SHORT).show();
            } else {
                // Aquí puedes implementar la lógica para guardar el evento
                Toast.makeText(this, "Evento \"" + eventName + "\" guardado para " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });
    }
}



package com.example.tarea2menus;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        // Referencia al TextView del encabezado
        TextView headerText = findViewById(R.id.header_text);
        headerText.setText("Ejercicios del Día");

        // Referencia al ListView
        ListView exerciseListView = findViewById(R.id.exercise_list_view);

        // Datos de ejemplo para la lista
        String[] exercises = {
                "Sentadillas",
                "Flexiones de brazo",
                "Abdominales",
                "Plancha",
                "Burpees",
                "Zancadas",
                "Salto de cuerda",
                "Dominadas"
        };

        // Adaptador para el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, // Contexto
                android.R.layout.simple_list_item_1, // Diseño para cada elemento
                exercises // Datos
        );

        // Conectar el adaptador con el ListView
        exerciseListView.setAdapter(adapter);
    }
}


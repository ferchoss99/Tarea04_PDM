package com.example.tarea2menus;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EntrenadorActivity extends AppCompatActivity {

    // Declarar las vistas
    private TextView headerText;
    private ImageView exerciseImage1, exerciseImage2, exerciseImage3, exerciseImage4;
    private TextView exerciseName1, exerciseName2, exerciseName3, exerciseName4;
    private TextView exerciseDescription1, exerciseDescription2, exerciseDescription3, exerciseDescription4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenador);

        // Inicializar las vistas
        headerText = findViewById(R.id.header_text);
        exerciseImage1 = findViewById(R.id.exercise_image1);
        exerciseImage2 = findViewById(R.id.exercise_image2);
        exerciseImage3 = findViewById(R.id.exercise_image3);
        exerciseImage4 = findViewById(R.id.exercise_image4);

        exerciseName1 = findViewById(R.id.exercise_name1);
        exerciseName2 = findViewById(R.id.exercise_name2);
        exerciseName3 = findViewById(R.id.exercise_name3);
        exerciseName4 = findViewById(R.id.exercise_name4);

        exerciseDescription1 = findViewById(R.id.exercise_description1);
        exerciseDescription2 = findViewById(R.id.exercise_description2);
        exerciseDescription3 = findViewById(R.id.exercise_description3);
        exerciseDescription4 = findViewById(R.id.exercise_description4);

        // Asignar los valores de texto a los TextViews
        headerText.setText(getString(R.string.searchTrainer));
        exerciseName1.setText(getString(R.string.coach1));
        exerciseName2.setText(getString(R.string.coach2));
        exerciseName3.setText(getString(R.string.coach3));
        exerciseName4.setText(getString(R.string.coach4));

        exerciseDescription1.setText(getString(R.string.des_coach1));
        exerciseDescription2.setText(getString(R.string.des_coach2));
        exerciseDescription3.setText(getString(R.string.des_coach3));
        exerciseDescription4.setText(getString(R.string.des_coach4));

        // Asignar imágenes a los ImageViews
        exerciseImage1.setImageResource(R.drawable.entrenador_1);
        exerciseImage2.setImageResource(R.drawable.entrenador_2);
        exerciseImage3.setImageResource(R.drawable.entrenador_3);
        exerciseImage4.setImageResource(R.drawable.entrenador_4);
    }
}

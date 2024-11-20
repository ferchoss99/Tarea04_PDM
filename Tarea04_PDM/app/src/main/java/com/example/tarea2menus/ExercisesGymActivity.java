package com.example.tarea2menus;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExercisesGymActivity extends AppCompatActivity {

    // Declaración de las vistas
    private TextView headerText;
    private ImageView exerciseImage1, exerciseImage2, exerciseImage3, exerciseImage4, exerciseImage5;
    private TextView exerciseName1, exerciseName2, exerciseName3, exerciseName4, exerciseName5;
    private TextView exerciseDescription1, exerciseDescription2, exerciseDescription3, exerciseDescription4, exerciseDescription5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_gym);

        // Inicialización de las vistas
        headerText = findViewById(R.id.header_text);

        // CardView 1
        exerciseImage1 = findViewById(R.id.exercise_image1);
        exerciseName1 = findViewById(R.id.exercise_name1);
        exerciseDescription1 = findViewById(R.id.exercise_description1);

        // CardView 2
        exerciseImage2 = findViewById(R.id.exercise_image2);
        exerciseName2 = findViewById(R.id.exercise_name2);
        exerciseDescription2 = findViewById(R.id.exercise_description2);

        // CardView 3
        exerciseImage3 = findViewById(R.id.exercise_image3);
        exerciseName3 = findViewById(R.id.exercise_name3);
        exerciseDescription3 = findViewById(R.id.exercise_description3);

        // CardView 4
        exerciseImage4 = findViewById(R.id.exercise_image4);
        exerciseName4 = findViewById(R.id.exercise_name4);
        exerciseDescription4 = findViewById(R.id.exercise_description4);

        // CardView 5
        exerciseImage5 = findViewById(R.id.exercise_image5);
        exerciseName5 = findViewById(R.id.exercise_name5);
        exerciseDescription5 = findViewById(R.id.exercise_description5);

    }

}


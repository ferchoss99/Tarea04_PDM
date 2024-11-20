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

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ComidaActivity extends AppCompatActivity {

    // Declaración de vistas
    private TextView weightGainTitle, weightGainDescription;
    private ImageView weightGainImage;
    private TextView weightLossTitle, weightLossDescription;
    private ImageView weightLossImage;
    private EditText inputPeso, inputTalla;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida);

        // Inicialización de vistas
        weightGainTitle = findViewById(R.id.weight_gain_title);
        weightGainDescription = findViewById(R.id.weight_gain_description);
        weightGainImage = findViewById(R.id.weight_gain_image);

        weightLossTitle = findViewById(R.id.weight_loss_title);
        weightLossDescription = findViewById(R.id.weight_loss_description);
        weightLossImage = findViewById(R.id.weight_loss_image);

        inputPeso = findViewById(R.id.input_peso);
        inputTalla = findViewById(R.id.input_talla);
        calculateButton = findViewById(R.id.calculate_button);

        // Configuración del botón de calcular
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });
    }

    /**
     * Método para calcular el Índice de Masa Corporal (IMC)
     */
    private void calcularIMC() {
        // Obtiene los valores de peso y talla
        String pesoStr = inputPeso.getText().toString();
        String tallaStr = inputTalla.getText().toString();

        // Validación de entrada
        if (pesoStr.isEmpty() || tallaStr.isEmpty()) {
            Toast.makeText(this, "Por favor, introduce peso y talla.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Conversión a números
            double peso = Double.parseDouble(pesoStr);
            double talla = Double.parseDouble(tallaStr) / 100; // Convertir talla a metros

            // Cálculo del IMC
            double imc = peso / (talla * talla);

            // Determina el resultado del IMC
            String resultado = determinarCategoriaIMC(imc);

            // Muestra el resultado en un Toast
            Toast.makeText(this, "Tu IMC es: " + String.format("%.2f", imc) + " (" + resultado + ")", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Introduce valores numéricos válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método para determinar la categoría del IMC
     * @param imc Índice de Masa Corporal calculado
     * @return Categoría del IMC
     */
    private String determinarCategoriaIMC(double imc) {
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }
}


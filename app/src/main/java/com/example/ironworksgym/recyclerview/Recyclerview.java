package com.example.ironworksgym.recyclerview;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ironworksgym.R;

public class Recyclerview extends AppCompatActivity {

    private TextView equipamentoTextView;
    private TextView dataMesAnoTextView;
    private TextView horarioTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste1); // Certifique-se de que o layout esteja correto

        // Inicializa as TextViews
        equipamentoTextView = findViewById(R.id.equipamento);
        dataMesAnoTextView = findViewById(R.id.dataMesAno);
        horarioTextView = findViewById(R.id.horario);

        // Captura os dados da Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int year = extras.getInt("year");
            int month = extras.getInt("month") + 1; // Janeiro é 0
            int day = extras.getInt("day");
            int hour = extras.getInt("hour");

            // Formata a data e hora para exibição
            String data = String.format("%02d/%02d/%04d", day, month, year);
            String horario = String.format("%02d:%02d", hour);

            // Atualiza as TextViews
            dataMesAnoTextView.setText(data);
            horarioTextView.setText(horario);
        }
    }
}
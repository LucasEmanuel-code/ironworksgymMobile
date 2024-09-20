package com.example.ironworksgym;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Agendamento extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento);

        // Encontra o TimePicker no layout
        TimePicker timePicker = findViewById(R.id.hours);

        // Configura o TimePicker para exibir em formato de 24 horas (se necessário)
        timePicker.setIs24HourView(true);

        // Define o listener para alterar o comportamento do TimePicker
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Força os minutos a sempre serem 0, para agendar de 1 em 1 hora
                if (minute != 0) {
                    view.setMinute(0);
                }
            }
        });

    }
}

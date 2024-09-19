package com.example.ironworksgym;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Agendamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.agendamento);

        Spinner spinnerHours = findViewById(R.id.spinner_hours);
        List<String> hours = new ArrayList<>();

        // Adicionando horários de 0 a 23
        for (int i = 0; i < 24; i++) {
            hours.add(String.format("%02d:00", i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, hours);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHours.setAdapter(adapter);

    }

}

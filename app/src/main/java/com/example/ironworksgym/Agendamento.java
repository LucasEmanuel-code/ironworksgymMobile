package com.example.ironworksgym;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class Agendamento extends AppCompatActivity {

    private Calendar selectedDate;
    private int selectedHour;
    private int selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento);

        // Inicializa o objeto Calendar para armazenar a data e hora
        selectedDate = Calendar.getInstance();

        // Encontra o CalendarView no layout
        CalendarView calendarView = findViewById(R.id.calendarView);

        // Encontra o TimePicker no layout
        TimePicker timePicker = findViewById(R.id.hours);

        // Configura o TimePicker para exibir em formato de 24 horas (se necessário)
        timePicker.setIs24HourView(true);

        // Listener para quando o usuário selecionar uma data no CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Atualiza o Calendar com a data escolhida
                selectedDate.set(year, month, dayOfMonth);
            }
        });

        // Define o listener para o TimePicker para forçar agendamento de 1 em 1 hora
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Força os minutos a sempre serem 0
                selectedHour = hourOfDay;
                selectedMinute = 0; // Sempre zero para agendamento a cada hora
                view.setMinute(selectedMinute);
            }
        });

        // Encontra o botão "Agendar" no layout
        Button btnAgendar = findViewById(R.id.btnAgendar);

        // Define o listener para o botão "Agendar"
        btnAgendar.setOnClickListener(v -> {
            // Atualiza o Calendar com a hora escolhida
            selectedDate.set(Calendar.HOUR_OF_DAY, selectedHour);
            selectedDate.set(Calendar.MINUTE, selectedMinute);

            // Exibe a data e hora escolhidas usando Toast
            String agendamento = "Agendado para: " + selectedDate.get(Calendar.DAY_OF_MONTH) + "/"
                    + (selectedDate.get(Calendar.MONTH) + 1) + "/" + selectedDate.get(Calendar.YEAR)
                    + " às " + selectedHour + ":00";

            Toast.makeText(Agendamento.this, agendamento, Toast.LENGTH_LONG).show();
        });
    }
}

package com.example.ironworksgym.AgendamentoApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.R;

import java.util.Calendar;

public class Agendamento extends AppCompatActivity {

    private Calendar selectedDate;
    private int selectedHour = -1; // Inicializado para -1 para indicar que o valor ainda não foi selecionado
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento);

        botao = findViewById(R.id.btnproximo);
        selectedDate = Calendar.getInstance();
        selectedDate.clear(); // Limpa para que não tenha uma data selecionada por padrão

        // Encontra o CalendarView no layout
        CalendarView calendarView = findViewById(R.id.calendarView);

        // Encontra o TimePicker no layout
        TimePicker timePicker = findViewById(R.id.hours);
        timePicker.setIs24HourView(true);

        // Listener para quando o usuário selecionar uma data no CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate.set(year, month, dayOfMonth);
            }
        });

        // Define o listener para o TimePicker
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selectedHour = hourOfDay; // Armazena a hora selecionada
                view.setMinute(0); // Força os minutos a sempre serem 00
            }
        });

        // Configura o botão para passar os dados para a próxima atividade
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedHour != -1 && selectedDate.get(Calendar.YEAR) != 1970) { // Verifica se a data e hora estão válidas
                    // Criar Intent para iniciar a próxima atividade (Equipamento)
                    Intent intent = new Intent(Agendamento.this, Equipamento.class);
                    // Adiciona as informações de data e hora como extras
                    intent.putExtra("year", selectedDate.get(Calendar.YEAR));
                    intent.putExtra("month", selectedDate.get(Calendar.MONTH));
                    intent.putExtra("day", selectedDate.get(Calendar.DAY_OF_MONTH));
                    intent.putExtra("hour", selectedHour);
                    intent.putExtra("minute", 0); // Sempre 00 para manter agendamentos de uma em uma hora
                    // Iniciar a próxima atividade
                    startActivity(intent);
                } else {
                    // Exibe mensagem de erro se a data ou a hora não forem selecionadas
                    Toast.makeText(Agendamento.this, "Por favor, selecione uma data e hora antes de prosseguir.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

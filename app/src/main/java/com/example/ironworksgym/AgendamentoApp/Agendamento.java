package com.example.ironworksgym.AgendamentoApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.AgendamentoApp.Equipamento;
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

        // Inicializa o objeto Calendar para armazenar a data
        selectedDate = Calendar.getInstance();
        selectedDate.clear(); // Limpa para que não tenha uma data selecionada por padrão

        // Encontra o CalendarView no layout
        CalendarView calendarView = findViewById(R.id.calendarView);

        // Encontra o TimePicker no layout
        TimePicker timePicker = findViewById(R.id.hours);

        // Configura o TimePicker para exibir em formato de 24 horas
        timePicker.setIs24HourView(true);

        // Listener para quando o usuário selecionar uma data no CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Atualiza o Calendar com a data escolhida
                selectedDate.set(year, month, dayOfMonth);
            }
        });

        // Define o listener para o TimePicker
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Força os minutos a sempre serem 0
                selectedHour = hourOfDay;
                view.setMinute(0); // Sempre 0 para agendamento a cada hora cheia
            }
        });

        // Configura o botão para passar os dados para a próxima atividade
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica se uma data foi selecionada
                if (selectedDate.isSet(Calendar.YEAR) && selectedHour != -1) {
                    // Cria um intent para navegar para a próxima atividade
                    Intent intent = new Intent(Agendamento.this, Equipamento.class);

                    // Adiciona os dados da data e hora como extras no intent
                    intent.putExtra("year", selectedDate.get(Calendar.YEAR));
                    intent.putExtra("month", selectedDate.get(Calendar.MONTH));
                    intent.putExtra("day", selectedDate.get(Calendar.DAY_OF_MONTH));
                    intent.putExtra("hour", selectedHour);
                    intent.putExtra("minute", 0); // Minutos sempre 0

                    // Inicia a próxima atividade
                    startActivity(intent);
                } else {
                    // Se a data ou hora não forem selecionadas, mostra uma mensagem de erro
                    Toast.makeText(Agendamento.this, "Por favor, selecione uma data e hora antes de prosseguir.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

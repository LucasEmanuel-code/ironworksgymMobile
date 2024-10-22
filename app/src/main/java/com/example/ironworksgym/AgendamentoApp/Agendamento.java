package com.example.ironworksgym.AgendamentoApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.Client.RetrofitClient;
import com.example.ironworksgym.Models.Agenda;
import com.example.ironworksgym.Models.Equipamento;
import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;
import com.example.ironworksgym.api.AgendamentoApi;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Agendamento extends AppCompatActivity {
    private Calendar selectedDate;
    private int selectedHour = -1;
    private Button btnproximo;
    private Equipamento equipamento;
    private Usuario usuario; // Certifique-se de inicializar o objeto Usuario corretamente
    private AgendamentoApi agendamentoApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento);

        agendamentoApi = RetrofitClient.getRetrofitInstance().create(AgendamentoApi.class);

        // Recebendo o objeto Equipamento usando Parcelable
        equipamento = getIntent().getParcelableExtra("equipamento"); // Use a chave correta aqui
        usuario = getIntent().getParcelableExtra("usuario"); // Use a chave correta para o usuário

        if (equipamento == null || equipamento.getId() <= 0) {
            Toast.makeText(this, "Equipamento inválido!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Log.d("Agendamento", "ID do equipamento: " + equipamento.getId());

        btnproximo = findViewById(R.id.btagendar);
        selectedDate = Calendar.getInstance();

        CalendarView calendarView = findViewById(R.id.calendarView);
        TimePicker timePicker = findViewById(R.id.hours);
        timePicker.setIs24HourView(true);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) ->
                selectedDate.set(year, month, dayOfMonth));

        timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            selectedHour = hourOfDay;
            view.setMinute(0);
        });

        btnproximo.setOnClickListener(v -> {
            if (selectedHour != -1 && selectedDate.get(Calendar.YEAR) != 1970) {
                createAgendamento();
            } else {
                Toast.makeText(Agendamento.this, "Por favor, selecione uma data e hora.", Toast.LENGTH_LONG).show();
            }
        });

        timePicker = findViewById(R.id.hours);
        timePicker.setIs24HourView(true);

        // Aplicar a cor branca ao TimePicker programaticamente
        int whiteColor = getResources().getColor(android.R.color.white);
        try {
            // Aplicar cor branca aos textos dos números (horas e minutos)
            int numberPickerTextColorId = getResources().getIdentifier("time_header", "id", "android");
            if (numberPickerTextColorId != 0) {
                timePicker.findViewById(numberPickerTextColorId).setBackgroundColor(whiteColor);
            }

            // Para versões anteriores ao Android 6.0, forçar cor branca nos spinners
            for (int i = 0; i < timePicker.getChildCount(); i++) {
                View child = timePicker.getChildAt(i);
                if (child instanceof NumberPicker ) {
                    @SuppressLint("SoonBlockedPrivateApi") Field textColorField = NumberPicker.class.getDeclaredField("mSelectorWheelPaint");
                    textColorField.setAccessible(true);
                    ((Paint) textColorField.get(child)).setColor(whiteColor);
                    ((NumberPicker) child).invalidate();
                }
            }
        } catch (Exception e) {
            Log.e("TimePicker", "Erro ao aplicar cor branca ao TimePicker: " + e.getMessage());
        }
    }

    @SuppressLint("DefaultLocale")
    private void createAgendamento() {
        if (equipamento.getId() <= 0) {
            Toast.makeText(this, "Equipamento inválido!", Toast.LENGTH_SHORT).show();
            return;
        }

        Agenda agendamento = new Agenda();
        @SuppressLint("SimpleDateFormat") String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate.getTime());
        agendamento.setDataDisponivel(formattedDate);
        agendamento.setHorarioDisponivel(String.format("%02d:00", selectedHour));
        agendamento.setUsuario(usuario);  // Objeto completo de Usuario, inicialize corretamente
        agendamento.setEquipamento(equipamento);
        agendamento.setStatusAgendamento("ATIVO");

        Log.d("Agendamento", "Dados enviados: " + agendamento.toString());

        Call<Agenda> call = agendamentoApi.create(agendamento);
        call.enqueue(new Callback<Agenda>() {
            @Override
            public void onResponse(Call<Agenda> call, Response<Agenda> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Agendamento.this, "Agendamento criado!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (Agendamento.this, Home2.class);
                    intent.putExtra("equipamentoNome", equipamento.getNome());
                    intent.putExtra("dataAgendamento", agendamento.getDataDisponivel());
                    intent.putExtra("horaAgendamento", agendamento.getHorarioDisponivel());
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);

                } else {
                    Toast.makeText(Agendamento.this, "Erro: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Agenda> call, Throwable t) {
                Toast.makeText(Agendamento.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
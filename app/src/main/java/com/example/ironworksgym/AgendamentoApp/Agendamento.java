package com.example.ironworksgym.AgendamentoApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.Models.Agenda;
import com.example.ironworksgym.R;
import com.example.ironworksgym.api.AgendamentoApi;
import com.example.ironworksgym.Client.RetrofitClient;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Agendamento extends AppCompatActivity {
    private Calendar selectedDate;
    private int selectedHour = -1; // Indica que a hora ainda não foi selecionada
    private Button btnproximo;
    private long usuario; // ID do usuário (ex.: por meio de sessão ou intent)
    private long equipamentoId; // ID do equipamento a ser selecionado
    private AgendamentoApi agendamentoApi; // Interface Retrofit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento);

        btnproximo = findViewById(R.id.btnproximo);
        selectedDate = Calendar.getInstance();
        selectedDate.clear(); // Limpa a data inicial
        // Inicializa Retrofit para usar a API de Agendamentos
        agendamentoApi = RetrofitClient.getRetrofitInstance().create(AgendamentoApi.class);
        // Inicialize o equipamentoId (pode ser recebido via Intent)
        equipamentoId = 1; // Substitua pelo ID real do equipamento

        // Recupera o ID do usuário passado pela Intent
        usuario = getIntent().getLongExtra("usuarioId", -1); // Alterado para long
        if (usuario == -1) {
            Toast.makeText(this, "ID do usuário não encontrado.", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("Agendamento", "Id do usuário recebido: " + usuario);

        // Definir o CalendarView para escolher a data
        CalendarView calendarView = findViewById(R.id.calendarView);
        TimePicker timePicker = findViewById(R.id.hours);
        timePicker.setIs24HourView(true);

        // Quando o usuário selecionar uma data
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate.set(year, month, dayOfMonth);
            }
        });

        // Quando o usuário selecionar a hora no TimePicker
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selectedHour = hourOfDay; // Armazena a hora selecionada
                view.setMinute(0); // Força os minutos para serem sempre 00
            }
        });

        // Altera a cor do texto do TimePicker
        setTimePickerTextColor(timePicker, getResources().getColor(R.color.white));

        // Listener do botão para criar o agendamento
        btnproximo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (selectedHour != -1 && selectedDate.get(Calendar.YEAR) != 1970) {
                    createAgendamento(); // Chama o método para enviar os dados
                } else {
                    Toast.makeText(Agendamento.this, "Por favor, selecione uma data e hora antes de prosseguir.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Método para criar e enviar o agendamento via Retrofit
    private void createAgendamento() {
        // Verifica se o ID do usuário é válido
        if (usuario == -1) {
            Toast.makeText(this, "ID do usuário não válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se o ID do equipamento é válido (pode adicionar uma verificação adicional se necessário)
        if (equipamentoId <= 0) {
            Toast.makeText(this, "ID do equipamento não válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        Agenda agendamento = new Agenda();
        // Formata a data
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate.getTime());
        agendamento.setDataDisponivel(formattedDate);

        // Formata a hora
        String formattedHour = String.format("%02d:00", selectedHour);
        agendamento.setHorarioDisponivel(formattedHour);
        // Define os IDs do usuário e equipamento
        agendamento.setUsuarioId(usuario); // Insira aqui o ID real do usuário
        agendamento.setEquipamentoId(equipamentoId); // Assegure-se de que este ID é válido
        agendamento.setStatusAgendamento("Pendente"); // Status inicial

        // Log para conferir os dados do agendamento
        Log.d("Agendamento", "Dados de agendamento enviados: " + agendamento.toString());

        // Enviar a requisição via Retrofit
        Call<Agenda> call = agendamentoApi.create(agendamento);
        call.enqueue(new Callback<Agenda>() {
            @Override
            public void onResponse(Call<Agenda> call, Response<Agenda> response) {
                if (response.isSuccessful()) {
                    // Mostra uma mensagem de sucesso
                    Toast.makeText(Agendamento.this, "Agendamento criado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Redireciona para a tela de Equipamento após o sucesso
                    Intent intent = new Intent(Agendamento.this, Equipamentos.class);
                    startActivity(intent); // Inicia a Activity de Equipamento
                } else {
                    // Verifica o código de resposta e exibe mensagem apropriada
                    if (response.code() == 400) {
                        Toast.makeText(Agendamento.this, "Dados inválidos fornecidos.", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 500) {
                        Toast.makeText(Agendamento.this, "Erro no servidor.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Agendamento.this, "Erro ao criar agendamento: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Agenda> call, Throwable t) {
                Toast.makeText(Agendamento.this, "Erro de comunicação com o servidor: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Agendamento", "Erro: " + t.getMessage());
            }
        });
    }

    // Método para alterar a cor do texto do TimePicker
    private void setTimePickerTextColor(TimePicker timePicker, int color) {
        for (int i = 0; i < timePicker.getChildCount(); i++) {
            View child = timePicker.getChildAt(i);
            if (child instanceof NumberPicker) {
                NumberPicker numberPicker = (NumberPicker) child;
                setNumberPickerTextColor(numberPicker, color);
            }
        }
    }

    // Função auxiliar para alterar a cor do NumberPicker
    private void setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        try {
            @SuppressLint("SoonBlockedPrivateApi") Field wheelPaintField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
            wheelPaintField.setAccessible(true);
            ((android.graphics.Paint) wheelPaintField.get(numberPicker)).setColor(color);

            for (int i = 0; i < numberPicker.getChildCount(); i++) {
                View child = numberPicker.getChildAt(i);
                if (child instanceof android.widget.EditText) {
                    ((android.widget.EditText) child).setTextColor(color);
                }
            }
            numberPicker.invalidate();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
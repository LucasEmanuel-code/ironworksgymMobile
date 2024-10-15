package com.example.ironworksgym.AgendamentoApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.ironworksgym.R;
import com.example.ironworksgym.api.EquipamentoApi;
import com.example.ironworksgym.Client.RetrofitClient;
import com.google.android.material.card.MaterialCardView;

import retrofit2.Call;

public class Equipamentos extends AppCompatActivity {

    private TextView tvSelectedEquipment;
    private String selectedEquipment = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipamentos);

        tvSelectedEquipment = findViewById(R.id.tvSelectedEquipment);

        MaterialCardView cardLegPress = findViewById(R.id.card);
        MaterialCardView cardCadeiraExtensora = findViewById(R.id.card2);
        MaterialCardView cardPeckDeck = findViewById(R.id.card3);
        MaterialCardView cardBicicleta = findViewById(R.id.card4);
        MaterialCardView cardEsteira = findViewById(R.id.card5);

        // Evento de clique nos cards para definir o equipamento selecionado
        cardLegPress.setOnClickListener(v -> selectEquipment("Leg Press"));
        cardCadeiraExtensora.setOnClickListener(v -> selectEquipment("Cadeira Extensora"));
        cardPeckDeck.setOnClickListener(v -> selectEquipment("Peck Deck"));
        cardBicicleta.setOnClickListener(v -> selectEquipment("Bicicleta"));
        cardEsteira.setOnClickListener(v -> selectEquipment("Esteira"));

        // Botão Agendar
        AppCompatButton btnAgendar = findViewById(R.id.btagendar);
        btnAgendar.setOnClickListener(v -> agendarEquipamento());
    }

    // Função para atualizar o TextView com o equipamento selecionado
    private void selectEquipment(String equipment) {
        selectedEquipment = equipment;
        tvSelectedEquipment.setText("Equipamento selecionado: " + equipment);
    }

    // Função que será chamada ao clicar no botão Agendar
    private void agendarEquipamento() {
        if (!selectedEquipment.isEmpty()) {
            Equipamentos equipamento = new Equipamentos(); // Crie o objeto Equipamento

            EquipamentoApi api = RetrofitClient.getRetrofitInstance().create(EquipamentoApi.class);
            Call<Equipamentos> call = api.agendarEquipamento(equipamento);

            call.enqueue(new retrofit2.Callback<Equipamentos>() {
                @Override
                public void onResponse(Call<Equipamentos> call, retrofit2.Response<Equipamentos> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(Equipamentos.this, "Equipamento " + selectedEquipment + " agendado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Equipamentos.this, Home2.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Equipamentos.this, "Falha ao agendar equipamento!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Equipamentos> call, Throwable t) {
                    Toast.makeText(Equipamentos.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Por favor, selecione um equipamento antes de agendar!", Toast.LENGTH_SHORT).show();
        }
    }
}

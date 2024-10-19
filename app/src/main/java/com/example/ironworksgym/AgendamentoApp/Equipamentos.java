package com.example.ironworksgym.AgendamentoApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.ironworksgym.Client.RetrofitClient;
import com.example.ironworksgym.Models.Equipamento;
import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;
import com.example.ironworksgym.api.EquipamentoApi;
import com.google.android.material.card.MaterialCardView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Equipamentos extends AppCompatActivity {

    private TextView tvSelectedEquipment;

    private Usuario usuario; // Objeto Usuario a ser passado
    private Equipamento equipamento = new Equipamento(); // Objeto Equipamento a ser enviado
    private EquipamentoApi equipamentoApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipamentos);

        // Recebendo o objeto Usuario do Intent
        usuario = getIntent().getParcelableExtra("usuarioId"); // Recebendo o objeto Usuario

        // Inicializa a API Retrofit
        equipamentoApi = RetrofitClient.getRetrofitInstance().create(EquipamentoApi.class);

        // Inicializa a interface
        tvSelectedEquipment = findViewById(R.id.tvSelectedEquipment);
        setupEquipmentCards();

        // Botão Agendar
        AppCompatButton btagendar = findViewById(R.id.btproximo);
        btagendar.setOnClickListener(v -> createEquipamento());
    }

    private void setupEquipmentCards() {
        MaterialCardView cardLegPress = findViewById(R.id.card);
        MaterialCardView cardCadeiraExtensora = findViewById(R.id.card2);
        MaterialCardView cardPeckDeck = findViewById(R.id.card3);
        MaterialCardView cardBicicleta = findViewById(R.id.card4);
        MaterialCardView cardEsteira = findViewById(R.id.card5);

        // Define o evento de clique nos cards para selecionar o equipamento
        cardLegPress.setOnClickListener(v -> selectEquipment("Leg Press", 1));
        cardCadeiraExtensora.setOnClickListener(v -> selectEquipment("Cadeira Extensora", 2));
        cardPeckDeck.setOnClickListener(v -> selectEquipment("Peck Deck", 3));
        cardBicicleta.setOnClickListener(v -> selectEquipment("Bicicleta", 4));
        cardEsteira.setOnClickListener(v -> selectEquipment("Esteira", 5));
    }

    private void selectEquipment(String equipment, long id) {
        equipamento.setId(id); // Define o ID do equipamento no objeto da classe
        equipamento.setNome(equipment); // Define o nome do equipamento corretamente
        equipamento.setQuantidade(1); // Define a quantidade como 1 ou outro valor relevante
        equipamento.setStatusEquipamento("ATIVO"); // Define o status do equipamento

        tvSelectedEquipment.setText("Equipamento selecionado: " + equipment);
    }

    private void createEquipamento() {
        if (equipamento.getId() > 0) { // Verifica se o equipamento está selecionado
            Call<Equipamento> call = equipamentoApi.createEquipamento(equipamento); // Envia o objeto Equipamento para o backend
            call.enqueue(new Callback<Equipamento>() {
                @Override
                public void onResponse(Call<Equipamento> call, Response<Equipamento> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(Equipamentos.this, "Equipamento " + equipamento.getNome() + " selecionado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Equipamentos.this, Agendamento.class);
                        intent.putExtra("equipamento", equipamento); // Passa o objeto Equipamento para a próxima tela
                        intent.putExtra("usuario", usuario); // Passa o objeto Usuario para a próxima tela
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Equipamentos.this, "Falha ao cadastrar equipamento!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Equipamento> call, Throwable t) {
                    Toast.makeText(Equipamentos.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Por favor, selecione um equipamento antes de cadastrar!", Toast.LENGTH_SHORT).show();
        }
    }
}

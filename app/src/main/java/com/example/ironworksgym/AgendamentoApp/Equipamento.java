package com.example.ironworksgym.AgendamentoApp;

import android.content.Intent; // Importar a classe Intent
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.example.ironworksgym.R;
import com.google.android.material.card.MaterialCardView;
import android.widget.TextView;

public class Equipamento extends AppCompatActivity {

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
            // Aqui você pode colocar o código para agendar o equipamento
            // Por exemplo, salvar no banco de dados ou mostrar uma mensagem de confirmação
            Toast.makeText(this, "Equipamento " + selectedEquipment + " agendado com sucesso!", Toast.LENGTH_SHORT).show();

            // Iniciar a atividade Home2
            Intent intent = new Intent(Equipamento.this, Home2.class);
            startActivity(intent);
            finish(); // Opcional: Se você quiser fechar a atividade atual
        } else {
            Toast.makeText(this, "Por favor, selecione um equipamento antes de agendar!", Toast.LENGTH_SHORT).show();
        }
    }
}

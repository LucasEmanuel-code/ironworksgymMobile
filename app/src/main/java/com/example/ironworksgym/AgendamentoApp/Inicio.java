package com.example.ironworksgym.AgendamentoApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.R;

public class Inicio extends AppCompatActivity {

    Button btcomecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home); // Certifique-se de ter o layout correto

        // Recebe o nome de usuário do Intent
        String usuario = getIntent().getStringExtra("usuario");
        long usuarioId = getIntent().getLongExtra("usuarioId", -1);

        // Encontra a TextView onde será exibida a mensagem de boas-vindas
        TextView textViewOla = findViewById(R.id.textViewOla);

        // Define a mensagem de boas-vindas com o nome do usuário
        textViewOla.setText("Olá, " + usuario + "!");

        btcomecar = findViewById(R.id.btcomecar);

        btcomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usuarioId != -1) {
                    Intent intent = new Intent(Inicio.this, Agendamento.class);
                    intent.putExtra("usuarioId", usuarioId);
                    startActivity(intent);
                }else{
                    Toast.makeText(Inicio.this, "Id do usuario nao encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}




package com.example.ironworksgym.AgendamentoApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.ironworksgym.AgendamentoApp.Agendamento;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.R;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home); // Certifique-se de ter o layout correto

        // Recebe o nome de usuário do Intent
        String usuario = getIntent().getStringExtra("usuario");

        // Encontra a TextView onde será exibida a mensagem de boas-vindas
        TextView textViewOla = findViewById(R.id.textViewOla);

        // Define a mensagem de boas-vindas com o nome do usuário
        textViewOla.setText("Olá, " + usuario + "!");
    }

}




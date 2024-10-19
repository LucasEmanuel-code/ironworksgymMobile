package com.example.ironworksgym.AgendamentoApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;


public class Inicio extends AppCompatActivity {
    private Usuario usuario;
    Button btcomecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Recebe o nome de usuário do Intent
        usuario = getIntent().getParcelableExtra("usuarioId");

        if (usuario == null) {
            // Se o usuário não for passado, exibe um valor padrão
            usuario = new Usuario(); // Crie um novo objeto Usuario ou trate o erro
            usuario.setNome("Usuário Desconhecido"); // Defina um nome padrão
        }

        // Encontra a TextView onde será exibida a mensagem de boas-vindas
        TextView textViewOla = findViewById(R.id.textViewOla);
        textViewOla.setText("Olá, " + usuario.getNome() + "!");

        btcomecar = findViewById(R.id.btcomecar);
        btcomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, Equipamentos.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            }
        });
    }
}

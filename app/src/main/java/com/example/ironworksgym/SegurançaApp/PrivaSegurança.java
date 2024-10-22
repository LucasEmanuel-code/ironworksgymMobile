package com.example.ironworksgym.SegurançaApp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;

public class PrivaSegurança extends AppCompatActivity {

    private EditText editSenha, editPhone, editApartamento, editTorre; // Adicione outros campos conforme necessário
    private Usuario usuario; // Objeto Usuario que será recebido pela Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacidade_seguranca);

        // Referenciar as caixas de texto
        editSenha = findViewById(R.id.editSenha); // Certifique-se de que este ID está correto no layout
        editPhone = findViewById(R.id.editPhone); // Certifique-se de que este ID está correto no layout
        editApartamento = findViewById(R.id.editApartamento); // Certifique-se de que este ID está correto no layout
        editTorre = findViewById(R.id.editTorre); // Certifique-se de que este ID está correto no layout

        // Verificar se há um Intent com o objeto Usuario
        if (getIntent() != null && getIntent().getSerializableExtra("usuario") != null) {
            usuario = (Usuario) getIntent().getSerializableExtra("usuario"); // Recebe o usuário passado pela Intent
            preencherDadosUsuario(); // Preenche os campos com os dados do usuário
        }
    }

    // Método para preencher os dados do usuário nas caixas de texto
    private void preencherDadosUsuario() {
        if (usuario != null) {
            editSenha.setText(usuario.getSenha()); // Se a senha deve ser mostrada
            editPhone.setText(usuario.getTelefone()); // Supondo que tenha um método getTelefone
            editApartamento.setText(usuario.getApartamento()); // Supondo que tenha um método getApartamento
            editTorre.setText(usuario.getTorre()); // Supondo que tenha um método getTorre
        }
    }
}

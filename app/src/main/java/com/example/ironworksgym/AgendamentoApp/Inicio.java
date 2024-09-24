package com.example.ironworksgym.AgendamentoApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.R;

public class Inicio extends AppCompatActivity {

    EditText editUsuario, editEmail, editDataNasc, editSenha, editPhone, editCPF, editConfirmarSenha, editApartamento, editTorre;
    CheckBox cbTermos;
    private Button botao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Inicio.this, Agendamento.class);
                startActivity(intent);
            }
        });

    }



}

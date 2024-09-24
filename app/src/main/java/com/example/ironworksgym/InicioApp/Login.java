package com.example.ironworksgym.InicioApp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.AgendamentoApp.Inicio;
import com.example.ironworksgym.MainActivity;
import com.example.ironworksgym.R;

public class Login extends AppCompatActivity {

    EditText editEmail, editSenha;
    Button btEntrar;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.login);

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);

        editEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();
                if (!email.contains("@") || !email.contains(".")) {
                    editEmail.setError("Insira um email válido (ex: exemplo@dominio.com)");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btEntrar = findViewById(R.id.btEntrar);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Inicio.class);
                startActivity(intent);
            }
        });

    }

}



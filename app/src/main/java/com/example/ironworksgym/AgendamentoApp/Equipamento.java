package com.example.ironworksgym.AgendamentoApp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.R;

public class Equipamento extends AppCompatActivity {

    EditText editUsuario, editEmail, editDataNasc, editSenha, editPhone, editCPF, editConfirmarSenha, editApartamento, editTorre;
    CheckBox cbTermos;
    Button btnEntrar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipamentos);

    }
}

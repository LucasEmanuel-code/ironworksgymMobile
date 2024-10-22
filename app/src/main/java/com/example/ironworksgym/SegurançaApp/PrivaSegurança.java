package com.example.ironworksgym.SegurançaApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;
import com.google.android.material.textfield.TextInputEditText;

public class PrivaSegurança extends Fragment {
    private TextInputEditText editSenha, editPhone, editApartamento, editTorre; // Adicione outros campos conforme necessário
    private Usuario usuario; // Objeto Usuario que será recebido pelo fragmento

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar o layout do fragment
        View view = inflater.inflate(R.layout.privacidade_seguranca, container, false);

        // Referenciar as caixas de texto dentro do Fragment
        editSenha = view.findViewById(R.id.editSenha); // Certifique-se de que este ID está correto
        editPhone = view.findViewById(R.id.editPhone); // Certifique-se de que este ID está correto
        editApartamento = view.findViewById(R.id.editApartamento); // Certifique-se de que este ID está correto
        editTorre = view.findViewById(R.id.editTorre); // Certifique-se de que este ID está correto

        // Verificar se há argumentos e obter o objeto Usuario
        if (getArguments() != null) {
            usuario = getArguments().getParcelable("usuario"); // Recebe o usuário do bundle
            if (usuario != null) {
                preencherDadosUsuario();
            }
        }

        return view;
    }

    // Método para preencher os dados do usuário nas caixas de texto
    private void preencherDadosUsuario() {
        editSenha.setText(usuario.getSenha()); // Se a senha deve ser mostrada
        editPhone.setText(usuario.getTelefone()); // Supondo que tenha um método getTelefone
        editApartamento.setText(usuario.getApartamento()); // Supondo que tenha um método getApartamento
        editTorre.setText(usuario.getTorre()); // Supondo que tenha um método getTorre
    }
}


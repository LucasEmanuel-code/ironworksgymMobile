package com.example.ironworksgym.Fragment;

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

public class ProfileFragment extends Fragment {

    private TextInputEditText editUsuario, editEmail;
    private Usuario usuario; // Objeto Usuario que será recebido pelo fragmento

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar o layout do fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Referenciar as caixas de texto dentro do Fragment
        editUsuario = view.findViewById(R.id.editUsuario);
        editEmail = view.findViewById(R.id.editEmail);

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
        editUsuario.setText(usuario.getNome());
        editEmail.setText(usuario.getEmail());
    }
}

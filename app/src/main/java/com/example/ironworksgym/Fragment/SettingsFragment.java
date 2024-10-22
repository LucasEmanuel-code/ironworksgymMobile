package com.example.ironworksgym.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;
import com.example.ironworksgym.SegurançaApp.FaleConosco;
import com.example.ironworksgym.SegurançaApp.PrivaSegurança;

public class SettingsFragment extends Fragment {

    private Usuario usuario;

    private Button btAjuda;

    private Button btPrivacidade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Recebe o objeto Usuario passado
        if (getArguments() != null) {
            usuario = (Usuario) getArguments().getSerializable("usuario"); // Certifique-se de que Usuario implementa Serializable
        }

        Button botaoPrivacidade = view.findViewById(R.id.btPrivacidadeSeg);
        Button botaoAjuda = view.findViewById(R.id.btAjuda);

        // Defina o listener do botão de privacidade
        botaoPrivacidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrivaSegurança.class);
                intent.putExtra("usuario", usuario); // Passa o objeto Usuario para a próxima tela
                startActivity(intent);
            }
        });

        // Defina o listener do botão de ajuda
        botaoAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FaleConosco.class);
                intent.putExtra("usuario", usuario); // Passa o objeto Usuario para a próxima tela
                startActivity(intent);
            }
        });

        return view;
    }
}

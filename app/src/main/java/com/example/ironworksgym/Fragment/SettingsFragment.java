package com.example.ironworksgym.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ironworksgym.InicioApp.Login;
import com.example.ironworksgym.R;
import com.example.ironworksgym.SegurançaApp.FaleConosco;
import com.example.ironworksgym.SegurançaApp.PrivaSegurança;


public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button botao = view.findViewById(R.id.btPrivacidadeSeg);

        // Defina o listener do botão
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação a ser realizada quando o botão é clicado
                Intent intent = new Intent(getActivity(), PrivaSegurança.class); // ou qualquer outra Activity
                startActivity(intent);
            }
        });

        botao = view.findViewById(R.id.btAjuda);

            // Defina o listener do botão
            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Ação a ser realizada quando o botão é clicado
                    Intent intent = new Intent(getActivity(), FaleConosco.class); // ou qualquer outra Activity
                    startActivity(intent);
                }
            });
        return view;
    }

}


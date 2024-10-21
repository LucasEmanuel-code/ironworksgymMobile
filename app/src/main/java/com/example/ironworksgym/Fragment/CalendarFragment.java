package com.example.ironworksgym.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ironworksgym.R;
import com.example.ironworksgym.recyclerview.AgendamentoAdapter;
import com.example.ironworksgym.recyclerview.AgendamentoItem;

import java.util.ArrayList;
import java.util.List;

public class CalendarFragment extends Fragment {

    private RecyclerView recyclerView;
    private AgendamentoAdapter adapter;
    private List<AgendamentoItem> equipamentos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        equipamentos = new ArrayList<>();

        // Acessar os dados do Bundle (se existirem)
        Bundle bundle = getArguments();
        if (bundle != null) {
            String equipamentoNome = bundle.getString("equipamentoNome");
            String dataAgendamento = bundle.getString("dataAgendamento");
            String horaAgendamento = bundle.getString("horaAgendamento");

            if (equipamentoNome != null && dataAgendamento != null && horaAgendamento != null) {
                // Adiciona o novo agendamento à lista a partir dos dados do Bundle
                equipamentos.add(new AgendamentoItem(equipamentoNome, dataAgendamento, horaAgendamento, R.drawable.peck_deck));
            }
        }

        // Acessar os dados do SharedPreferences (se existirem)
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AgendamentoPrefs", Context.MODE_PRIVATE);
        String equipamentoNomePrefs = sharedPreferences.getString("equipamentoNome", null);
        String dataAgendamentoPrefs = sharedPreferences.getString("dataAgendamento", null);
        String horaAgendamentoPrefs = sharedPreferences.getString("horaAgendamento", null);

        if (equipamentoNomePrefs != null && dataAgendamentoPrefs != null && horaAgendamentoPrefs != null) {
            // Adiciona o novo agendamento à lista a partir dos dados do SharedPreferences
            equipamentos.add(new AgendamentoItem(equipamentoNomePrefs, dataAgendamentoPrefs, horaAgendamentoPrefs, R.drawable.peck_deck));
        }

        // Configura o RecyclerView
        adapter = new AgendamentoAdapter(equipamentos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}

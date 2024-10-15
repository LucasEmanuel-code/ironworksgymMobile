package com.example.ironworksgym.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ironworksgym.R;
import com.example.ironworksgym.recyclerview.ItemAdapter;
import com.example.ironworksgym.recyclerview.ItemData;
import java.util.ArrayList;
import java.util.List;

public class CalendarFragment extends Fragment {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<ItemData> equipamentos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla o layout para o fragmento
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // Inicialize o RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview); // Aponte para o RecyclerView do XML

        // Crie a lista de dados (equipamentos)
        equipamentos = new ArrayList<>();

        // Adicione alguns itens de exemplo. Aqui você pode usar os dados reais.
        equipamentos.add(new ItemData("Equipamento 1", "01/10/2024", "10:00", R.drawable.peck_deck));
        equipamentos.add(new ItemData("Equipamento 2", "02/10/2024", "11:00", R.drawable.bicicleta));

        // Crie o Adapter e passe a lista de dados
        adapter = new ItemAdapter(equipamentos);

        // Configure o LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Use LinearLayoutManager

        // Conecte o Adapter ao RecyclerView
        recyclerView.setAdapter(adapter);

        return view;
    }
}

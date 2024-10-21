package com.example.ironworksgym.recyclerview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ironworksgym.R;
import java.util.ArrayList;
import java.util.List;

public class Item extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AgendamentoAdapter adapter;
    private List<AgendamentoItem> equipamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_calendar); // Verifique se este é o layout correto

        recyclerView = findViewById(R.id.recyclerview); // Certifique-se de que o ID está correto
        equipamentos = new ArrayList<>();

        // Adiciona alguns dados
        equipamentos.add(new AgendamentoItem("Equipamento 1", "01/10/2024", "10:00", R.drawable.peck_deck));
        equipamentos.add(new AgendamentoItem("Equipamento 2", "02/10/2024", "11:00", R.drawable.peck_deck));

        adapter = new AgendamentoAdapter(equipamentos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
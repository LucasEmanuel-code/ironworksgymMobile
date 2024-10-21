package com.example.ironworksgym.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ironworksgym.R;
import java.util.List;

public class AgendamentoAdapter extends RecyclerView.Adapter<AgendamentoAdapter.AgendamentoViewHolder> {

    private List<AgendamentoItem>  agendamentoItems; // Mudado de equipamentos para items

    public AgendamentoAdapter(List<AgendamentoItem> agendamentoItems) {
        this.agendamentoItems = agendamentoItems;
    }

    @NonNull
    @Override
    public AgendamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teste1, parent, false); // Verifique se o layout do item está correto
        return new AgendamentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendamentoViewHolder holder, int position) {
        AgendamentoItem item = agendamentoItems.get(position);
        holder.itemTextView.setText(item.getEquipamento());
        holder.dataTextView.setText(item.getDataMesAno());
        holder.horarioTextView.setText(item.getHorario());
    }

    @Override
    public int getItemCount() {
        return agendamentoItems.size();
    }

    public static class AgendamentoViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView; // Mudado de equipamentoTextView para itemTextView
        TextView dataTextView;
        TextView horarioTextView;

        public AgendamentoViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.equipamento);
            dataTextView = itemView.findViewById(R.id.dataMesAno);
            horarioTextView = itemView.findViewById(R.id.horario);
        }
    }
}
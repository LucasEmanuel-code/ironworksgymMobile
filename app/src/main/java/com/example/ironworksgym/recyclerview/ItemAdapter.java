package com.example.ironworksgym.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ironworksgym.R;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<ItemData> items; // Mudado de equipamentos para items

    public ItemAdapter(List<ItemData> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teste1, parent, false); // Verifique se o layout do item está correto
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemData item = items.get(position);
        holder.itemTextView.setText(item.getEquipamento());
        holder.dataTextView.setText(item.getDataMesAno());
        holder.horarioTextView.setText(item.getHorario());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView; // Mudado de equipamentoTextView para itemTextView
        TextView dataTextView;
        TextView horarioTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.equipamento);
            dataTextView = itemView.findViewById(R.id.dataMesAno);
            horarioTextView = itemView.findViewById(R.id.horario);
        }
    }
}

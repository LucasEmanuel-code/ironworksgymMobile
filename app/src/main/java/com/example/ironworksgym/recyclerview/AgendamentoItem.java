package com.example.ironworksgym.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AgendamentoItem implements Parcelable {
    private String equipamento;
    private String dataMesAno;
    private String horario;

    // Construtor
    public AgendamentoItem(String equipamento, String dataMesAno, String horario, int peck_deck) {
        this.equipamento = equipamento;
        this.dataMesAno = dataMesAno;
        this.horario = horario;
    }

    protected AgendamentoItem(Parcel in) {
        equipamento = in.toString();
        dataMesAno = in.toString();
        horario = in.toString();
    }

    public static final Creator<AgendamentoItem> CREATOR = new Creator<AgendamentoItem>() {
        @Override
        public AgendamentoItem createFromParcel(Parcel in) {
            return new AgendamentoItem(in);
        }

        @Override
        public AgendamentoItem[] newArray(int size) {
            return new AgendamentoItem[size];
        }
    };

    // Getters e Setters
    public String getEquipamento() {
        return equipamento;
    }

    public String getDataMesAno() {
        return dataMesAno;
    }

    public String getHorario() {
        return horario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(equipamento);
        parcel.writeString(dataMesAno);
        parcel.writeString(horario);
    }
}

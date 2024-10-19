package com.example.ironworksgym.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Equipamento implements Parcelable {
    private long id;
    private String nome;
    private int quantidade;
    private String statusEquipamento;

    public Equipamento() {}

    protected Equipamento(Parcel in) {
        id = in.readLong();
        nome = in.readString();
        quantidade = in.readInt();
        statusEquipamento = in.readString();
    }

    public static final Creator<Equipamento> CREATOR = new Creator<Equipamento>() {
        @Override
        public Equipamento createFromParcel(Parcel in) {
            return new Equipamento(in);
        }

        @Override
        public Equipamento[] newArray(int size) {
            return new Equipamento[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nome);
        dest.writeInt(quantidade);
        dest.writeString(statusEquipamento);
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatusEquipamento() {
        return statusEquipamento;
    }

    public void setStatusEquipamento(String statusEquipamento) {
        this.statusEquipamento = statusEquipamento;
    }
}

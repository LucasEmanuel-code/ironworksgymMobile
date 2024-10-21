package com.example.ironworksgym.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Agenda implements Parcelable {
    @SerializedName("id")
    private long id;

    @SerializedName("dataDisponivel")
    private String dataDisponivel;

    @SerializedName("horarioDisponivel")
    private String horarioDisponivel;

    @SerializedName("usuario")
    private Usuario usuario;

    @SerializedName("equipamento")
    private Equipamento equipamento;

    @SerializedName("statusAgendamento")
    private String statusAgendamento;

    // Construtor completo
    public Agenda(long id, String dataDisponivel, String horarioDisponivel,
                  Usuario usuario, Equipamento equipamento, String statusAgendamento) {
        this.id = id;
        this.dataDisponivel = dataDisponivel;
        this.horarioDisponivel = horarioDisponivel;
        this.usuario = usuario;
        this.equipamento = equipamento;
        this.statusAgendamento = statusAgendamento;
    }

    // Construtor padrão
    public Agenda() {}

    protected Agenda(Parcel in) {
        id = in.readLong();
        dataDisponivel = in.readString();
        horarioDisponivel = in.readString();
        usuario = in.readParcelable(Usuario.class.getClassLoader());
        equipamento = in.readParcelable(Equipamento.class.getClassLoader());
        statusAgendamento = in.readString();
    }

    public static final Creator<Agenda> CREATOR = new Creator<Agenda>() {
        @Override
        public Agenda createFromParcel(Parcel in) {
            return new Agenda(in);
        }

        @Override
        public Agenda[] newArray(int size) {
            return new Agenda[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(dataDisponivel);
        dest.writeString(horarioDisponivel);
        dest.writeParcelable(usuario, flags);
        dest.writeParcelable(equipamento, flags);
        dest.writeString(statusAgendamento);
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataDisponivel() {
        return dataDisponivel;
    }

    public void setDataDisponivel(String dataDisponivel) {
        this.dataDisponivel = dataDisponivel;
    }

    public String getHorarioDisponivel() {
        return horarioDisponivel;
    }

    public void setHorarioDisponivel(String horarioDisponivel) {
        this.horarioDisponivel = horarioDisponivel;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(String statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }
}

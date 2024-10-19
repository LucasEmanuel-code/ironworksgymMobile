package com.example.ironworksgym.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Agenda implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("dataDisponivel")
    private String dataDisponivel;

    @SerializedName("horarioDisponivel")
    private String horarioDisponivel;

    @SerializedName("usuario")
    private Usuario usuario;  // Renomeado para refletir que é um objeto

    @SerializedName("equipamento")
    private Equipamento equipamento;  // Renomeado para refletir que é um objeto

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

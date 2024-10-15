package com.example.ironworksgym.Models;

import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Agenda {

    @SerializedName("id")
    private long id;

    // Data formatada como "yyyy-MM-dd" para compatibilidade com JSON
    @SerializedName("dataDisponivel")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String dataDisponivel;

    @SerializedName("horarioDisponivel")
    private String horarioDisponivel;

    @SerializedName("usuario")
    private long usuarioId; // ID do usuário, representado como long

    @SerializedName("equipamento")
    private long equipamentoId; // ID do equipamento, representado como long

    @SerializedName("statusAgendamento")
    private String statusAgendamento;

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

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public long getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(long equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public String getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(String statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }
}

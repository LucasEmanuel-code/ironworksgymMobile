package com.example.ironworksgym.recyclerview;

public class ItemData {
    private String equipamento;
    private String dataMesAno;
    private String horario;

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getDataMesAno() {
        return dataMesAno;
    }

    public void setDataMesAno(String dataMesAno) {
        this.dataMesAno = dataMesAno;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public ItemData(String equipamento, String dataMesAno, String horario, int peck_deck) {
        this.equipamento = equipamento;
        this.dataMesAno = dataMesAno;
        this.horario = horario;
    }
}

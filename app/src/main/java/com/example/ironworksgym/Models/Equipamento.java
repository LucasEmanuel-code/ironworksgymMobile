package com.example.ironworksgym.Models;

public class Equipamento {

    private int id;
    private String nome;
    private int quantidade;
    private String statusEquipamento;

    // Construtor
    public Equipamento(int id, String nome, int quantidade, String statusEquipamento) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.statusEquipamento = statusEquipamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

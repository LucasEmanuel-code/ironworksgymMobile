package com.example.ironworksgym.Models;

import java.io.Serializable;

public class Usuario implements Serializable {
    private long id; // Adiciona o campo ID
    private String nome;
    private String email;
    private String dataNascimento; // Corrigido para camelCase
    private String senha;
    private String telefone;
    private String cpf; // Corrigido para camelCase
    private String apartamento;
    private String torre;

    // Construtor
    public Usuario(long id, String nome, String email, String dataNascimento, String senha,
                   String telefone, String cpf, String apartamento, String torre) {
        this.id = id; // Inicializa o ID
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento; // Corrigido para camelCase
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf; // Corrigido para camelCase
        this.apartamento = apartamento;
        this.torre = torre;
    }

    // Construtor padrão
    public Usuario() {}

    // Getters e Setters
    public long getId() { // Adiciona o getter para ID
        return id;
    }

    public void setId(long id) { // Adiciona o setter para ID
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData_Nascimento() { // Corrigido para camelCase
        return dataNascimento;
    }

    public void setData_Nascimento(String dataNascimento) { // Corrigido para camelCase
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public String getTorre() {
        return torre;
    }

    public void setTorre(String torre) {
        this.torre = torre;
    }
}

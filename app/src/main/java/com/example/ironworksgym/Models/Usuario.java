package com.example.ironworksgym.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {
    private long id;
    private String nome;
    private String dataNascimento;
    private String telefone;
    private String email;
    private String senha;
    private String cpf;
    private String torre;
    private String apartamento;
    private String statusUsuario;

    public Usuario() {}

    protected Usuario(Parcel in) {
        id = in.readLong();
        nome = in.readString();
        dataNascimento = in.readString();
        telefone = in.readString();
        email = in.readString();
        senha = in.readString();
        cpf = in.readString();
        torre = in.readString();
        apartamento = in.readString();
        statusUsuario = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
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
        dest.writeString(dataNascimento);
        dest.writeString(telefone);
        dest.writeString(email);
        dest.writeString(senha);
        dest.writeString(cpf);
        dest.writeString(torre);
        dest.writeString(apartamento);
        dest.writeString(statusUsuario);
    }

    // Getters e Setters
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getData_Nascimento() { return dataNascimento; }

    public void setData_Nascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTorre() { return torre; }

    public void setTorre(String torre) { this.torre = torre; }

    public String getApartamento() { return apartamento; }

    public void setApartamento(String apartamento) { this.apartamento = apartamento; }

    public String getStatusUsuario() { return statusUsuario; }

    public void setStatusUsuario(String statusUsuario) { this.statusUsuario = statusUsuario; }
}

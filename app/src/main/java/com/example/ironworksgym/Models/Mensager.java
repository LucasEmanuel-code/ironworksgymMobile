package com.example.ironworksgym.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Mensager implements Parcelable {

    private long id;
    private String Data_Mensagem;
    private String Emissor;
    private String Email;
    private String Texto;
    private String StatusMensagem;

    public Mensager() {
        // Construtor padrão
    }

    protected Mensager(Parcel in) {
        id = in.readLong();
        Data_Mensagem = in.readString();
        Emissor = in.readString();
        Email = in.readString();
        Texto = in.readString();
        StatusMensagem = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData_Mensagem() {
        return Data_Mensagem;
    }

    public void setData_Mensagem(String data_Mensagem) {
        Data_Mensagem = data_Mensagem;
    }

    public String getEmissor() {
        return Emissor;
    }

    public void setEmissor(String emissor) {
        Emissor = emissor;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String texto) {
        Texto = texto;
    }

    public String getStatusMensagem() {
        return StatusMensagem;
    }

    public void setStatusMensagem(String statusMensagem) {
        StatusMensagem = statusMensagem;
    }

    public static final Creator<Mensager> CREATOR = new Creator<Mensager>() {
        @Override
        public Mensager createFromParcel(Parcel in) {
            return new Mensager(in); // Agora passando o Parcel para o construtor protegido
        }

        @Override
        public Mensager[] newArray(int size) {
            return new Mensager[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(Data_Mensagem);
        dest.writeString(Emissor);
        dest.writeString(Email);
        dest.writeString(Texto);
        dest.writeString(StatusMensagem);
    }
}

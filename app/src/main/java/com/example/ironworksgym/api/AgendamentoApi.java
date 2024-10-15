package com.example.ironworksgym.api;

import com.example.ironworksgym.Models.Agenda;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.DELETE;

public interface AgendamentoApi {

    // Criar um novo agendamento
    @POST("agendamento/create")
    Call<Agenda> create(@Body Agenda agendamento);

    // Buscar todos os agendamentos
    @GET("agendamento/findAll")
    Call<List<Agenda>> findAll();

    // Atualizar um agendamento existente
    @PUT("agendamento/update/{id}")
    Call<Agenda> update(@Path("id") long id, @Body Agenda agendamento);

    // Inativar um agendamento
    @PUT("agendamento/inativar/{id}")
    Call<Agenda> inativar(@Path("id") long id);

    // Reativar um agendamento
    @PUT("agendamento/reativar/{id}")
    Call<Agenda> reativar(@Path("id") long id);

    // Deletar um agendamento
    @DELETE("agendamento/delete/{id}")
    Call<Void> delete(@Path("id") long id);
}

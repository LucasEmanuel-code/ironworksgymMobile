package com.example.ironworksgym.api;

import com.example.ironworksgym.Models.Equipamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EquipamentoApi {

    @GET("/equipamento/findAll")
    Call<List<Equipamento>> findAll();

    @POST("/equipamento/create")
    Call<Equipamento> createEquipamento(@Body Equipamento equipamento);

    @PUT("/equipamento/inativar/{id}")
    Call<Equipamento> inativarEquipamento(@Path("id") long id);

    @PUT("/equipamento/ativar/{id}")
    Call<Equipamento> ativarEquipamento(@Path("id") long id);
}

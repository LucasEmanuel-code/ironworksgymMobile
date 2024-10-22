package com.example.ironworksgym.api;

import com.example.ironworksgym.Models.Mensager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MensagemApi {

    @GET("mensagem/findAll")
    Call<List<Mensager>> findAll();

    @GET("mensagem/findById/{id}")
    Call<Mensager> findById(@Path("id") long id);

    @POST("mensagem/create")
    Call<Mensager> create(@Body Mensager mensager);

    @POST("mensagem/inativar/{id}")
    Call<Mensager> inativar(@Body Mensager mensager);

    @POST("mensagem/marcarComoLida/{id}")
    Call<Mensager> marcarComoLida(@Body Mensager mensager);

}






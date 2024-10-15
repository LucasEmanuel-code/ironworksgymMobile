package com.example.ironworksgym.api;

import com.example.ironworksgym.Models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioApi {

    @GET("usuario/findAll")
    Call<List<Usuario>> findAll();

    @GET("usuario/findById/{id}")
    Call<Usuario> findById(@Path("id") long id);

    @POST("usuario/create")
    Call<Usuario> create(@Body Usuario usuario);


    @POST("usuario/signin")
    Call<Usuario> signin(@Body Usuario usuario);

}


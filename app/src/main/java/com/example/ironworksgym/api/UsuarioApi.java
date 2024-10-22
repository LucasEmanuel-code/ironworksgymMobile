package com.example.ironworksgym.api;

import com.example.ironworksgym.AgendamentoApp.Agendamento;
import com.example.ironworksgym.Models.Agenda;
import com.example.ironworksgym.Models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioApi {

    @GET("usuario/findAll")
    Call<List<Usuario>> findAll();

    @GET("usuario/findByEmail/")
    Call<Usuario> findByEmail(@Query("email") String email);

    @GET("usuario/findById/{id}")
    Call<Usuario> findById(@Path("id") long id);

    @POST("usuario/create")
    Call<Usuario> create(@Body Usuario usuario);


    @POST("usuario/signin")
    Call<Usuario> signin(@Body Usuario usuario);

    @PUT("usuario/alterarSenha/{id}")
    Call<Usuario> alterarSenha(@Path("id") long id, @Body Usuario usuario);

    @GET("usuario/agendamento/{userId}") // Exemplo de como pode ser o endpoint
    Call<List<Agenda>> getAgendamentos(@Path("userId") Usuario userId);
}


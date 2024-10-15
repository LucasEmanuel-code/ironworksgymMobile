package com.example.ironworksgym.api;

import com.example.ironworksgym.AgendamentoApp.Equipamentos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EquipamentoApi {

    // Método para obter a lista de equipamentos
    @GET("equipamentos")
    Call<List<Equipamentos>> getEquipamentos();

    // Método para agendar um equipamento
    @POST("equipamentos/agendar")
    Call<Equipamentos> agendarEquipamento(@Body Equipamentos equipamento);
}

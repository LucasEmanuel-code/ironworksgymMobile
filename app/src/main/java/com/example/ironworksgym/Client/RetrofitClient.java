package com.example.ironworksgym.Client;

import android.util.Log;

import com.example.ironworksgym.Models.Agenda;
import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.api.AgendamentoApi;
import com.example.ironworksgym.api.UsuarioApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static UsuarioApi usuarioApi;
    private static AgendamentoApi agendamentoApi;

    // Método para obter a instância do Retrofit
    public static Retrofit getRetrofitInstance() {
        String apiUrl = "http://192.168.0.135:8080"; // URL da API local
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(apiUrl) // URL base do servidor da API
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Inicializa a interface de API para Usuario e Agendamento
            usuarioApi = retrofit.create(UsuarioApi.class);
            agendamentoApi = retrofit.create(AgendamentoApi.class);
        }
        return retrofit;
    }

    // Método para criar um novo usuário
    public static void createUser(Usuario novoUsuario) {
        Call<Usuario> call = usuarioApi.create(novoUsuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    // Usuário criado com sucesso
                    Log.d("Retrofit", "Usuário criado com sucesso!");
                } else {
                    try {
                        // Captura e exibe a resposta de erro
                        String errorResponse = response.errorBody().string();
                        Log.e("Retrofit", "Erro: " + errorResponse); // Logar o erro
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Tratamento de falha na chamada (problemas de conexão, etc.)
                Log.e("Retrofit", "Falha na requisição: " + t.getMessage());
            }
        });
    }


    // Método para criar um novo agendamento
    public static void createAgendamento(Agenda agendamento) {
        // Implementação da chamada Retrofit para enviar a `agendamento`
        AgendamentoApi apiService = retrofit.create(AgendamentoApi.class);
        Call<Agenda> call = apiService.create(agendamento);
        call.enqueue(new Callback<Agenda>() {
            @Override
            public void onResponse(Call<Agenda> call, Response<Agenda> response) {
                if (response.isSuccessful()) {
                    // Lógica para o sucesso
                } else {
                    // Lógica para o erro de resposta
                }
            }

            @Override
            public void onFailure(Call<Agenda> call, Throwable t) {
                // Lógica para falha na conexão
            }
        });
    }

}

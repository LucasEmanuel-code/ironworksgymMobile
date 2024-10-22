package com.example.ironworksgym.InicioApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.AgendamentoApp.Agendamento;
import com.example.ironworksgym.AgendamentoApp.Home2;
import com.example.ironworksgym.Client.RetrofitClient;
import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;
import com.example.ironworksgym.api.UsuarioApi;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login"; // Tag para logs
    private TextView txtEsqueceuSenha;
    private EditText editEmail, editSenha;
    private Button btEntrar;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.login);

        // Inicializa a UI
        initViews();

        // Configura o listener do botão de login
        btEntrar.setOnClickListener(v -> loginUser());

        // Configura o listener do texto de "Esqueceu Senha"
        txtEsqueceuSenha.setOnClickListener(v -> openEsqueceuSenhaActivity());
    }

    private void initViews() {
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btEntrar = findViewById(R.id.btEntrar);
        txtEsqueceuSenha = findViewById(R.id.txtEsqueceuSenha);
    }

    private void loginUser() {
        String email = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        // Cria um usuário para login
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        UsuarioApi usuarioApi = RetrofitClient.getRetrofitInstance().create(UsuarioApi.class);
        Call<Usuario> call = usuarioApi.signin(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Login.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Home2.class);
                    Usuario user = response.body();
                    intent.putExtra("usuarioId", user);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("API Error", "Erro: " + response.message());
                    Toast.makeText(Login.this, "Erro ao logar: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Toast.makeText(Login.this, "Erro na comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAgendamentos(long userId, Usuario usuario) {
        UsuarioApi usuarioApi = RetrofitClient.getRetrofitInstance().create(UsuarioApi.class);
        Call<List<Agendamento>> call = usuarioApi.getAgendamentos(usuario);

        call.enqueue(new Callback<List<Agendamento>>() {
            @Override
            public void onResponse(@NonNull Call<List<Agendamento>> call, @NonNull Response<List<Agendamento>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    saveAgendamentosToPreferences(response.body());
                }
                navigateToHome(usuario); // Navega para Home2 após a obtenção dos agendamentos
            }

            @Override
            public void onFailure(@NonNull Call<List<Agendamento>> call, @NonNull Throwable t) {
                Log.e(TAG, "Erro ao buscar agendamentos: " + t.getMessage());
                navigateToHome(usuario); // Navega para Home2 mesmo em caso de erro
            }
        });
    }

    private void saveAgendamentosToPreferences(List<Agendamento> agendamento) {
        SharedPreferences sharedPreferences = getSharedPreferences("AgendamentoPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("agendamentos", new Gson().toJson(agendamento));
        editor.apply();
    }

    private void navigateToHome(Usuario usuario) {
        Intent intent = new Intent(Login.this, Home2.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
        finish();
    }

    private void openEsqueceuSenhaActivity() {
        Intent intent = new Intent(Login.this, EsqueceuSenha.class);
        startActivity(intent);
    }
}

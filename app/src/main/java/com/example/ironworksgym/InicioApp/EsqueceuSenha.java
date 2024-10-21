package com.example.ironworksgym.InicioApp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.Client.RetrofitClient;
import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;
import com.example.ironworksgym.api.UsuarioApi;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EsqueceuSenha extends AppCompatActivity {

    private TextInputEditText editEmail, editNovaSenha;
    private UsuarioApi usuarioApi; // Interface para Retrofit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.esqueceusenha);

        // Inicializar os campos de input
        editEmail = findViewById(R.id.editEmail);
        editNovaSenha = findViewById(R.id.editNovaSenha);

        // Inicializar Retrofit e a interface UsuarioApi
        usuarioApi = RetrofitClient.getRetrofitInstance().create(UsuarioApi.class);

        // Botão para confirmar a troca de senha
        findViewById(R.id.btEntrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarSenha();
            }
        });
    }

    private void alterarSenha() {
        // Obtenção dos valores dos campos
        String email = editEmail.getText().toString().trim();
        String novaSenha = editNovaSenha.getText().toString().trim();

        // Validações básicas dos campos
        if (TextUtils.isEmpty(email)) {
            editEmail.setError("Informe o email");
            return;
        }

        if (TextUtils.isEmpty(novaSenha)) {
            editNovaSenha.setError("Informe a nova senha");
            return;
        }

        // Chamada para buscar o usuário pelo email
        Call<Usuario> callFindUsuario = usuarioApi.findByEmail(email);
        callFindUsuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuario = response.body();
                    // Se encontrou o usuário, agora faz a chamada para alterar a senha
                    alterarSenhaUsuario(usuario.getId(), novaSenha);
                } else {
                    // Caso não encontre o usuário
                    Toast.makeText(EsqueceuSenha.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Erro de comunicação
                Toast.makeText(EsqueceuSenha.this, "Falha na comunicação com o servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void alterarSenhaUsuario(long idUsuario, String novaSenha) {
        // Cria um objeto Usuario com apenas a nova senha para enviar no corpo da requisição
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        usuario.setSenha(novaSenha);

        // Chamada para o backend via Retrofit
        Call<Usuario> call = usuarioApi.alterarSenha(idUsuario, usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Senha alterada com sucesso
                    Toast.makeText(EsqueceuSenha.this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();
                    finish(); // Fecha a tela
                } else {
                    // Erro no retorno do servidor
                    Toast.makeText(EsqueceuSenha.this, "Erro ao alterar senha. Verifique suas informações.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Erro de comunicação
                Toast.makeText(EsqueceuSenha.this, "Falha na comunicação com o servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

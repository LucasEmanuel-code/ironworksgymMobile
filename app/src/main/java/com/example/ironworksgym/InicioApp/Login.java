package com.example.ironworksgym.InicioApp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.Client.RetrofitClient;
import com.example.ironworksgym.Fragment.CalendarFragment;
import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;
import com.example.ironworksgym.api.UsuarioApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextView txtEsqueceuSenha;
    EditText editEmail, editSenha;
    Button btEntrar;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.login);

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);

        // Validação de e-mail
        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();
                if (!email.contains("@") || !email.contains(".")) {
                    editEmail.setError("Insira um email válido (ex: exemplo@dominio.com)");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btEntrar = findViewById(R.id.btEntrar);
        btEntrar.setOnClickListener(v -> loginUser());

        txtEsqueceuSenha = findViewById(R.id.txtEsqueceuSenha);
        txtEsqueceuSenha.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, EsqueceuSenha.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        UsuarioApi usuarioApi = RetrofitClient.getRetrofitInstance().create(UsuarioApi.class);
        Call<Usuario> call = usuarioApi.signin(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuario = response.body();

                    // Verifica se o ID foi retornado
                    Log.d("Login", "ID do usuário: " + usuario.getId());

                    Intent intent = new Intent(Login.this, CalendarFragment.class);
                    intent.putExtra("usuario", usuario.getNome());
                    intent.putExtra("usuarioId", usuario.getId()); // Passa o ID para a próxima Activity
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Login falhou! Verifique suas credenciais.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Toast.makeText(Login.this, "Erro na comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

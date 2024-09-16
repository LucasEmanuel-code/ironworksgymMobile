package com.example.ironworksgym;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.dao.UsuarioDao;
import com.example.ironworksgym.model.Usuario;

public class Login extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnEntrar;

    // Usuário de teste com email e senha válidos (pode ser substituído por dados de banco de dados)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Referenciando os campos de entrada
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnEntrar = findViewById(R.id.btEntrar);

        // Configurando o listener para o botão de login
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();

                Usuario usu = new UsuarioDao().selecionaUsuario(email, senha);
                if(usu != null){
                    
                }

                // Validação das informações
                if (validateLogin(email, senha)) {
                    // Se as credenciais forem válidas, exibe uma mensagem de sucesso e prossegue
                    Toast.makeText(Login.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                    abriraTelaPrincipal(); // Método para abrir outra activity
                } else {
                    // Caso as credenciais sejam inválidas
                    Toast.makeText(Login.this, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Função que valida o email e a senha
    private boolean validateLogin(String email, String senha) {
        // Verifica se o campo de email está vazio
        if (TextUtils.isEmpty(email)) {
            editEmail.setError("O campo de email é obrigatório!");
            return false;
        }
        // Verifica se o email é válido (padrão de email)
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Por favor, insira um email válido!");
            return false;
        }
        // Verifica se o campo de senha está vazio
        else if (TextUtils.isEmpty(senha)) {
            editSenha.setError("O campo de senha é obrigatório!");
            return false;
        }
        // Verifica se a senha tem pelo menos 6 caracteres
        else if (senha.length() < 6) {
            editSenha.setError("A senha deve ter pelo menos 6 caracteres!");
            return false;
        }
        // Caso as credenciais estejam incorretas
        else {
            return false;
        }
    }

    // Método para abrir a próxima tela (Activity Principal) após login bem-sucedido
    private void abriraTelaPrincipal() {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        finish(); // Fecha a tela de login
    }

}

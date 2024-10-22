package com.example.ironworksgym.SegurançaApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.Client.RetrofitClient;
import com.example.ironworksgym.Fragment.SettingsFragment;
import com.example.ironworksgym.Models.Mensager;
import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;
import com.example.ironworksgym.api.MensagemApi;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaleConosco extends AppCompatActivity {
    private TextInputEditText edtTexto, edtEmail;
    private Button enviar;
    private Usuario usuario; // Objeto Usuario declarado
    private MensagemApi mensagemApi;

    private Button btvoltar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fale_conosco); // Verifique se este é o layout correto

        // Inicializando os componentes do layout
        edtEmail = findViewById(R.id.editEmail);
        edtTexto = findViewById(R.id.editMensagem);
        enviar = findViewById(R.id.btEnviar);
        btvoltar = findViewById(R.id.btVoltar);

        // Verificações de null
        Log.d("FaleConosco", "edtEmail: " + edtEmail); // Deve ser diferente de null
        Log.d("FaleConosco", "edtTexto: " + edtTexto); // Deve ser diferente de null
        Log.d("FaleConosco", "enviar: " + enviar); // Deve ser diferente de null
        Log.d("FaleConosco", "btVoltar: " + btvoltar); // Deve ser diferente de null

        mensagemApi = RetrofitClient.getRetrofitInstance().create(MensagemApi.class);

        // Receber o objeto Usuario através do Intent
        Intent intent = getIntent();
        if (intent != null) {
            usuario = (Usuario) intent.getSerializableExtra("usuario"); // Certifique-se que Usuario implementa Serializable
        }
        carregarEmailDoUsuario();

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensagem();
            }
        });

        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mude para o SettingsFragment usando o ID correto do layout que contém o fragmento
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.btVoltar, new SettingsFragment()) // Use o ID correto do contêiner de fragmentos
                        .commit();
            }
        });
    }

    private void carregarEmailDoUsuario() {
        // Verificando se o usuário e o email estão disponíveis antes de definir no EditText
        if (usuario != null && usuario.getEmail() != null) {
            edtEmail.setText(usuario.getEmail());
        } else {
            Log.e("FaleConosco", "Usuario é nulo ou o email é nulo");
            edtEmail.setText(""); // Ou você pode deixar vazio ou definir um texto padrão
        }
    }

    private void enviarMensagem() {
        // Verifica se o objeto usuario não é nulo
        if (usuario != null && usuario.getEmail() != null) {
            // Puxa o email do objeto usuario
            String email = usuario.getEmail();
            String texto = edtTexto.getText().toString().trim();

            // Lógica para obter o telefone e emissor (ajuste conforme necessário)
            String statusMensagem = "Pendente"; // Pode ser "Pendente", "Lida", etc.

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(texto)) {
                Snackbar.make(enviar, "Preencha todos os campos", Snackbar.LENGTH_LONG).show();
                return;
            }

            Mensager novaMensagem = new Mensager();
            novaMensagem.setEmail(email); // Puxa o email corretamente
            novaMensagem.setTexto(texto);
            novaMensagem.setStatusMensagem(statusMensagem); // Definindo o status

            mensagemApi.create(novaMensagem).enqueue(new Callback<Mensager>() {
                @Override
                public void onResponse(Call<Mensager> call, Response<Mensager> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Mensagem enviada com sucesso.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Mensagem não enviada.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Mensager> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha na comunicação com o servidor.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.e("FaleConosco", "Usuario é nulo ou o email é nulo");
            Snackbar.make(enviar, "Usuário não logado ou email não disponível.", Snackbar.LENGTH_LONG).show();
        }
    }
}

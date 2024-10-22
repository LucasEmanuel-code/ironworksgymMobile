package com.example.ironworksgym.SegurançaApp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.Models.Mensager;
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

    private MensagemApi mensagemApi;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fale_conosco);

        edtEmail = findViewById(R.id.editEmail);
        edtTexto = findViewById(R.id.editMensagem);
        enviar = findViewById(R.id.btEnviar);

        // Supondo que você tenha um método para obter o email do usuário
        carregarEmailDoUsuario();

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensagem();
            }
        });
    }

    private void carregarEmailDoUsuario() {
        // Aqui você deve implementar a lógica para obter o email do usuário
        // Por exemplo, a partir de uma SharedPreferences ou de uma chamada à API
        String email = "usuario@exemplo.com"; // Substitua pela lógica real
        edtEmail.setText(email);
    }

    private void enviarMensagem() {
        String email = edtEmail.getText().toString().trim();
        String texto = edtTexto.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(texto)) {
            Snackbar.make(enviar, "Preencha todos os campos", Snackbar.LENGTH_LONG).show();
            return;
        }

        Mensager novaMensagem;
        novaMensagem = new Mensager();
        novaMensagem.setEmail(email);
        novaMensagem.setTexto(texto);
        // Você pode querer definir outros campos, como o emissor ou o status

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
    }
}

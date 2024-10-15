package com.example.ironworksgym.InicioApp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ironworksgym.AgendamentoApp.Inicio;
import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;
import com.example.ironworksgym.Client.RetrofitClient;
import com.example.ironworksgym.api.UsuarioApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText editUsuario, editEmail, editDataNasc, editSenha, editPhone, editCPF, editConfirmarSenha, editApartamento, editTorre;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initViews();
        setupTextWatchers();  // Chama o método para configurar os TextWatchers

        btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    create(); // Chama a função para registrar o usuário via Retrofit
                } else {
                    Toast.makeText(getApplicationContext(), "Verifique as informações novamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void initViews() {
        editUsuario = findViewById(R.id.editUsuario);
        editEmail = findViewById(R.id.editEmail);
        editDataNasc = findViewById(R.id.editDataNasc);
        editSenha = findViewById(R.id.editSenha);
        editPhone = findViewById(R.id.editPhone);
        editCPF = findViewById(R.id.editCPF);
        editConfirmarSenha = findViewById(R.id.editConfirmarSenha);
        editApartamento = findViewById(R.id.editApartamento);
        editTorre = findViewById(R.id.editTorre);
        btnEntrar = findViewById(R.id.btnEntrar);
    }
    private void setupTextWatchers() {
        setupCPFTextWatcher();
        setupPhoneTextWatcher();
        setupDateTextWatcher();
    }
    private boolean validateFields() {
        String usuario = editUsuario.getText().toString();
        String email = editEmail.getText().toString();
        String dataNascimento = editDataNasc.getText().toString();
        String senha = editSenha.getText().toString();
        String numero = editPhone.getText().toString();
        String cpf = editCPF.getText().toString();
        String confirmarSenha = editConfirmarSenha.getText().toString();
        String apartamento = editApartamento.getText().toString();
        String torre = editTorre.getText().toString();

        boolean isValid = validateinfo(usuario, email, dataNascimento, senha, numero, cpf, confirmarSenha, apartamento, torre);

        Log.d("Validation", "Campos validados: " + isValid); // Log para verificar se os campos passaram na validação

        return isValid;
    }
    public void create() {
        String nome = editUsuario.getText().toString();
        String email = editEmail.getText().toString();
        String dataNascimento = editDataNasc.getText().toString();
        String senha = editSenha.getText().toString();
        String telefone = editPhone.getText().toString();
        String cpf = editCPF.getText().toString();
        String apartamento = editApartamento.getText().toString();
        String torre = editTorre.getText().toString();

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setData_Nascimento(dataNascimento);
        usuario.setSenha(senha);
        usuario.setTelefone(telefone);
        usuario.setCpf(cpf);
        usuario.setApartamento(apartamento);
        usuario.setTorre(torre);

        UsuarioApi usuarioApi = RetrofitClient.getRetrofitInstance().create(UsuarioApi.class);
        Call<Usuario> call = usuarioApi.create(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.d("API Response", "Response: " + response.body());

                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuarioResponse = response.body();
                    long usuarioId = usuarioResponse.getId();

                    Toast.makeText(Register.this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Inicio.class);
                    intent.putExtra("usuarioId", usuarioId);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("API Error", "Erro: " + response.message());
                    Toast.makeText(Register.this, "Erro ao registrar: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(Register.this, "Erro de comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validateinfo(String usuario, String email, String dataNasc, String senha, String numero, String CPF, String confirmarSenha, String apartamento, String torre) {
        // Validação de Usuário
        if (TextUtils.isEmpty(usuario)) {
            editUsuario.setError("O campo não pode estar vazio!");
            return false;
        } else if (!usuario.matches("[a-zA-Z]+")) {
            editUsuario.setError("Insira apenas caracteres alfabéticos");
            return false;
        } else if (usuario.length() < 4) {
            editUsuario.setError("O nome de usuário deve ter ao menos 4 caracteres");
            return false;
        }

        // Validação de Email
        if (TextUtils.isEmpty(email)) {
            editEmail.setError("O campo não pode estar vazio!");
            return false;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-zA-Z]+\\.[a-zA-Z]+")) {
            editEmail.setError("Insira um email válido (ex: exemplo@dominio.com)");
            return false;
        }

        // Validação da Data de Nascimento
        if (TextUtils.isEmpty(dataNasc)) {
            editDataNasc.setError("O campo Data de Nascimento não pode estar vazio!");
            return false;
        } else if (!isValidDate(dataNasc)) {
            editDataNasc.setError("Data de Nascimento inválida! Verifique o formato.");
            return false;
        }

        // Validação de Telefone
        if (TextUtils.isEmpty(numero)) {
            editPhone.setError("O campo Telefone não pode estar vazio!");
            return false;
        } else if (!numero.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}")) {
            editPhone.setError("Formato Correto: (xx)xxxxx-xxxx");
            return false;
        }

        // Validação 0de CPF
        if (TextUtils.isEmpty(CPF)) {
            editCPF.setError("O campo CPF não pode estar vazio!");
            return false;
        } else if (!isValidCPF(CPF)) {
            editCPF.setError("CPF inválido!");
            return false;
        }

        // Validação de Senha
        if (TextUtils.isEmpty(senha)) {
            editSenha.setError("O campo Senha não pode estar vazio!");
            return false;
        } else if (senha.length() < 8) {
            editSenha.setError("A Senha precisa ter no mínimo 8 caracteres");
            return false;
        }

        // Validação de Confirmação de Senha
        if (TextUtils.isEmpty(confirmarSenha)) {
            editConfirmarSenha.setError("O campo não pode estar vazio!");
            return false;
        } else if (!senha.equals(confirmarSenha)) {
            editConfirmarSenha.setError("As senhas não coincidem");
            return false;
        }

        // Validação de Apartamento
        if (TextUtils.isEmpty(apartamento)) {
            editApartamento.setError("O campo Apartamento não pode estar vazio!");
            return false;
        } else if (!apartamento.matches("\\d{3}")) {
            editApartamento.setError("O número do apartamento deve ter 3 dígitos");
            return false;
        }

        // Validação de Torre
        if (TextUtils.isEmpty(torre)) {
            editTorre.setError("O campo Torre não pode estar vazio!");
            return false;
        } else if (!torre.matches("[ABC]")) {
            editTorre.setError("Escolha um tipo de edifício: A, B ou C");
            return false;
        }

        return true;
    }
    // Implementação de validação de data
    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Verifica datas inválidas
            Date birthDate = sdf.parse(date);

            // Verificar se a data é de pelo menos 18 anos atrás
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -18);
            return birthDate.before(cal.getTime());
        } catch (ParseException e) {
            return false;
        }
    }
    // TextWatcher para formatação de CPF
    private void setupCPFTextWatcher() {
        editCPF.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;

                String cpf = s.toString().replaceAll("[^\\d]", ""); // Remover todos os caracteres não numéricos
                if (cpf.length() > 11) cpf = cpf.substring(0, 11); // Limita o CPF a 11 dígitos numéricos

                String formattedCpf = formatCPF(cpf);

                isUpdating = true;
                editCPF.setText(formattedCpf);
                editCPF.setSelection(formattedCpf.length());
                isUpdating = false;
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    // Formatação do CPF
    private String formatCPF(String cpf) {
        if (cpf.length() <= 3) return cpf;
        if (cpf.length() <= 6) return cpf.substring(0, 3) + "." + cpf.substring(3);
        if (cpf.length() <= 9) return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6);
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
    }
    // TextWatcher para formatação de Telefone
    private void setupPhoneTextWatcher() {
        editPhone.addTextChangedListener(new TextWatcher() {
            private boolean  isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;

                String phone = s.toString().replaceAll("[^\\d]", ""); // Remover todos os caracteres não numéricos
                if (phone.length() > 11) phone = phone.substring(0, 11); // Limitar a 11 dígitos numéricos

                String formattedPhone = formatPhone(phone);

                isUpdating = true;
                editPhone.setText(formattedPhone);
                editPhone.setSelection(formattedPhone.length());
                isUpdating = false;
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    // Formatação do telefone
    private String formatPhone(String phone) {
        if (phone.length() <= 2) return phone; // Apenas o DDD
        String ddd = phone.substring(0, 2);
        String number = phone.substring(2);
        if (number.length() == 0) return "(" + ddd + ")";
        if (number.length() <= 4) return "(" + ddd + ") " + number;
        return "(" + ddd + ") " + number.substring(0, 5) + "-" + number.substring(5);
    }
    // TextWatcher para formatação de Data de Nascimento
    private void setupDateTextWatcher() {
        editDataNasc.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;

                String date = s.toString().replaceAll("[^\\d]", ""); // Remover todos os caracteres não numéricos
                if (date.length() > 8) date = date.substring(0, 8); // Limitar a 8 dígitos

                String formattedDate = formatDate(date);

                isUpdating = true;
                editDataNasc.setText(formattedDate);
                editDataNasc.setSelection(formattedDate.length());
                isUpdating = false;
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    // Formatação da Data
    private String formatDate(String date) {
        if (date.length() <= 2) return date; // Dia
        if (date.length() <= 4) return date.substring(0, 2) + "/" + date.substring(2); // Dia/Mês
        return date.substring(0, 2) + "/" + date.substring(2, 4) + "/" + date.substring(4); // Dia/Mês/Ano
    }
    // Validação do CPF
    private boolean isValidCPF(String CPF) {
        // Remover formatação
        CPF = CPF.replaceAll("[^\\d]", "");
        if (CPF.length() != 11) return false;

        // Verificar se todos os dígitos são iguais (ex: 111.111.111-11)
        if (CPF.matches("(\\d)\\1{10}")) return false;

        // Cálculo de dígitos verificadores
        int[] multiplicador1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicador2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        String tempCPF = CPF.substring(0, 9);
        int soma = 0;

        for (int i = 0; i < 9; i++) {
            soma += (tempCPF.charAt(i) - 48) * multiplicador1[i];
        }
        int resto = soma % 11;
        char digito1 = (resto < 2) ? '0' : (char) (11 - resto + 48);

        tempCPF = tempCPF + digito1;
        soma = 0;

        for (int i = 0; i < 10; i++) {
            soma += (tempCPF.charAt(i) - 48) * multiplicador2[i];
        }
        resto = soma % 11;
        char digito2 = (resto < 2) ? '0' : (char) (11 - resto + 48);

        return CPF.equals(tempCPF + digito2);
    }
}
package com.example.ironworksgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Register extends AppCompatActivity {

    EditText editUsuario, editEmail, editDataNasc, editSenha, editPhone, editCPF, editConfirmarSenha, editApartamento, editTorre;
    CheckBox cbTermos;
    Button btnEntrar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

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


        //Campo de CPF
        editCPF.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }
                String cpf = s.toString().replaceAll("[^\\d]", "");
                String formattedCpf = "";
                if (cpf.length() > 0) {
                    if (cpf.length() <= 3) {
                        formattedCpf = cpf;
                    } else if (cpf.length() <= 6) {
                        formattedCpf = cpf.substring(0, 3) + "." + cpf.substring(3);
                    } else if (cpf.length() <= 9) {
                        formattedCpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6);
                    } else if (cpf.length() <= 11) {
                        formattedCpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
                    } else {
                        formattedCpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
                    }
                }
                isUpdating = true;
                editCPF.setText(formattedCpf);
                editCPF.setSelection(formattedCpf.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Campo de Telefone
        editPhone.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;
            private String old = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^\\d]", ""); // Remove tudo que não é número
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                String formatted = "";
                if (str.length() > 0) {
                    if (str.length() <= 2) {
                        formatted = "(" + str;
                    } else if (str.length() <= 7) {
                        formatted = "(" + str.substring(0, 2) + ") " + str.substring(2);
                    } else if (str.length() <= 11) {
                        formatted = "(" + str.substring(0, 2) + ") " + str.substring(2, 7) + "-" + str.substring(7);
                    } else {
                        formatted = "(" + str.substring(0, 2) + ") " + str.substring(2, 7) + "-" + str.substring(7, 11);
                    }
                }
                isUpdating = true;
                editPhone.setText(formatted);
                editPhone.setSelection(formatted.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Campo de Data de Nascimento
        editDataNasc.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }
                String data = s.toString().replaceAll("[^\\d]", "");
                String formattedData = "";
                if (data.length() > 0) {
                    if (data.length() <= 2) {
                        formattedData = data;
                    } else if (data.length() <= 4) {
                        formattedData = data.substring(0, 2) + "/" + data.substring(2);
                    } else if (data.length() <= 8) {
                        formattedData = data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4);
                    } else {
                        formattedData = data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4, 8);
                    }
                }
                isUpdating = true;
                editDataNasc.setText(formattedData);
                editDataNasc.setSelection(formattedData.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Campo de Apartamento
        editApartamento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String apartamento = s.toString();
                if (!apartamento.matches("[a-zA-Z0-9]*")) {
                    editApartamento.setError("O apartamento deve conter apenas caracteres alfanuméricos");
                } else if (apartamento.length() > 10) {
                    editApartamento.setError("O número de apartamento deve ter no máximo 10 caracteres");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        // Campo de Torre
        editTorre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String torre = s.toString();
                if (!torre.matches("\\d*")) {
                    editTorre.setError("O campo Torre deve conter apenas números");
                } else if (torre.length() > 3) {
                    editTorre.setError("O campo Torre deve conter no máximo 3 dígitos");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String usuario = editUsuario.getText().toString();
                    String email = editEmail.getText().toString();
                    String DataNasc = editDataNasc.getText().toString();
                    String senha = editSenha.getText().toString();
                    String numero = editPhone.getText().toString();
                    String CPF = editCPF.getText().toString();
                    String confirmarsenha = editConfirmarSenha.getText().toString();
                    String Apartamento = editApartamento.getText().toString();
                    String Torre = editTorre.getText().toString();


                boolean check = validateinfo(usuario, email, DataNasc, senha, numero, CPF, confirmarsenha, Apartamento, Torre);

                if (check) {
                    Toast.makeText(getApplicationContext(), "Infomações validas", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Register.this, Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Verifique as informações novamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Campo de Email
        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();
                if (!email.contains("@") || !email.contains(".")) {
                    editEmail.setError("Insira um email válido (ex: exemplo@dominio.com)");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private boolean validateinfo(String usuario, String email, String DataNasc, String senha, String numero, String CPF, String confirmarsenha, String Apartamento, String Torre) {

        //validação de Usuario
        if (usuario.length() == 0) {
            editUsuario.requestFocus();
            editUsuario.setError("O campo não pode estar vazio!");
            return false;
        } else if (!usuario.matches("[a-zA-Z]+")) {
            editUsuario.requestFocus();
            editUsuario.setError("Insira apenas caracteres alfabéticos");
            return false;
        } else if (usuario.length() < 4) {  // Exigindo ao menos 4 caracteres no usuário
            editUsuario.requestFocus();
            editUsuario.setError("O nome de usuário deve ter ao menos 4 caracteres");
            return false;
        } else if (usuario.contains(" ")) {  // Impedindo espaços no usuário
            editUsuario.requestFocus();
            editUsuario.setError("O nome de usuário não pode conter espaços");
            return false;
        }

        //Validação de Email
        if (email.length() == 0) {
            editEmail.requestFocus();
            editEmail.setError("O campo não pode estar vazio!");
            return false;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-zA-Z]+\\.[a-zA-Z]+")) {
            editEmail.requestFocus();
            editEmail.setError("Insira um email válido (ex: exemplo@dominio.com)");
            return false;
        }

        // Validação da Data de Nascimento
        if (DataNasc.length() == 0) {
            editDataNasc.requestFocus();
            editDataNasc.setError("O campo Data de Nascimento não pode estar vazio!");
            return false;
        } else if (!DataNasc.matches(DataNasc)) {
            editDataNasc.requestFocus();
            editDataNasc.setError("Data de Nascimento inválida!");
            return false;
        }

        //Validação de Telefone
        if (numero.length() == 0) {
            editPhone.requestFocus();
            editPhone.setError("O Campo não pode estar vazio!");
            return false;

        } else if (!numero.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}")) {
            editPhone.requestFocus();
            editPhone.setError("Formato Correto: (xx)xxxxx-xxxx");
            return false;
        }

        //Validação de Senha
        if (editSenha.length() == 0) {
            editSenha.setError("Password is required");
            return false;
        } else if (editSenha.length() < 8) {
            editSenha.setError("A Senha precisa no minimo 8 caracteres");
            return false;
        }

        // Validação do CPF
        if (CPF.length() == 0) {
            editCPF.requestFocus();
            editCPF.setError("O campo CPF não pode estar vazio!");
            return false;
        } else if (!CPF.matches(CPF)) {
            editCPF.requestFocus();
            editCPF.setError("CPF inválido!");
            return false;
        }

        // Validação de Confirmação de Senha
        if (TextUtils.isEmpty(confirmarsenha)) {
            editConfirmarSenha.requestFocus();
            editConfirmarSenha.setError("O campo não pode estar vazio!");
            return false;
        } else if (!senha.equals(confirmarsenha)) {
            editConfirmarSenha.requestFocus();
            editConfirmarSenha.setError("As senhas não coincidem");
            return false;
        }

        // Validação Apartamento
        if (Apartamento.length() == 0) {
            editApartamento.requestFocus();
            editApartamento.setError("O campo Apartamento não pode estar vazio!");
            return false;
        } else if (!Apartamento.matches("[a-zA-Z0-9]*")) {
            editApartamento.requestFocus();
            editApartamento.setError("O apartamento deve conter apenas caracteres alfanuméricos");
            return false;
        } else if (Apartamento.length() > 10) {
            editApartamento.requestFocus();
            editApartamento.setError("O número de apartamento deve ter no máximo 10 caracteres");
            return false;
        }
        //Validação Torre
        if (Torre.length() == 0) {
            editTorre.requestFocus();
            editTorre.setError("O campo Torre não pode estar vazio!");
            return false;

        } else if (!Torre.matches("\\d*")) {
            editTorre.requestFocus();
            editTorre.setError("O campo Torre deve conter apenas números");
            return false;
        } else if (Torre.length() > 3) {
            editTorre.requestFocus();
            editTorre.setError("O campo Torre deve conter no máximo 3 dígitos");
            return false;
        }

        return true;
    }


    private boolean validateCommonFields(String usuario, String email, String CPF, String senha, String confirmarsenha, String numero) {
        // Validações comuns já implementadas...
        return true;
    }

    // Função para validação de CPF
    private boolean isValidCPF(String CPF) {
        CPF = CPF.replaceAll("[^\\d]", ""); // Remove os pontos e traços
        if (CPF.length() != 11) return false;

        // Lógica simplificada para validação de CPF (poderia ser mais completa)
        return true; // Aqui deve ser implementada a lógica completa de verificação dos dígitos verificadores
    }

    // Função para validar a Data de Nascimento
    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Verifica datas inválidas (ex: 30/02/2020)
            Date birthDate = sdf.parse(date);
            // Verificar se a data é de pelo menos 18 anos atrás
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -18);
            return birthDate.before(cal.getTime());
        } catch (ParseException e) {
            return false;

        }
    }


}

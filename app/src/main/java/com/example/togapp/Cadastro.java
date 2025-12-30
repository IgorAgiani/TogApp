package com.example.togapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro extends AppCompatActivity {
    private TextInputLayout campoNome, campoEmail, campoCpf, campoDataNascimento, campoCelular, campoSenha, campoRepetirSenha;
    private MaterialButton botaoRegistrar;
    private TextView textoFacaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
        inicializarComponentes();
        configurarListeners();
    }

    private void inicializarComponentes() {
        campoNome = findViewById(R.id.campo_nome);
        campoEmail = findViewById(R.id.campo_email);
        campoCpf = findViewById(R.id.campo_cpf);

        campoDataNascimento = findViewById(R.id.campo_data_nascimento);
        campoCelular = findViewById(R.id.campo_celular);
        campoSenha = findViewById(R.id.campo_senha);
        campoRepetirSenha = findViewById(R.id.campo_repetir_senha);
        botaoRegistrar = findViewById(R.id.botao_registrar);
        textoFacaLogin = findViewById(R.id.texto_faca_login);
    }

    private void configurarListeners() {
        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        textoFacaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void registrarUsuario() {
        String nome = campoNome.getEditText().getText().toString().trim();
        String email = campoEmail.getEditText().getText().toString().trim();
        String cpf = campoCpf.getEditText().getText().toString().trim();
        String dataNasc = campoDataNascimento.getEditText().getText().toString().trim();
        String celular = campoCelular.getEditText().getText().toString().trim();
        String senha = campoSenha.getEditText().getText().toString();
        String repetirSenha = campoRepetirSenha.getEditText().getText().toString();

        if (!validarCampos(nome, email, cpf, dataNasc, celular, senha, repetirSenha)) {
            Toast.makeText(this, "Por favor, corrija os erros.", Toast.LENGTH_SHORT).show();
            return;
        }

        // --- AQUI COMEÇA O BANCO DE DADOS ---

        // 1. Cria uma instância do banco
        Database db = new Database(this);

        // 2. Tenta salvar
        boolean sucesso = db.adicionarUsuario(cpf, nome, email, dataNasc, celular, senha);

        if (sucesso) {
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
            // Volta para o login
            finish();
        } else {
            Toast.makeText(this, "Erro ao cadastrar. Tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCampos(String nome, String email, String cpf, String dataNasc, String celular, String senha, String repetirSenha) {
        boolean isValido = true;

        if (nome.isEmpty()) {
            campoNome.setError("Preencha seu nome");
            isValido = false;
        } else {
            campoNome.setError(null);
        }

        if (email.isEmpty()) {
            campoEmail.setError("Preencha seu e-mail");
            isValido = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            campoEmail.setError("Formato de e-mail inválido");
            isValido = false;
        } else {
            campoEmail.setError(null);
        }

        if (cpf.isEmpty()) {
            campoCpf.setError("Preencha seu CPF");
            isValido = false;
        } else if (cpf.length() != 11) {
            // Validação simples de 11 dígitos
            campoCpf.setError("CPF inválido (deve ter 11 dígitos)");
            isValido = false;
        } else {
            campoCpf.setError(null);
        }

        if (dataNasc.isEmpty()) {
            campoDataNascimento.setError("Preencha sua data de nascimento");
            isValido = false;
        } else {
            campoDataNascimento.setError(null);
        }


        if (celular.isEmpty()) {
            campoCelular.setError("Preencha seu celular");
            isValido = false;
        } else if (celular.length() < 10) {
            campoCelular.setError("Celular inválido (mínimo 10 dígitos)");
            isValido = false;
        } else {
            campoCelular.setError(null);
        }

        if (senha.isEmpty()) {
            campoSenha.setError("Preencha sua senha");
            isValido = false;
        } else if (senha.length() < 6) {
            campoSenha.setError("Senha deve ter no mínimo 6 caracteres");
            isValido = false;
        } else {
            campoSenha.setError(null);
        }

        if (repetirSenha.isEmpty()) {
            campoRepetirSenha.setError("Repita sua senha");
            isValido = false;
        } else if (!repetirSenha.equals(senha)) {
            campoRepetirSenha.setError("As senhas não são iguais");
            isValido = false;
        } else {
            campoRepetirSenha.setError(null);
        }

        return isValido;
    }
}
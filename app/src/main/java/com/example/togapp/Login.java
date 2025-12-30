package com.example.togapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    private TextInputLayout campoCpf;
    private TextInputLayout campoSenha;
    private MaterialButton botaoEntrar;
    private TextView textoEsqueciSenha;
    private MaterialButton botaoPrimeiroAcesso;
    private MaterialButton botaoPossuiConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        inicializarComponentes();
        configurarListeners();
    }

    private void inicializarComponentes() {
        campoCpf = findViewById(R.id.campo_cpf);
        campoSenha = findViewById(R.id.campo_senha);
        botaoEntrar = findViewById(R.id.botao_entrar);
        textoEsqueciSenha = findViewById(R.id.texto_esqueci_senha);
        botaoPrimeiroAcesso = findViewById(R.id.botao_primeiro_acesso);
        botaoPossuiConta = findViewById(R.id.botao_possui_conta);
    }

    private void configurarListeners() {
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerLogin();
            }
        });

        textoEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RecuperarSenha.class);
                startActivity(intent);
            }
        });

        botaoPrimeiroAcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Cadastro.class);
                startActivity(intent);
            }
        });

        botaoPossuiConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Modo 'Possuo Conta' selecionado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fazerLogin() {
        String cpf = campoCpf.getEditText().getText().toString().trim();
        String senha = campoSenha.getEditText().getText().toString().trim();

        if (cpf.isEmpty()) {
            campoCpf.setError("Por favor, insira seu CPF");
            return;
        } else {
            campoCpf.setError(null);
        }

        if (senha.isEmpty()) {
            campoSenha.setError("Por favor, insira sua senha");
            return;
        } else {
            campoSenha.setError(null);
        }

        Database db = new Database(this);

        boolean usuarioExiste = db.checkUser(cpf, senha);

        if (usuarioExiste) {
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

            String nomeUsuario = db.buscarNomePorCpf(cpf);

            SharedPreferences prefs = getSharedPreferences("DadosUsuario", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("nome_salvo", nomeUsuario);

            editor.apply();

            Intent intent = new Intent(Login.this, Menu.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "CPF ou Senha incorretos.", Toast.LENGTH_LONG).show();
            campoSenha.setError("Verifique seus dados");
        }
    }
}
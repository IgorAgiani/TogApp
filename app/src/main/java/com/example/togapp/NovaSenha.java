package com.example.togapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class NovaSenha extends AppCompatActivity {
    private TextInputLayout campoNovaSenha;
    private TextInputLayout campoConfirmarSenha;
    private MaterialButton botaoContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nova_senha);

        inicializarComponentes();

        configurarListeners();
    }

    private void inicializarComponentes() {
        campoNovaSenha = findViewById(R.id.campo_nova_senha);
        campoConfirmarSenha = findViewById(R.id.campo_confirmar_senha);
        botaoContinuar = findViewById(R.id.botao_continuar);
    }

    private void configurarListeners() {
        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarESalvarSenha();
            }
        });
    }

    private void validarESalvarSenha() {
        String senha = campoNovaSenha.getEditText().getText().toString();
        String confirmarSenha = campoConfirmarSenha.getEditText().getText().toString();

        boolean isValido = true;

        if (senha.isEmpty()) {
            campoNovaSenha.setError("Por favor, crie uma senha");
            isValido = false;
        } else if (senha.length() < 6) {
            campoNovaSenha.setError("A senha deve ter no mínimo 6 caracteres");
            isValido = false;
        } else {
            campoNovaSenha.setError(null);
        }

        if (confirmarSenha.isEmpty()) {
            campoConfirmarSenha.setError("Por favor, confirme sua senha");
            isValido = false;
        } else if (!confirmarSenha.equals(senha)) {
            campoConfirmarSenha.setError("As senhas não são iguais");
            isValido = false;
        } else {
            campoConfirmarSenha.setError(null);
        }

        if (!isValido) {
            return;
        }

        String celularRecebido = getIntent().getStringExtra("CELULAR_USUARIO");

        Database db = new Database(this);

        boolean sucesso = db.atualizarSenha(celularRecebido, senha);

        if (sucesso) {
            Toast.makeText(this, "Senha alterada com sucesso!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(NovaSenha.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Erro: Celular não encontrado no cadastro.", Toast.LENGTH_LONG).show();
        }
    }
}

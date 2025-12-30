package com.example.togapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class RecuperarSenha extends AppCompatActivity {

    private TextInputLayout campoCelular;
    private MaterialButton botaoContinuar;
    private MaterialButton botaoVoltarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recuperar_senha);

        inicializarComponentes();

        configurarListeners();
    }

    private void inicializarComponentes() {
        campoCelular = findViewById(R.id.campo_celular);
        botaoContinuar = findViewById(R.id.botao_continuar);
        botaoVoltarLogin = findViewById(R.id.botao_voltar_login);
    }

    private void configurarListeners() {
        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarEContinuar();
            }
        });

        botaoVoltarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void validarEContinuar() {
        String celular = campoCelular.getEditText().getText().toString().trim();

        if (celular.isEmpty()) {
            campoCelular.setError("Por favor, insira seu celular");
            return;
        } else if (celular.length() < 10) {
            campoCelular.setError("Número de celular inválido");
            return;
        } else {
            campoCelular.setError(null);
        }

        Toast.makeText(this, "Enviando código...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RecuperarSenha.this, RecuperaCodigo.class);

        intent.putExtra("CELULAR_USUARIO", celular);

        startActivity(intent);
    }
}

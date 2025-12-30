package com.example.togapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class RecuperaCodigo extends AppCompatActivity {
    private TextInputLayout campoCodigo;
    private MaterialButton botaoContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recupera_codigo);

        inicializarComponentes();

        configurarListeners();
    }

    private void inicializarComponentes() {
        campoCodigo = findViewById(R.id.campo_codigo);
        botaoContinuar = findViewById(R.id.botao_continuar);
    }

    private void configurarListeners() {
        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCodigoEContinuar();
            }
        });
    }

    private void validarCodigoEContinuar() {
        String codigo = campoCodigo.getEditText().getText().toString().trim();

        if (codigo.isEmpty()) {
            campoCodigo.setError("Por favor, insira o código");
            return;
        } else if (codigo.length() < 6) {
            campoCodigo.setError("O código deve ter 6 dígitos");
            return;
        } else {
            campoCodigo.setError(null);
        }

        if (codigo.equals("123456")) {
            Toast.makeText(this, "Código verificado!", Toast.LENGTH_SHORT).show();

            String celularRecebido = getIntent().getStringExtra("CELULAR_USUARIO");

            Intent intent = new Intent(RecuperaCodigo.this, NovaSenha.class);

            intent.putExtra("CELULAR_USUARIO", celularRecebido);

            startActivity(intent);
            finish();

        } else {
            campoCodigo.setError("Código inválido. Tente novamente.");
            Toast.makeText(this, "Código inválido.", Toast.LENGTH_SHORT).show();
        }
    }
}

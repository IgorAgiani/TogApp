package com.example.togapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Acionamentos extends AppCompatActivity {
    private ImageButton botaoNotificacoes;
    private ImageButton botaoMenuInicio;
    private ImageButton botaoMenuServicos;
    private ImageButton botaoMenuAcionamentos;
    private ImageButton botaoMenuChat;
    private TextView textoSaudacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acionamentos);
        inicializarComponentes();

        SharedPreferences prefs = getSharedPreferences("DadosUsuario", MODE_PRIVATE);
        String nomeUsuario = prefs.getString("nome_salvo", "Usuário");

        if (textoSaudacao != null) {
            textoSaudacao.setText("Olá,\n" + nomeUsuario + "!");
        }

        configurarListeners();

    }

    private void inicializarComponentes() {
        botaoNotificacoes = findViewById(R.id.botao_notificacoes);

        botaoMenuInicio = findViewById(R.id.botao_menu_inicio);
        botaoMenuServicos = findViewById(R.id.botao_menu_servicos);
        botaoMenuAcionamentos = findViewById(R.id.botao_menu_acionamentos);
        botaoMenuChat = findViewById(R.id.botao_menu_chat);

        textoSaudacao = findViewById(R.id.texto_saudacao);
    }

    private void configurarListeners() {
        botaoNotificacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Acionamentos.this, "Clicou em Notificações", Toast.LENGTH_SHORT).show();
            }
        });

        botaoMenuInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Acionamentos.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });

        botaoMenuServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Acionamentos.this, Servicos.class);
                startActivity(intent);
                finish();
            }
        });

        botaoMenuAcionamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Acionamentos.this, "Você já está em Acionamentos", Toast.LENGTH_SHORT).show();
            }
        });

        botaoMenuChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Acionamentos.this, Chat.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

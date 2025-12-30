package com.example.togapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Chat extends AppCompatActivity {
    private ImageButton botaoNotificacoes;
    private Button btCentralVendas;
    private Button btSAC;
    private Button btRouboFurto;
    private Button btAssistencia;
    private Button btColisao;
    private ImageButton botaoMenuInicio;
    private ImageButton botaoMenuServicos;
    private ImageButton botaoMenuAcionamentos;
    private ImageButton botaoMenuChat;
    private TextView textoSaudacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        inicializarComponentes();
        configurarListeners();

        SharedPreferences prefs = getSharedPreferences("DadosUsuario", MODE_PRIVATE);

        String nomeUsuario = prefs.getString("nome_salvo", "Usuário");

        if (textoSaudacao != null) {
            textoSaudacao.setText("Olá,\n" + nomeUsuario + "!");
        }
    }

    private void inicializarComponentes() {
        botaoNotificacoes = findViewById(R.id.botao_notificacoes);

        btCentralVendas = findViewById(R.id.btcentraldevendas);
        btSAC = findViewById(R.id.btSAC);
        btRouboFurto = findViewById(R.id.btRouboeFurto);
        btAssistencia = findViewById(R.id.btAssistencia24horas);
        btColisao = findViewById(R.id.btColisao);

        botaoMenuInicio = findViewById(R.id.botao_menu_inicio);
        botaoMenuServicos = findViewById(R.id.botao_menu_servicos);
        botaoMenuAcionamentos = findViewById(R.id.botao_menu_acionamentos);
        botaoMenuChat = findViewById(R.id.botao_menu_chat);

        textoSaudacao = findViewById(R.id.texto_saudacao);
    }

    private void configurarListeners() {
        botaoNotificacoes.setOnClickListener(v ->
                Toast.makeText(Chat.this, "Clicou em Notificações", Toast.LENGTH_SHORT).show()
        );

        btCentralVendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://wa.me/5511978419418?text=Ol%C3%A1!%20"));
                startActivity(intent);
            }
        });

        btSAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://wa.me/5511978419418?text=Ol%C3%A1!%20Preciso%20de%20ajuda."));
                startActivity(intent);
            }
        });

        btRouboFurto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:30030335"));
                startActivity(intent);
            }
        });

        btAssistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:08003278424"));
                startActivity(intent);
            }
        });

        btColisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0800-784-2410"));
                startActivity(intent);
            }
        });


        botaoMenuInicio.setOnClickListener(v -> {
            Intent intent = new Intent(Chat.this, Menu.class);
            startActivity(intent);
            finish();
        });

        botaoMenuServicos.setOnClickListener(v -> {
            Intent intent = new Intent(Chat.this, Servicos.class);
            startActivity(intent);
            finish();
        });

        botaoMenuAcionamentos.setOnClickListener(v -> {
            Intent intent = new Intent(Chat.this, Acionamentos.class);
            startActivity(intent);
            finish();
        });

        botaoMenuChat.setOnClickListener(v -> {
            Toast.makeText(Chat.this, "Você já está no Chat", Toast.LENGTH_SHORT).show();
        });
    }

    private void abrirDiscador(String numero) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + numero));
        startActivity(intent);
    }
}
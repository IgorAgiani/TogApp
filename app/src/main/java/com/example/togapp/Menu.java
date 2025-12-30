package com.example.togapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    private ImageButton botaoNotificacoes;
    private Button botaoDetalhesPlano;
    private Button botaoAbrirSinistro;
    private Button botaoFaturas;
    private Button botaoAssistencia24h;
    private ImageView bannerAnuncio;
    private ImageButton botaoMenuInicio;
    private ImageButton botaoMenuServicos;
    private ImageButton botaoMenuAcionamentos;
    private ImageButton botaoMenuChat;
    private TextView textoSaudacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
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

        botaoDetalhesPlano = findViewById(R.id.botao_detalhes_plano);
        botaoAbrirSinistro = findViewById(R.id.botao_abrir_sinistro);

        botaoFaturas = findViewById(R.id.botao_faturas);
        botaoAssistencia24h = findViewById(R.id.botao_assistencia_24h);

        bannerAnuncio = findViewById(R.id.banner_anuncio);

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
                // TODO: Navegar para a tela de Notificações
                Toast.makeText(Menu.this, "Clicou em Notificações", Toast.LENGTH_SHORT).show();
            }
        });

        botaoDetalhesPlano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlDoPdfDoDrive = "https://drive.google.com/file/d/1t7gLouXqCiZ7U-JAHu_ybAXmVDgDFwU_/view?usp=sharing";

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse(urlDoPdfDoDrive));

                startActivity(intent);
            }
        });

        botaoAbrirSinistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://avisarsinistro.suhaiseguradora.com/_/suhai/new/type"));
                startActivity(intent);
            }
        });

        botaoFaturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Servicos.class);
                startActivity(intent);
                finish();
            }
        });

        botaoAssistencia24h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:08003278424"));
                startActivity(intent);
            }
        });

        bannerAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://materiais.togarantido.com.br/embaixador-indique-e-ganhe-moto-ifood?bl_ref=PNPPZ"));
                startActivity(intent);
            }
        });

        botaoMenuInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this, "Você já está no Início", Toast.LENGTH_SHORT).show();
            }
        });

        botaoMenuServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Servicos.class);
                startActivity(intent);
                finish();
            }
        });

        botaoMenuAcionamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Acionamentos.class);
                startActivity(intent);
                finish();
            }
        });

        botaoMenuChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Chat.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
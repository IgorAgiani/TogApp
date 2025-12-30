package com.example.togapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import com.google.android.material.card.MaterialCardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView;

public class Servicos extends AppCompatActivity {
    private ImageButton botaoNotificacoes;
    private AutoCompleteTextView dropdownServicos;
    private MaterialButton botaoDetalhes;
    private MaterialButton botaoFechar;
    private MaterialCardView cardFatura1;
    private MaterialCardView cardFatura2;
    private Group grupoFaturas;
    private MaterialCardView cardSinistro;
    private MaterialCardView cardRenovacao;
    private TextView textoLinkSinistro;
    private TextView textoLinkRenovacao;
    private ImageButton botaoMenuInicio;
    private ImageButton botaoMenuServicos;
    private ImageButton botaoMenuAcionamentos;
    private ImageButton botaoMenuChat;
    private TextView textoSaudacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicos);
        inicializarComponentes();
        configurarDropdown();

        SharedPreferences prefs = getSharedPreferences("DadosUsuario", MODE_PRIVATE);
        String nomeUsuario = prefs.getString("nome_salvo", "Usuário");

        if (textoSaudacao != null) {
            textoSaudacao.setText("Olá,\n" + nomeUsuario + "!");
        }

        configurarListeners();

    }

    private void inicializarComponentes() {
        botaoNotificacoes = findViewById(R.id.botao_notificacoes);

        dropdownServicos = findViewById(R.id.dropdown_servicos_autocomplete);

        botaoDetalhes = findViewById(R.id.botao_detalhes);
        botaoFechar = findViewById(R.id.botao_fechar);
        cardFatura1 = findViewById(R.id.card_fatura_1);
        cardFatura2 = findViewById(R.id.card_fatura_2);

        grupoFaturas = findViewById(R.id.grupo_faturas);
        cardSinistro = findViewById(R.id.card_sinistro);
        cardRenovacao = findViewById(R.id.card_renovacao);
        textoLinkSinistro = findViewById(R.id.texto_link_sinistro);
        textoLinkRenovacao = findViewById(R.id.texto_link_renovacao);

        botaoMenuInicio = findViewById(R.id.botao_menu_inicio);
        botaoMenuServicos = findViewById(R.id.botao_menu_servicos);
        botaoMenuAcionamentos = findViewById(R.id.botao_menu_acionamentos);
        botaoMenuChat = findViewById(R.id.botao_menu_chat);

        textoSaudacao = findViewById(R.id.texto_saudacao);
    }

    private void configurarListeners() {
        botaoNotificacoes.setOnClickListener(v ->
                Toast.makeText(Servicos.this, "Clicou em Notificações", Toast.LENGTH_SHORT).show()
        );

        botaoDetalhes.setOnClickListener(v -> {
            cardFatura1.setVisibility(View.GONE);
            cardFatura2.setVisibility(View.VISIBLE);
        });

        botaoFechar.setOnClickListener(v -> {
            cardFatura1.setVisibility(View.VISIBLE);
            cardFatura2.setVisibility(View.GONE);
        });

        textoLinkSinistro.setOnClickListener(v -> {
            String link = textoLinkSinistro.getText().toString();
            if (link.startsWith("http")) {
                abrirLink(link);
            } else {
                Toast.makeText(Servicos.this, "Nenhum link disponível no momento", Toast.LENGTH_SHORT).show();
            }
        });

        textoLinkRenovacao.setOnClickListener(v -> {
            String link = textoLinkRenovacao.getText().toString();
            if (link.startsWith("http")) {
                abrirLink(link);
            } else {
                Toast.makeText(Servicos.this, "Nenhum link disponível no momento", Toast.LENGTH_SHORT).show();
            }
        });

        botaoMenuInicio.setOnClickListener(v -> {
            Intent intent = new Intent(Servicos.this, Menu.class);
            startActivity(intent);
            finish();
        });
        botaoMenuServicos.setOnClickListener(v ->
                Toast.makeText(Servicos.this, "Você já está em Serviços", Toast.LENGTH_SHORT).show()
        );
        botaoMenuAcionamentos.setOnClickListener(v -> {
            Intent intent = new Intent(Servicos.this, Acionamentos.class);
            startActivity(intent);
            finish();
        });
        botaoMenuChat.setOnClickListener(v -> {
            Intent intent = new Intent(Servicos.this, Chat.class);
            startActivity(intent);
            finish();
        });
    }

    private void configurarDropdown() {
        String[] servicos = getResources().getStringArray(R.array.servicos_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                servicos
        );
        dropdownServicos.setAdapter(adapter);

        dropdownServicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelecionado = (String) parent.getItemAtPosition(position);

                grupoFaturas.setVisibility(View.GONE);
                cardSinistro.setVisibility(View.GONE);
                cardRenovacao.setVisibility(View.GONE);

                switch (itemSelecionado) {
                    case "2ª via de boleto":
                        grupoFaturas.setVisibility(View.VISIBLE);
                        cardFatura1.setVisibility(View.VISIBLE);
                        cardFatura2.setVisibility(View.GONE);
                        break;

                    case "Processo Sinistro":
                        cardSinistro.setVisibility(View.VISIBLE);
                        textoLinkSinistro.setText("https://www.webprotecao.com.br/suhai");
                        break;

                    case "Renovação Apólice":
                        cardRenovacao.setVisibility(View.VISIBLE);
                        textoLinkRenovacao.setText("https://chatbot.togarantido.com.br/ifood-renovacao");
                        break;
                }
            }
        });
    }
    private void abrirLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
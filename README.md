# Tô Garantido App

![Status](http://img.shields.io/static/v1?label=STATUS&message=CONCLUÍDO&color=GREEN&style=for-the-badge)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)

## Sobre o projeto

O **Tô Garantido App** é uma solução mobile desenvolvida como Projeto de Extensão no **Centro Universitário Eniac**.

O objetivo do aplicativo é atender entregadores autônomos e parceiros de plataformas (como iFood e Rappi), oferecendo um canal de autoatendimento rápido e eficiente. O projeto visa resolver o gargalo operacional da corretora, automatizando serviços simples que antes dependiam de contato humano.

**Destaques do Problema x Solução:**
* **Problema:** Alta dependência de suporte humano para tarefas repetitivas (2ª via de boleto, dúvidas de apólice) e demora no atendimento telefônico.
* **Solução:** Um app que centraliza as demandas, projetado para reduzir em até **60%** a carga operacional do pós-venda e melhorar o CSAT (Satisfação do Cliente).
* **Impacto Social:** O projeto está alinhado à **ODS 9** (Indústria, Inovação e Infraestrutura), promovendo modernização tecnológica.

---

## Funcionalidades

O aplicativo conta com um fluxo de navegação linear para autenticação e hierárquico para os serviços principais:

- [x] **Autenticação Segura:** Login com validação de CPF e Senha.
- [x] **Cadastro de Usuários:** Registro completo com persistência de dados local (CRUD).
- [x] **Gestão de Senhas:** Funcionalidade de "Esqueci a Senha" e alteração de credenciais.
- [x] **Assistência 24h:** Botão de acionamento rápido que integra diretamente com o discador do celular (SOS).
- [x] **Carteira Digital:** Visualização de faturas em aberto e histórico de pagamentos.
- [x] **Gestão de Apólices:** Acesso rápido aos detalhes do plano contratado (via PDF).
- [x] **Abertura de Sinistros:** Integração via WebView para registro e acompanhamento de sinistros.

---

## Tecnologias Utilizadas

O projeto foi desenvolvido focando em performance e funcionamento offline para dados críticos:

* **Linguagem:** [Java](https://www.java.com/) (Lógica de controle e Activities).
* **Interface:** XML (Layouts responsivos e componentes como CardViews).
* **Banco de Dados:** [SQLite](https://www.sqlite.org/index.html) (Nativo do Android) - Utilizado para armazenar dados de cadastro e login localmente, garantindo velocidade sem depender 100% de conexão externa para autenticação.
* **IDE:** Android Studio.

---

## Como executar o projeto

### Pré-requisitos
Para rodar a aplicação, você precisará ter instalado:
* [Android Studio](https://developer.android.com/studio) (Versão mais recente recomendada).
* JDK (Java Development Kit) configurado.

### Passo a passo

1.  Clone este repositório:
    ```bash
    git clone https://github.com/IgorAgiani/TogApp.git
    ```
2.  Abra o **Android Studio** e selecione "Open an Existing Project".
3.  Navegue até a pasta clonada e selecione-a.
4.  Aguarde o Gradle sincronizar todas as dependências.
5.  Conecte um dispositivo físico via USB ou inicie um Emulador (AVD).
6.  Clique no botão **Run** (ícone de Play verde) ou pressione `Shift + F10`.

---

## Autores

Projeto desenvolvido pela equipe do Projeto de Extensão App Developer:

* **Igor Agiani Silva** - [LinkedIn](https://www.linkedin.com/in/igor-agiani/)
* **Cristiane Hernandes Barbosa**
* **João Victor Alves Santana**

---

<p align="center">
  Feito com dedicação para a disciplina de Projeto de Extensão do Eniac. 💙
</p>

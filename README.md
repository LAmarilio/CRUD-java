# CRUD API com Spring Boot + PostgreSQL

Este projeto é uma API REST desenvolvida em **Java (Spring Boot)** com integração ao **PostgreSQL**, rodando em **containers Docker**.  
Ele implementa um CRUD completo para a entidade `Pessoa`, com validação via **API Key**.

---

## 🚀 Tecnologias utilizadas
- Java 21
- Spring Boot
- PostgreSQL
- Maven
- Docker & Docker Compose

---

## 📂 Estrutura do projeto
- `controllers/` → Endpoints REST (`PessoaController`)
- `service/` → Regras de negócio (`PessoaService`)
- `repository/` → Acesso ao banco (`PessoaRepository`)
- `models/` → Entidade `Pessoa`
- `middlewares/` → Validação de API Key (`Validators`)
- `config_db/` → Conexão com PostgreSQL (`ConnectionFactory`)

---

## 🔑 Segurança
Todas as requisições exigem o header:

   X-API-KEY: <sua_chave>

A chave é configurada via variável de ambiente `API_KEY`. O projeto já vem com uma chave API definida no arquivo docker-compose, para testes, copiar ou alterar antes de rodar o comando docker-compose up --build.

---

## 🐳 Como rodar com Docker
1. Clone o repositório:
   ```bash
   git clone https://github.com/LAmarilio/CRUD-java.git
   cd CRUD-java
   ```
   
2. Suba os containers:
   ```bash
   docker-compose up --build
   ```

3. Acesse a API em:
   http://localhost:8080/pessoas

---

## 📖 Endpoints principais
- GET /pessoas → Lista todas as pessoas
- POST /pessoas → Cria uma nova pessoa
- GET /pessoas/{id} → Busca pessoa por ID
- PUT /pessoas/{id} → Atualiza pessoa
- DELETE /pessoas/{id} → Remove pessoa

---

## 🗄️ Banco de dados
A tabela pessoas é criada automaticamente via init.sql:
    ```sql
    
    CREATE TABLE pessoas (
      id UUID PRIMARY KEY,
      created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
      nome VARCHAR(100) NOT NULL,
      idade INT NOT NULL,
      cidade VARCHAR(100),
      estado VARCHAR(100),
      pais VARCHAR(100)
    );

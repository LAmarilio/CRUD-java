package com.example.demo.models;

import java.util.UUID;
import java.time.OffsetDateTime;

public class Pessoa {
    private UUID id;
    private OffsetDateTime createdAt;
    private String nome;
    private int idade;
    private String cidade;
    private String estado;
    private String pais;

    public Pessoa() {}

    public Pessoa(
            UUID id,
            OffsetDateTime createdAt,
            String nome,
            int idade,
            String cidade,
            String estado,
            String pais) {
        this.id = id;
        this.createdAt = createdAt;
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public Pessoa(
            String nome,
            int idade,
            String cidade,
            String estado,
            String pais) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}
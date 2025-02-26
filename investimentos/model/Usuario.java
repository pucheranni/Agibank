package com.agibank.investimentos.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private PerfilInvestidor perfil;
    private List<Simulacao> historicoSimulacoes = new ArrayList<>();

    // Constructor, getters, and setters
    public Usuario(String nome, String cpf, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public void adicionarSimulacao(Simulacao simulacao) {
        historicoSimulacoes.add(simulacao);
    }
}
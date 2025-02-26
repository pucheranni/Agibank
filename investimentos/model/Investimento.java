package com.agibank.investimentos.model;

public abstract class Investimento {
    private final String nome;
    private final boolean isentoIR;
    protected final double taxaBase;

    public Investimento(String nome, double taxaBase, boolean isentoIR) {
        this.nome = nome;
        this.taxaBase = taxaBase;
        this.isentoIR = isentoIR;
    }

    public abstract double calcularMontante(double capitalInicial, double aporteMensal, int meses);

    public double calcularIR(double rendimento, int meses) {
        if (isentoIR) return 0;
        if (meses <= 6) return rendimento * 0.225;
        if (meses <= 12) return rendimento * 0.20;
        if (meses <= 24) return rendimento * 0.175;
        return rendimento * 0.15;
    }

    // Getters
    public String getNome() { return nome; }
}
package com.agibank.investimentos.model;

public class InvestimentoPoupanca extends Investimento {
    public InvestimentoPoupanca() {
        super("Poupança", 0.06, true);
    }

    @Override
    public double calcularMontante(double capitalInicial, double aporteMensal, int meses) {
        double taxaMensal = this.taxaBase / 12;
        return CalculadoraInvestimento.calcularMontanteComAportes(capitalInicial, aporteMensal, meses, taxaMensal);
    }
}
package com.agibank.investimentos.utils;

public class CalculadoraInvestimento {
    public static double calcularMontante(double capitalInicial, double aporteMensal, int meses, double taxaAnual) {
        double taxaMensal = taxaAnual / 12;
        double montanteInicial = capitalInicial * Math.pow(1 + taxaMensal, meses);
        double montanteAportes = aporteMensal * (Math.pow(1 + taxaMensal, meses) - 1) / taxaMensal;
        return montanteInicial + montanteAportes;
    }
}
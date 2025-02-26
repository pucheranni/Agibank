package com.agibank.investimentos.utils;

public class CalculadoraInvestimento {
    public static double calcularMontanteComAportes(double capitalInicial, double aporteMensal, int meses, double taxaMensal) {
        double montanteInicial = capitalInicial * Math.pow(1 + taxaMensal, meses);
        double montanteAportes = aporteMensal * (Math.pow(1 + taxaMensal, meses) - 1) / taxaMensal;
        return montanteInicial + montanteAportes;
    }
}
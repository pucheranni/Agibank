package com.agibank.investimentos.model;

import java.time.LocalDateTime;
import java.util.List;

public class Simulacao {
    private final LocalDateTime data;
    private final double capitalInicial;
    private final double aporteMensal;
    private final int meses;
    private final List<Resultado> resultados;

    public Simulacao(double capitalInicial, double aporteMensal, int meses, List<Resultado> resultados) {
        this.data = LocalDateTime.now();
        this.capitalInicial = capitalInicial;
        this.aporteMensal = aporteMensal;
        this.meses = meses;
        this.resultados = resultados;
    }

    // Getters
}
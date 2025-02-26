package com.agibank.investimentos.service;

import com.agibank.investimentos.model.PerfilInvestidor;

public class PerfilService {
    public PerfilInvestidor determinarPerfil(int pontuacao) {
        if (pontuacao <= 4) return PerfilInvestidor.CONSERVADOR;
        if (pontuacao <= 7) return PerfilInvestidor.MODERADO;
        return PerfilInvestidor.AGRESSIVO;
    }

    public void executarQuestionario() {
        System.out.println("\n=== Questionário de Perfil ===");
        int pontuacao = 0;
        pontuacao += InputUtils.lerInteiro("1. Objetivo (1 - Preservação, 2 - Crescimento Moderado, 3 - Alto Crescimento): ");
        pontuacao += InputUtils.lerInteiro("2. Prazo (1 - Curto, 2 - Médio, 3 - Longo): ");
        pontuacao += InputUtils.lerInteiro("3. Tolerância ao Risco (1 - Baixa, 2 - Média, 3 - Alta): ");

        PerfilInvestidor perfil = determinarPerfil(pontuacao);
        System.out.println("Seu perfil: " + perfil);
    }
}
package com.agibank.investimentos;

import com.agibank.investimentos.service.*;
import com.agibank.investimentos.utils.InputUtils;

public class Main {
    private final PerfilService perfilService = new PerfilService();

    public static void main(String[] args) {
        new Main().exibirMenuPrincipal();
    }

    private void exibirMenuPrincipal() {
        while (true) {
            System.out.println("\n=== Simulador de Investimentos ===");
            System.out.println("1 - Questionário de Perfil do Investidor");
            System.out.println("2 - Simulação de Investimentos");
            System.out.println("3 - Sair");

            int opcao = InputUtils.lerInteiro("Selecione uma opção: ");
            switch (opcao) {
                case 1 -> perfilService.executarQuestionario();
                case 2 -> iniciarSimulacao();
                case 3 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void iniciarSimulacao() {
        double capital = InputUtils.lerDouble("Capital Inicial (R$): ");
        double aporte = InputUtils.lerDouble("Aporte Mensal (R$): ");
        int meses = InputUtils.lerInteiro("Tempo de Investimento (meses): ");

        // Implement simulation flow
    }
}
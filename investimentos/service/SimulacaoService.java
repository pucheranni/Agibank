package com.agibank.investimentos.service;

import com.agibank.investimentos.model.*;
import com.agibank.investimentos.utils.InputUtils;

public class SimulacaoService {
    public void simuladoresPoupanca(double capital, double aporte, int meses) {
        InvestimentoPoupanca poupanca = new InvestimentoPoupanca();
        while (true) {
            System.out.println("\n=== Simuladores de Poupança ===");
            System.out.println("1 - Projeção Futura");
            System.out.println("2 - Correção Passada");
            System.out.println("3 - Poupança x CDI");
            System.out.println("4 - Tabela de Rendimentos");
            System.out.println("5 - Percentual do CDI");
            System.out.println("6 - Rentabilidade Real");
            System.out.println("7 - Voltar");

            int opcao = InputUtils.lerInteiro("Selecione um simulador: ");
            switch (opcao) {
                case 1 -> calcularPoupancaFutura(poupanca, capital, aporte, meses);
                case 2 -> calcularCorrecaoPassada(poupanca, capital);
                case 3 -> compararComCDI(poupanca, capital, aporte, meses);
                case 4 -> exibirTabelaRendimentos(poupanca, capital, meses);
                case 5 -> calcularPercentualCDI(poupanca, capital, aporte, meses);
                case 6 -> calcularRentabilidadeReal(poupanca, capital, aporte, meses);
                case 7 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void calcularPoupancaFutura(InvestimentoPoupanca poupanca, double capital, double aporte, int meses) {
        double total = poupanca.calcularMontante(capital, aporte, meses);
        System.out.printf("\nProjeção da Poupança após %d meses:%n", meses);
        System.out.printf("Total Investido: R$ %.2f%n", capital + (aporte * meses));
        System.out.printf("Valor Final: R$ %.2f%n", total);
        System.out.printf("Rendimento Bruto: R$ %.2f%n", total - (capital + (aporte * meses)));
    }

    // Other methods for Poupança simulations...
}
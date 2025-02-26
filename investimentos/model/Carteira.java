import java.util.Scanner;

public class InvestimentoSimulador {
    private static String perfilInvestidor = "Não definido";
    private static final Scanner scanner = new Scanner(System.in);

    // Annual rates based on SELIC 2025 (13.25%) and market estimates
    private static final double SELIC_RATE = 0.1325;     // 13.25%
    private static final double CDI_RATE = 0.1325;       // CDI aligned with SELIC
    private static final double CDB_CDI_RATE = 0.1325;   // 13.25% (100% CDI)
    private static final double CRA_RATE = 0.12;         // 12%
    private static final double CRI_RATE = 0.11;         // 11%
    private static final double LCI_RATE = 0.1275;       // 12.75% (approx 96% CDI)
    private static final double LCA_RATE = 0.1275;       // 12.75% (approx 96% CDI)
    private static final double TESOURO_PREFIXADO_RATE = 0.12;    // 12%
    private static final double TESOURO_SELIC_RATE = 0.1325;      // 13.25%
    private static final double TESOURO_IPCA_RATE = 0.0656;       // 6.56% (IPCA 4.56% + 2%)
    private static final double POUPANCA_RATE = 0.06;    // 6% annual (approx 0.5% monthly)
    private static final double INFLATION_RATE = 0.0456; // 4.56% annual

    public static void main(String[] args) {
        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {
        while (true) {
            System.out.println("\n=== Simulador de Investimentos ===");
            System.out.println("1 - Questionário de Perfil do Investidor");
            System.out.println("2 - Simulação de Investimentos");
            System.out.println("3 - Sair");

            int opcao = lerInteiro("Selecione uma opção: ");
            switch (opcao) {
                case 1:
                    executarQuestionarioPerfil();
                    break;
                case 2:
                    executarSimulacoes();
                    break;
                case 3:
                    System.out.println("Obrigado por usar o simulador!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void executarSimulacoes() {
        System.out.println("\n=== Simulação de Investimentos ===");
        double capitalInicial = lerDouble("Capital Inicial (R$): ");
        double aporteMensal = lerDouble("Aporte Mensal (R$): ");
        int mesesInvestimento = lerInteiro("Tempo de Investimento (meses): ");

        while (true) {
            System.out.println("\n=== Simuladores Disponíveis ===");
            System.out.println("1 - Poupança");
            System.out.println("2 - Títulos Privados");
            System.out.println("3 - Tesouro Direto");
            System.out.println("4 - Renda Variável");
            System.out.println("5 - Comparativos Completos");
            System.out.println("6 - Voltar");

            int opcao = lerInteiro("Selecione uma categoria: ");
            switch (opcao) {
                case 1:
                    simuladoresPoupanca(capitalInicial, aporteMensal, mesesInvestimento);
                    break;
                case 2:
                    simuladoresTitulosPrivados(capitalInicial, aporteMensal, mesesInvestimento);
                    break;
                case 3:
                    simuladoresTesouroDireto(capitalInicial, aporteMensal, mesesInvestimento);
                    break;
                case 4:
                    simuladoresRendaVariavel(capitalInicial, aporteMensal, mesesInvestimento);
                    break;
                case 5:
                    simuladoresComparativos(capitalInicial, aporteMensal, mesesInvestimento);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Poupança Simulators
    private static void simuladoresPoupanca(double capital, double aporte, int meses) {
        while (true) {
            System.out.println("\n=== Simuladores de Poupança ===");
            System.out.println("1 - Projeção Futura");
            System.out.println("2 - Correção Passada pela Poupança");
            System.out.println("3 - Poupança x Fundo (CDI)");
            System.out.println("4 - Tabela de Rendimentos");
            System.out.println("5 - Percentual do CDI");
            System.out.println("6 - Rentabilidade Real (com inflação)");
            System.out.println("7 - Voltar");

            int opcao = lerInteiro("Selecione um simulador: ");
            switch (opcao) {
                case 1:
                    calcularPoupancaFutura(capital, aporte, meses);
                    break;
                case 2:
                    calcularCorrecaoPoupancaPassada(capital);
                    break;
                case 3:
                    compararPoupancaComCDI(capital, aporte, meses);
                    break;
                case 4:
                    tabelaRendimentosPoupanca(capital, meses);
                    break;
                case 5:
                    percentualCDI(capital, aporte, meses);
                    break;
                case 6:
                    calcularRentabilidadeRealPoupanca(capital, aporte, meses);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void calcularPoupancaFutura(double capital, double aporte, int meses) {
        double total = calcularInvestimento(capital, aporte, meses, POUPANCA_RATE);
        System.out.printf("\nProjeção da Poupança após %d meses:%n", meses);
        System.out.printf("Total Investido: R$ %.2f%n", capital + (aporte * meses));
        System.out.printf("Valor Final: R$ %.2f%n", total);
        System.out.printf("Rendimento Bruto: R$ %.2f%n", total - (capital + (aporte * meses)));
    }

    private static void calcularCorrecaoPoupancaPassada(double capital) {
        int meses = lerInteiro("Digite o número de meses passados: ");
        double total = capital * Math.pow(1 + (POUPANCA_RATE / 12), meses);
        System.out.printf("\nCorreção pela Poupança (%d meses atrás):%n", meses);
        System.out.printf("Valor Inicial: R$ %.2f%n", capital);
        System.out.printf("Valor Corrigido: R$ %.2f%n", total);
    }

    private static void compararPoupancaComCDI(double capital, double aporte, int meses) {
        double totalPoupanca = calcularInvestimento(capital, aporte, meses, POUPANCA_RATE);
        double totalCDI = calcularInvestimento(capital, aporte, meses, CDI_RATE);
        System.out.printf("\nPoupança x CDI (%d meses):%n", meses);
        System.out.printf("Poupança: R$ %.2f%n", totalPoupanca);
        System.out.printf("CDI (100%%): R$ %.2f%n", totalCDI);
        System.out.printf("Diferença: R$ %.2f%n", totalCDI - totalPoupanca);
    }

    private static void tabelaRendimentosPoupanca(double capital, int meses) {
        double taxaMensal = POUPANCA_RATE / 12;
        System.out.println("\nTabela de Rendimentos da Poupança:");
        System.out.println("Mês | Valor Acumulado");
        double total = capital;
        for (int i = 1; i <= meses; i++) {
            total *= (1 + taxaMensal);
            System.out.printf("%3d | R$ %.2f%n", i, total);
        }
    }

    private static void percentualCDI(double capital, double aporte, int meses) {
        double totalPoupanca = calcularInvestimento(capital, aporte, meses, POUPANCA_RATE);
        double totalCDI = calcularInvestimento(capital, aporte, meses, CDI_RATE);
        double percentual = (totalPoupanca / totalCDI) * 100;
        System.out.printf("\nPoupança como Percentual do CDI (%d meses): %.2f%%%n", meses, percentual);
    }

    private static void calcularRentabilidadeRealPoupanca(double capital, double aporte, int meses) {
        double totalNominal = calcularInvestimento(capital, aporte, meses, POUPANCA_RATE);
        double inflacaoMensal = INFLATION_RATE / 12;
        double totalReal = totalNominal / Math.pow(1 + inflacaoMensal, meses);
        System.out.printf("\nRentabilidade Real da Poupança (%d meses):%n", meses);
        System.out.printf("Valor Nominal: R$ %.2f%n", totalNominal);
        System.out.printf("Valor Real (descontada inflação): R$ %.2f%n", totalReal);
    }

    // Títulos Privados Simulators
    private static void simuladoresTitulosPrivados(double capital, double aporte, int meses) {
        while (true) {
            System.out.println("\n=== Títulos Privados ===");
            System.out.println("1 - Pós-fixados");
            System.out.println("2 - Prefixados");
            System.out.println("3 - Indexados ao IPCA + Juros");
            System.out.println("4 - Voltar");

            int opcao = lerInteiro("Selecione uma categoria: ");
            switch (opcao) {
                case 1:
                    simuladoresPosFixados(capital, aporte, meses);
                    break;
                case 2:
                    simuladoresPrefixados(capital, aporte, meses);
                    break;
                case 3:
                    simuladoresIpcaJuros(capital, aporte, meses);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void simuladoresPosFixados(double capital, double aporte, int meses) {
        while (true) {
            System.out.println("\n=== Pós-fixados ===");
            System.out.println("1 - CDB Pós-fixado");
            System.out.println("2 - LCI/LCA Pós-fixado");
            System.out.println("3 - Comparador Percentual CDI");
            System.out.println("4 - CDB Pós-fixado Líquido");
            System.out.println("5 - Tabela Taxas Equivalentes");
            System.out.println("6 - Correção pelo CDI (passado)");
            System.out.println("7 - Voltar");

            int opcao = lerInteiro("Selecione um simulador: ");
            switch (opcao) {
                case 1:
                    simularCDBPosFixado(capital, aporte, meses);
                    break;
                case 2:
                    simularLCIouLCA(capital, aporte, meses);
                    break;
                case 3:
                    comparadorPercentualCDI(capital, aporte, meses);
                    break;
                case 4:
                    simularCDBPosFixadoLiquido(capital, aporte, meses);
                    break;
                case 5:
                    tabelaTaxasEquivalentes(meses);
                    break;
                case 6:
                    correcaoCDIPassado(capital);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void simularCDBPosFixado(double capital, double aporte, int meses) {
        double percentualCDI = lerDouble("Percentual do CDI (ex: 100 para 100%): ") / 100;
        double total = calcularInvestimento(capital, aporte, meses, CDB_CDI_RATE * percentualCDI);
        System.out.printf("\nCDB Pós-fixado (%.0f%% CDI): R$ %.2f%n", percentualCDI * 100, total);
    }

    private static void simularLCIouLCA(double capital, double aporte, int meses) {
        double percentualCDI = lerDouble("Percentual do CDI (ex: 90 para 90%): ") / 100;
        double total = calcularInvestimento(capital, aporte, meses, LCI_RATE * percentualCDI);
        System.out.printf("\nLCI/LCA Pós-fixado (%.0f%% CDI): R$ %.2f%n", percentualCDI * 100, total);
    }

    private static void comparadorPercentualCDI(double capital, double aporte, int meses) {
        double taxa1 = lerDouble("Primeira taxa (% CDI): ") / 100;
        double taxa2 = lerDouble("Segunda taxa (% CDI): ") / 100;
        double total1 = calcularInvestimento(capital, aporte, meses, CDI_RATE * taxa1);
        double total2 = calcularInvestimento(capital, aporte, meses, CDI_RATE * taxa2);
        System.out.printf("\nComparação (%d meses):%n", meses);
        System.out.printf("Taxa 1 (%.0f%% CDI): R$ %.2f%n", taxa1 * 100, total1);
        System.out.printf("Taxa 2 (%.0f%% CDI): R$ %.2f%n", taxa2 * 100, total2);
    }

    private static void simularCDBPosFixadoLiquido(double capital, double aporte, int meses) {
        double percentualCDI = lerDouble("Percentual do CDI (ex: 100): ") / 100;
        double totalBruto = calcularInvestimento(capital, aporte, meses, CDB_CDI_RATE * percentualCDI);
        double ir = getIR(meses);
        double rendimento = totalBruto - (capital + aporte * meses);
        double totalLiquido = totalBruto - (rendimento * ir);
        System.out.printf("\nCDB Pós-fixado Líquido (%.0f%% CDI, %d meses):%n", percentualCDI * 100, meses);
        System.out.printf("Bruto: R$ %.2f%n", totalBruto);
        System.out.printf("Líquido (IR %.1f%%): R$ %.2f%n", ir * 100, totalLiquido);
    }

    private static void tabelaTaxasEquivalentes(int meses) {
        double ir = getIR(meses);
        System.out.println("\nTabela de Taxas Equivalentes (CDB x Outros, " + meses + " meses):");
        System.out.println("CDB | LCI/LCA | CRI | CRA");
        for (double cdbPercent = 0.8; cdbPercent <= 1.2; cdbPercent += 0.1) {
            double cdbRate = CDI_RATE * cdbPercent;
            double equivalentRate = cdbRate * (1 - ir);
            System.out.printf("%.0f%% | %.2f%% | %.2f%% | %.2f%%%n",
                    cdbPercent * 100, equivalentRate * 100, equivalentRate * 100, equivalentRate * 100);
        }
    }

    private static void correcaoCDIPassado(double capital) {
        int meses = lerInteiro("Meses passados: ");
        double total = capital * Math.pow(1 + (CDI_RATE / 12), meses);
        System.out.printf("\nCorreção pelo CDI (%d meses atrás): R$ %.2f%n", meses, total);
    }

    private static void simuladoresPrefixados(double capital, double aporte, int meses) {
        System.out.println("\n=== Prefixados ===");
        System.out.println("1 - CDB Prefixado");
        System.out.println("2 - LCI/LCA Prefixado x CDB");
        int opcao = lerInteiro("Selecione: ");
        if (opcao == 1) {
            double taxa = lerDouble("Taxa anual prefixada (%): ") / 100;
            double total = calcularInvestimento(capital, aporte, meses, taxa);
            System.out.printf("\nCDB Prefixado (%.2f%% a.a.): R$ %.2f%n", taxa * 100, total);
        } else if (opcao == 2) {
            double taxaCDB = lerDouble("Taxa CDB prefixada (%): ") / 100;
            double taxaLCI = lerDouble("Taxa LCI/LCA prefixada (%): ") / 100;
            double totalCDB = calcularInvestimento(capital, aporte, meses, taxaCDB);
            double totalLCI = calcularInvestimento(capital, aporte, meses, taxaLCI);
            System.out.printf("\nCDB (%.2f%%): R$ %.2f%n", taxaCDB * 100, totalCDB);
            System.out.printf("LCI/LCA (%.2f%%): R$ %.2f%n", taxaLCI * 100, totalLCI);
        }
    }

    private static void simuladoresIpcaJuros(double capital, double aporte, int meses) {
        double spread = lerDouble("Spread acima do IPCA (%): ") / 100;
        double taxa = INFLATION_RATE + spread;
        double total = calcularInvestimento(capital, aporte, meses, taxa);
        System.out.printf("\nCDB IPCA + %.2f%%: R$ %.2f%n", spread * 100, total);
    }

    // Tesouro Direto Simulators
    private static void simuladoresTesouroDireto(double capital, double aporte, int meses) {
        while (true) {
            System.out.println("\n=== Tesouro Direto ===");
            System.out.println("1 - Tesouro Prefixado (líquido)");
            System.out.println("2 - Taxas e Preços Prefixado");
            System.out.println("3 - Tesouro Selic x Poupança");
            System.out.println("4 - Tesouro Selic x CDB Pós-fixado");
            System.out.println("5 - Tesouro Selic x LCI/LCA");
            System.out.println("6 - Selic Acumulada entre Datas");
            System.out.println("7 - Correção pela Selic (passado)");
            System.out.println("8 - Voltar");

            int opcao = lerInteiro("Selecione um simulador: ");
            switch (opcao) {
                case 1:
                    calcularTesouroPrefixadoLiquido(capital, aporte, meses);
                    break;
                case 2:
                    taxasPrecosTesouroPrefixado();
                    break;
                case 3:
                    compararTesouroSelicPoupanca(capital, aporte, meses);
                    break;
                case 4:
                    compararTesouroSelicCDB(capital, aporte, meses);
                    break;
                case 5:
                    compararTesouroSelicLCI(capital, aporte, meses);
                    break;
                case 6:
                    selicAcumuladaEntreDatas();
                    break;
                case 7:
                    correcaoSelicPassado(capital);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void calcularTesouroPrefixadoLiquido(double capital, double aporte, int meses) {
        double totalBruto = calcularInvestimento(capital, aporte, meses, TESOURO_PREFIXADO_RATE);
        double ir = getIR(meses);
        double rendimento = totalBruto - (capital + aporte * meses);
        double totalLiquido = totalBruto - (rendimento * ir);
        System.out.printf("\nTesouro Prefixado (%d meses):%n", meses);
        System.out.printf("Bruto: R$ %.2f%n", totalBruto);
        System.out.printf("Líquido (IR %.1f%%): R$ %.2f%n", ir * 100, totalLiquido);
    }

    private static void taxasPrecosTesouroPrefixado() {
        double taxa = lerDouble("Taxa anual (%): ") / 100;
        int meses = lerInteiro("Meses: ");
        double preco = 1000 / Math.pow(1 + (taxa / 12), meses);
        System.out.printf("\nPreço do Título (R$ 1000 a %.2f%% a.a., %d meses): R$ %.2f%n", taxa * 100, meses, preco);
    }

    private static void compararTesouroSelicPoupanca(double capital, double aporte, int meses) {
        double selicTotal = calcularInvestimento(capital, aporte, meses, TESOURO_SELIC_RATE);
        double poupancaTotal = calcularInvestimento(capital, aporte, meses, POUPANCA_RATE);
        System.out.printf("\nTesouro Selic x Poupança (%d meses):%n", meses);
        System.out.printf("Tesouro Selic: R$ %.2f%n", selicTotal);
        System.out.printf("Poupança: R$ %.2f%n", poupancaTotal);
    }

    private static void compararTesouroSelicCDB(double capital, double aporte, int meses) {
        double selicTotal = calcularInvestimento(capital, aporte, meses, TESOURO_SELIC_RATE);
        double cdbTotal = calcularInvestimento(capital, aporte, meses, CDB_CDI_RATE);
        System.out.printf("\nTesouro Selic x CDB (%d meses):%n", meses);
        System.out.printf("Tesouro Selic: R$ %.2f%n", selicTotal);
        System.out.printf("CDB (100%% CDI): R$ %.2f%n", cdbTotal);
    }

    private static void compararTesouroSelicLCI(double capital, double aporte, int meses) {
        double selicTotal = calcularInvestimento(capital, aporte, meses, TESOURO_SELIC_RATE);
        double lciTotal = calcularInvestimento(capital, aporte, meses, LCI_RATE);
        System.out.printf("\nTesouro Selic x LCI (%d meses):%n", meses);
        System.out.printf("Tesouro Selic: R$ %.2f%n", selicTotal);
        System.out.printf("LCI (100%%): R$ %.2f%n", lciTotal);
    }

    private static void selicAcumuladaEntreDatas() {
        int meses = lerInteiro("Diferença em meses entre datas: ");
        double acumulada = Math.pow(1 + (SELIC_RATE / 12), meses) - 1;
        System.out.printf("\nSelic acumulada (%d meses): %.2f%%%n", meses, acumulada * 100);
    }

    private static void correcaoSelicPassado(double capital) {
        int meses = lerInteiro("Meses passados: ");
        double total = capital * Math.pow(1 + (SELIC_RATE / 12), meses);
        System.out.printf("\nCorreção pela Selic (%d meses atrás): R$ %.2f%n", meses, total);
    }

    // Renda Variável Simulators
    private static void simuladoresRendaVariavel(double capital, double aporte, int meses) {
        System.out.println("\n=== Renda Variável ===");
        System.out.println("1 - Coeficiente de Correlação (placeholder)");
        int opcao = lerInteiro("Selecione: ");
        if (opcao == 1) {
            System.out.println("Funcionalidade de correlação não implementada. Forneça séries de dados para cálculo.");
        }
    }

    // Comparativos Completos with Explicit Output
    private static void simuladoresComparativos(double capital, double aporte, int meses) {
        double totalInvestido = capital + (aporte * meses);
        int dias = meses * 30; // Approximate days

        // Calculate gross returns
        double poupancaResult = calcularInvestimento(capital, aporte, meses, POUPANCA_RATE);
        double cdbResult = calcularInvestimento(capital, aporte, meses, CDB_CDI_RATE);
        double lciResult = calcularInvestimento(capital, aporte, meses, LCI_RATE);
        double craResult = calcularInvestimento(capital, aporte, meses, CRA_RATE);
        double criResult = calcularInvestimento(capital, aporte, meses, CRI_RATE);
        double prefixadoResult = calcularInvestimento(capital, aporte, meses, TESOURO_PREFIXADO_RATE);
        double selicResult = calcularInvestimento(capital, aporte, meses, TESOURO_SELIC_RATE);
        double ipcaResult = calcularInvestimento(capital, aporte, meses, TESOURO_IPCA_RATE);

        // Calculate net returns (considering IR where applicable)
        double ir = getIR(meses);
        double poupancaRendimento = poupancaResult - totalInvestido;
        double poupancaLiquido = poupancaResult; // Poupança is tax-exempt
        double cdbRendimento = cdbResult - totalInvestido;
        double cdbImposto = cdbRendimento * ir;
        double cdbLiquido = cdbResult - cdbImposto;
        double lciRendimento = lciResult - totalInvestido;
        double lciLiquido = lciResult; // LCI/LCA tax-exempt

        // Calculate percentages
        double poupancaPercent = (poupancaRendimento / totalInvestido) * 100;
        double cdbPercent = (cdbLiquido - totalInvestido) / totalInvestido * 100;
        double lciPercent = (lciRendimento / totalInvestido) * 100;

        // Detailed Output
        System.out.println("\n=== Investimento ===");
        System.out.printf("Valor da Aplicação: R$ %.2f%n", totalInvestido);
        System.out.println("Vencimento: " + meses + " meses");
        System.out.println("Tipo de período: Mensal");
        System.out.printf("Dias: %d%n", dias);
        System.out.printf("Taxa DI: %.2f%% ao ano%n", CDI_RATE * 100);
        System.out.printf("Taxa SELIC: %.2f%% ao ano%n", SELIC_RATE * 100);
        System.out.printf("CDB/RDB/LC: %.0f%% DI%n", (CDB_CDI_RATE / CDI_RATE) * 100);
        System.out.printf("LCI/LCA: %.0f%% DI%n", (LCI_RATE / CDI_RATE) * 100);

        System.out.println("\n## Simulação");
        System.out.println("Simulação da rentabilidade do seu investimento conforme o tipo de aplicação:");

        // Poupança
        System.out.println("\nPoupança");
        System.out.printf("Valor Investido: R$ %.2f%n", totalInvestido);
        System.out.printf("Rendimento Bruto: R$ %.2f%n", poupancaRendimento);
        System.out.printf("Rendimento Líquido: R$ %.2f%n", poupancaRendimento); // No tax
        System.out.printf("Valor Total Líquido: R$ %.2f%n", poupancaLiquido);
        System.out.printf("%.2f%%%n", poupancaPercent);

        // CDB/RDB
        System.out.println("\nCDB / RDB");
        System.out.printf("Valor Investido: R$ %.2f%n", totalInvestido);
        System.out.printf("Rendimento Bruto: R$ %.2f%n", cdbRendimento);
        System.out.printf("Imposto de Renda: R$ %.2f%n", cdbImposto);
        System.out.printf("%.0f%%%n", ir * 100);
        System.out.printf("Rendimento Líquido: R$ %.2f%n", cdbRendimento - cdbImposto);
        System.out.printf("Valor Total Líquido: R$ %.2f%n", cdbLiquido);
        System.out.printf("%.2f%%%n", cdbPercent);

        // LCI/LCA
        System.out.println("\nLCI / LCA");
        System.out.printf("Valor Investido: R$ %.2f%n", totalInvestido);
        System.out.printf("Rendimento Bruto: R$ %.2f%n", lciRendimento);
        System.out.printf("Rendimento Líquido: R$ %.2f%n", lciRendimento); // No tax
        System.out.printf("Valor Total Líquido: R$ %.2f%n", lciLiquido);
        System.out.printf("%.2f%%%n", lciPercent);

        // Additional Results (simplified output for others)
        System.out.println("\nOutras Aplicações (Resumo):");
        System.out.printf("CRA (%.2f%% a.a.): R$ %.2f%n", CRA_RATE * 100, craResult);
        System.out.printf("CRI (%.2f%% a.a.): R$ %.2f%n", CRI_RATE * 100, criResult);
        System.out.printf("Tesouro Prefixado (%.2f%% a.a.): R$ %.2f%n", TESOURO_PREFIXADO_RATE * 100, prefixadoResult);
        System.out.printf("Tesouro Selic (%.2f%% a.a.): R$ %.2f%n", TESOURO_SELIC_RATE * 100, selicResult);
        System.out.printf("Tesouro IPCA+ (%.2f%% a.a.): R$ %.2f%n", TESOURO_IPCA_RATE * 100, ipcaResult);

        // Recommendation
        double maxLiquido = Math.max(cdbLiquido, Math.max(poupancaLiquido, lciLiquido));
        String recomendacao = maxLiquido == cdbLiquido ? "CDB/RDB" :
                maxLiquido == poupancaLiquido ? "Poupança" :
                        "LCI/LCA";
        System.out.printf("\nRecomendação: %s (R$ %.2f) por oferecer o maior retorno líquido.%n", recomendacao, maxLiquido);
    }

    // Core Calculation Methods
    private static double calcularInvestimento(double capitalInicial, double aporteMensal, int meses, double taxaAnual) {
        double taxaMensal = taxaAnual / 12;
        double montanteInicial = capitalInicial * Math.pow(1 + taxaMensal, meses);
        double montanteAportes = aporteMensal * (Math.pow(1 + taxaMensal, meses) - 1) / taxaMensal;
        return montanteInicial + montanteAportes;
    }

    private static double getIR(int meses) {
        if (meses <= 6) return 0.225;
        if (meses <= 12) return 0.20;
        if (meses <= 24) return 0.175;
        return 0.15;
    }

    private static void executarQuestionarioPerfil() {
        System.out.println("\n=== Questionário de Perfil ===");
        int pontuacao = 0;
        pontuacao += lerInteiro("1. Objetivo (1 - Preservação, 2 - Crescimento Moderado, 3 - Alto Crescimento): ");
        pontuacao += lerInteiro("2. Prazo (1 - Curto, 2 - Médio, 3 - Longo): ");
        pontuacao += lerInteiro("3. Tolerância ao Risco (1 - Baixa, 2 - Média, 3 - Alta): ");
        perfilInvestidor = pontuacao <= 4 ? "Conservador" : pontuacao <= 7 ? "Moderado" : "Arrojado";
        System.out.println("Seu perfil: " + perfilInvestidor);
    }

    // Helper Methods
    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Digite um número inteiro.");
            System.out.print(mensagem);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida! Digite um número válido.");
            System.out.print(mensagem);
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}
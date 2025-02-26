package com.agibank.investimentos.utils;

import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Digite um número inteiro.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida! Digite um número válido.");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
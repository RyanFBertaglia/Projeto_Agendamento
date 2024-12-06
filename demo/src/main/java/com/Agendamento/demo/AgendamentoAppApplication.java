package com.Agendamento.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Scanner;

@SpringBootApplication
public class AgendamentoAppApplication {

	public static void main(String[] args) {
			SpringApplication.run(AgendamentoAppApplication.class, args);

		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o número de linhas: ");
		int linhasUsadas = sc.nextInt();
		sc.nextLine();

		String[] principal = new String[linhasUsadas];
		for (int i = 0; i < linhasUsadas; i++) {
			System.out.println("Digite a linha " + (i + 1) + ": ");
			principal[i] = sc.nextLine();
		}
		sc.close();

		// Ordenar as linhas por comprimento (do maior para o menor) usando Bubble Sort
		String temporaria;
		for (int i = 0; i < principal.length; ++i) {
			for (int j = 0; j < (principal.length - 1); ++j) {
				if (principal[j].length() < principal[j + 1].length()) {
					temporaria = principal[j];
					principal[j] = principal[j + 1];
					principal[j + 1] = temporaria;
				}
			}
		}

		System.out.println("Linhas ordenadas por comprimento (maior para menor):");
		for (String linha : principal) {
			System.out.println(linha);
		}
	}
}

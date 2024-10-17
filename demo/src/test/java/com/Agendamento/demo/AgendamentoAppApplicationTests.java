package com.Agendamento.demo;


import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		long valorantigo=0;
		long valorMaisAntigo=0;
		long valoratual=1;
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		sc.close();

		for(int i=0; i<a; i++){
			valoratual=valorMaisAntigo+valoratual;
			System.out.printf("%d\n", valoratual);
			valorMaisAntigo = valorantigo;
			valorantigo = valoratual;
		}

		System.out.println("Hello World");
	}


}


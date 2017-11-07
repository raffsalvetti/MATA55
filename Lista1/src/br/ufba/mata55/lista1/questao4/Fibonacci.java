package br.ufba.mata55.lista1.questao4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fibonacci {
	
	public Fibonacci(){
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n;
		try {
			System.out.print("Informe a posição: ");
			n = Integer.parseInt(bufferedReader.readLine());
			System.out.print("Sequencia : ");
			int r = calcular(n);
			System.out.println("Numero referente a " + n + " posição = " + r);
		} catch (Exception e) {}
	}
	
	private int calcular(int n) {
		int k1 = 0, k2 = 1, t;
		for (int i = 0; i < n; i++) {
			System.out.print(k2 + " ");
			t = k1;
			k1 = k2;
			k2 = t + k1;
		}
		System.out.println("");
		return k1;
	}
}

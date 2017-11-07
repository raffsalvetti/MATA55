package br.ufba.mata55.lista2;

public class Palindromo {
	public Palindromo(String nome) {
		System.out.println((isPalindrome(nome.toCharArray())?"É":"Não é") + " palindromo");
	}
	
	public boolean isPalindrome(char[] nome) {
		for (int i = 0; i < nome.length / 2; i++) {
			if (nome[i] != nome[nome.length - i - 1])
				return false;
		}
		return true;
	}
}

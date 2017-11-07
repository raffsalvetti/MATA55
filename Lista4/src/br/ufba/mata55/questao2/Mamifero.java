package br.ufba.mata55.questao2;

public class Mamifero extends Animal {

	private String alimento;
	private static int CRIADOS = 0;
	
	public Mamifero(String nome, String cor, double comprimento, double velocidade, int numeroPatas, String alimento) {
		super(nome, cor, "terra", comprimento, velocidade, numeroPatas);
		this.alimento = alimento;
		CRIADOS++;
	}

	public Mamifero() {
		super();
		System.out.println("ALIMENTO: ");
		alteraAlimento(lerEntrada());
		CRIADOS++;
	}
	
	public static int NASCIMENTOS() {
		return CRIADOS;
	}

	public String getAlimento() {
		return alimento;
	}

	public void alteraAlimento(String alimento) {
		this.alimento = alimento;
	}
	
	
}

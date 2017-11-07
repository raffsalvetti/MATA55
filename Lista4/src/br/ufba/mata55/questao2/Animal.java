package br.ufba.mata55.questao2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Animal {
	private String nome, cor, ambiente;
	private double comprimento, velocidade;
	private int numeroPatas = 4;
	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	
	private static int CRIADOS = 0;
	
	public Animal(String nome, String cor, String ambiente, double comprimento, double velocidade, int numeroPatas) {
		super();
		this.nome = nome;
		this.cor = cor;
		this.ambiente = ambiente;
		this.comprimento = comprimento;
		this.velocidade = velocidade;
		this.numeroPatas = numeroPatas;
		CRIADOS++;
	}
	
	public Animal() {
		System.out.print("DIGITE O NOME: ");
		this.nome = lerEntrada();
		System.out.print("DIGITE A COR: ");
		this.cor = lerEntrada();
		System.out.print("DIGITE O AMBIENTE: ");
		this.ambiente = lerEntrada();
		System.out.print("DIGITE O COMPRIMENTO: ");
		this.comprimento = Double.parseDouble(lerEntrada());
		System.out.print("DIGITE O VELOCIDADE: ");
		this.velocidade = Double.parseDouble(lerEntrada());
		System.out.print("DIGITE O NUMERO DE PATAS: ");
		this.numeroPatas = Integer.parseInt(lerEntrada());
		CRIADOS++;
	}
	
	public static int NASCIMENTOS() {
		return CRIADOS;
	}
	
	public String lerEntrada() {
		try {
			return bufferedReader.readLine();
		} catch (Exception ex){
			return null;
		}
	}
	
	public String getNome() {
		return nome;
	}
	public void alteraNome(String nome) {
		this.nome = nome;
	}
	public String getCor() {
		return cor;
	}
	public void alteraCor(String cor) {
		this.cor = cor;
	}
	public String getAmbiente() {
		return ambiente;
	}
	public void alteraAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	public double getComprimento() {
		return comprimento;
	}
	public void alteraComprimento(double comprimento) {
		this.comprimento = comprimento;
	}
	public double getVelocidade() {
		return velocidade;
	}
	public void alteraVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}
	public int getNumeroPatas() {
		return numeroPatas;
	}
	public void alteraPatas(int numeroPatas) {
		this.numeroPatas = numeroPatas;
	}
	
	public void dados() {
		System.out.println("Nome: " + getNome());
		System.out.println("Cor: " + getCor());
		System.out.println("Ambiente: " + getAmbiente());
		System.out.println("Comprimento: " + getComprimento());
		System.out.println("Velocidade: " + getVelocidade());
		System.out.println("Numero de patas: " + getNumeroPatas());
	}
}

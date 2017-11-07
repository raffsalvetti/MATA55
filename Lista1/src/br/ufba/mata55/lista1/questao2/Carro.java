package br.ufba.mata55.lista1.questao2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import br.ufba.mata55.lista1.questao1.Pessoa;

public class Carro {
	private String cor, modelo, marca;
	private int potencia, velocidadeAtual, velocidadeMaxima;
	private boolean ligado;
	private Pessoa proprietario;
	
	public Carro(){}
	
	public Carro(String cor, String modelo, String marca, int potencia,
			int velocidadeAtual, int velocidadeMaxima, boolean ligado,
			Pessoa proprietario) {
		this.cor = cor;
		this.modelo = modelo;
		this.marca = marca;
		this.potencia = potencia;
		this.velocidadeAtual = velocidadeAtual;
		this.velocidadeMaxima = velocidadeMaxima;
		this.ligado = ligado;
		this.proprietario = proprietario;
	}
	
	public void ligar() {
		if(!ligado)
			ligado = true;
	}
	
	public void desligar() {
		if(ligado)
			ligado = false;
	}
	
	public void acelerar(int velocidade) {
		if(velocidade <= velocidadeMaxima)
			velocidadeAtual = velocidade;
	}
	
	public void freiar(int freio) {
		if(velocidadeAtual - freio < 0)
			velocidadeAtual = 0;
		else
			velocidadeAtual -= freio;
	}
	
	public void cadastrarCarro() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("CADASTRO DE CARRO");
			System.out.print("COR: ");
			cor = bufferedReader.readLine();
			System.out.print("MODELO: ");
			modelo = bufferedReader.readLine();
			System.out.print("MARCA: ");
			marca = bufferedReader.readLine();
			System.out.print("POTENCIA: ");
			potencia = Integer.parseInt(bufferedReader.readLine());
			System.out.print("VELOCIDADE ATUAL: ");
			velocidadeAtual = Integer.parseInt(bufferedReader.readLine());
			System.out.print("VELOCIDADE MAXIMA: ");
			velocidadeMaxima = Integer.parseInt(bufferedReader.readLine());
			System.out.print("LIGADO? [SIM/NAO]: ");
			ligado = bufferedReader.readLine().equalsIgnoreCase("SIM");
			proprietario = new Pessoa();
			proprietario.cadastrarPessoa();
		} catch (Exception e) {
			System.out.println("Erro na leitura dos dados");
		}
	}

	public void exibirDadosCarro() {
		System.out.println("cor = " + cor);
		System.out.println("modelo = " + modelo);
		System.out.println("marca = " + marca);
		System.out.println("potencia = " + potencia);
		System.out.println("velocidadeAtual = " + velocidadeAtual);
		System.out.println("velocidadeMaxima = " + velocidadeMaxima);
		System.out.println("ligado = " + (ligado ? "Ligado" : "Desligado"));
		System.out.println("proprietario = " + proprietario.getNome() + " (" + proprietario.getNacionalidade() + ")");
	}
	
	
}

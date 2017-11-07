package br.ufba.mata55.lista1.questao1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Pessoa {
	private String nome, rg, cpf, nacionalidade;
	private Date dataNascimento;
		
	public Pessoa() { }
	
	public Pessoa(String nome) {
		this.nome = nome;
	}
	
	public Pessoa(String nome, String rg, String cpf) {
		super();
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
	}

	public Pessoa(String nome, String rg, String cpf, String nacionalidade, Date dataNascimento) {
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.nacionalidade = nacionalidade;
		this.dataNascimento = dataNascimento;
	}

	public int Idade(Date dataAtual) {
		Calendar cDataAtual = Calendar.getInstance();
		cDataAtual.setTime(dataAtual);
		Calendar cDataNascimento = Calendar.getInstance();
		cDataNascimento.setTime(dataNascimento);
		if(cDataAtual.before(cDataNascimento)) {
			return 0;
		} else {
			return (cDataAtual.get(Calendar.YEAR) - cDataNascimento.get(Calendar.YEAR));
		}
	}
	
	public void modificarCPF(String nome, String rg, String cpfVelho, String cpfNovo) {
		if(nome.equals(this.nome) && rg.equals(this.rg) && cpfVelho.equals(cpf))
			cpf = cpfNovo;
	}
	
	public void cadastrarPessoa() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("CADASTRO DE PESSOA");
			System.out.print("NOME: ");
			nome = bufferedReader.readLine();
			System.out.print("RG: ");
			rg = bufferedReader.readLine();
			System.out.print("CPF: ");
			cpf = bufferedReader.readLine();
			System.out.print("NACIONALIDADE: ");
			nacionalidade = bufferedReader.readLine();
			System.out.print("DATA DE NASCIMENTO (DD/MM/AAAA): ");
			dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(bufferedReader.readLine());
		} catch (Exception e) {
			System.out.println("Erro na leitura dos dados");
		}
	}
	
	public void exibirInformacoes() {
		System.out.println("nome = " + nome);
		System.out.println("rg = " + rg);
		System.out.println("cpf = " + cpf);
		System.out.println("nacionalidade = " + nacionalidade);
		System.out.println("dataNascimento = " + new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento));
	}

	public String getNome() {
		return nome;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
}

package br.ufba.mata55.lista2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Aluno {
	private String nome, matricula, nomePai, nomeMae;
	private Serie serie;
	
	public Aluno() {
		cadastrarAluno();
	}
	
	public Aluno(String nome, String matricula, String nomePai, String nomeMae, Serie serie) {
		this.nome = nome;
		this.matricula = matricula;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		matricular(serie);
		serie.matricularAluno(this);
	}
	
	public void cadastrarAluno() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("CADASTRO DE ALUNO");
		try {
			System.out.print("NOME: ");
			nome = bufferedReader.readLine();
			System.out.print("MATRICULA: ");
			matricula = bufferedReader.readLine();
			System.out.print("NOME DO PAI: ");
			nomePai = bufferedReader.readLine();
			System.out.print("NOME DA MÃE: ");
			nomeMae = bufferedReader.readLine();
		} catch (IOException e) {
			System.out.println("Erro na leitura dos dados");
		}
	}
	
	public Serie serieMatriculada() {
		return serie;
	}

	public boolean desmatricular() {
		if(serie == null) {
			return false;
		} else {
			serie.desmatricularAluno(this);
			serie = null;
		}
		return true;
	}
	
	public boolean matricular(Serie serie) {
		if(this.serie != null || serie == null) {
			return false;
		} else {
			this.serie = serie;
		}
		return true;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public Serie getSerie() {
		return serie;
	}

	public void exibirInformacoes() {
		System.out.println(" ------------ ALUNO ------------");
		System.out.println("nome = " + nome);
		System.out.println("matricula = " + matricula);
		System.out.println("nomePai = " + nomePai);
		System.out.println("nomeMae = " + nomeMae);
		System.out.println("serie = " + serie.getIdentificador());
		System.out.println(" -------------------------------");
	}
}

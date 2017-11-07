package br.ufba.mata55.lista2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Serie {
	private int identificador;
	private String nomeProfessor;
	private int anoCriacao;
	
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	public Serie() { }
	
	public Serie(int identificador, String nomeProfessor, int anoCriacao) {
		this.identificador = identificador;
		this.nomeProfessor = nomeProfessor;
		this.anoCriacao = anoCriacao;
	}
	
	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public int getAnoCriacao() {
		return anoCriacao;
	}

	public void setAnoCriacao(int anoCriacao) {
		this.anoCriacao = anoCriacao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public boolean cadastrarProfessor(String professor) {
		if(nomeProfessor.equals("")) {
			this.nomeProfessor = professor;
			return true;
		}
		return false;
	}
	
	public boolean alunoMatriculado(String matricula) {
		for (Aluno aluno : alunos) {
			if(aluno.getMatricula().equals(matricula))
				return true;
		}
		return false;
	}
	
	/**
	 * professor, não faz sentido usar somente o numero de matricula para matricular um aluno
	 * pois algumas informações ficam inconsistentes. Criei os dois metodos para atender os
	 * prerequisitos da lista 
	 * @param matriculaAluno numero da matricula
	 * @return matriculado ou nao
	 */
	public boolean matricularAluno(String matriculaAluno) {
		if(alunoMatriculado(matriculaAluno)){
			return false;
		} else {
			// esse construtor ja adiciona o aluno na lista
			// nao eh necessario adicionar manualmente na lista
			Aluno a = new Aluno("", matriculaAluno, "", "", this);
			return true;
		}
	}
	
	public boolean matricularAluno(Aluno aluno) {
		return alunoMatriculado(aluno.getMatricula()) ? false : alunos.add(aluno); 
	}
	
	public boolean desmatricularAluno(Aluno aluno) {
		return alunoMatriculado(aluno.getMatricula()) ? alunos.remove(aluno): false;
	}
	
	public boolean desmatricularAluno(String matriculaAluno) {
		if(alunoMatriculado(matriculaAluno)) {
			Iterator<Aluno> iterator = alunos.iterator();
			while (iterator.hasNext()) {
				Aluno a = iterator.next();
				if(a.getMatricula().equals(matriculaAluno)) {
					return alunos.remove(a) && a.desmatricular();
				}
			}
		} 
		return false;
	}
	
	public void exibirInformacoes() {
		System.out.println(" ------------ SERIE ------------");
		System.out.println("identificador = " + identificador);
		System.out.println("nomeProfessor = " + nomeProfessor);
		System.out.println("anoCriacao = " + anoCriacao);
		for (Aluno aluno : alunos) {
			System.out.println("aluno = " + aluno.getMatricula());
		}
		System.out.println(" -------------------------------");
	}
}

package br.ufba.mata55.lista2;

public class Lista2Main {

	public Lista2Main() { 
		Serie s1 = new Serie(1, "Mauricio", 2016);
		Serie s2 = new Serie(2, "Claudio", 2016);
		
		Aluno a = new Aluno("Aluno a", "92487623897", "Pai1", "Mae1", s1);
		Aluno b = new Aluno("Aluno b", "95656970053", "Pai2", "Mae2", s1);
		
		Aluno c = new Aluno("Aluno c", "21213346888", "Pai1", "Mae1", s2);
		Aluno d = new Aluno("Aluno d", "93865869955", "Pai2", "Mae2", s2);
		
		c.exibirInformacoes();
		
		System.out.println("exibindo serie 1");
		s1.exibirInformacoes();
		System.out.println("removendo alunos");
		s1.desmatricularAluno(a);
		s1.desmatricularAluno("95656970053");
		s1.exibirInformacoes();
		
		System.out.println("exibindo serie 2");
		s2.exibirInformacoes();
		System.out.println("removendo alunos");
		c.desmatricular();
		s2.exibirInformacoes();
		
		a.serieMatriculada().exibirInformacoes();
		
		new Palindromo("SOCORRAM ME SUBINO ONIBUS EM MARROCOS");
		new Palindromo("NAO EH PALINDROMO");
	}
	
	public static void main(String[] args) {
		new Lista2Main();
	}

}

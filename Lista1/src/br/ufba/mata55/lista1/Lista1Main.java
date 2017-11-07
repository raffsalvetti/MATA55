package br.ufba.mata55.lista1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.ufba.mata55.lista1.questao1.Pessoa;
import br.ufba.mata55.lista1.questao2.Carro;
import br.ufba.mata55.lista1.questao4.Fibonacci;

public class Lista1Main {
	
	

	public static void main(String[] args) {
		//questao 1
		Pessoa p1 = new Pessoa();
		p1.cadastrarPessoa();
		p1.exibirInformacoes();
		System.out.println("IDADE: " + p1.Idade(Calendar.getInstance().getTime()));
		
		//criando pessoa 2
		try {
			Pessoa p2 = new Pessoa("Senor Abravanel", "06735473-3", "009.366.245-12", "Brasileiro", new SimpleDateFormat("dd/MM/yyyy").parse("12/12/1930"));
			p2.exibirInformacoes();
			System.out.println("IDADE: " + p2.Idade(Calendar.getInstance().getTime()));
		} catch (ParseException e) {
			System.out.println("Erro criando a pessoa 2");
		}
		
		//questao2
		Carro c = new Carro();
		c.cadastrarCarro();
		c.exibirDadosCarro();
		
		//questao3 nao tem execucao
		
		//questao4
		new Fibonacci();
		
	}

}

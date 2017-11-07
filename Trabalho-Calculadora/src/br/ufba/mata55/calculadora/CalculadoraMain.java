package br.ufba.mata55.calculadora;

import java.util.ArrayList;
import java.util.List;

import br.ufba.mata55.calculadora.core.operacao.Operacao;
import br.ufba.mata55.calculadora.core.operacao.OperacaoInterface;
import br.ufba.mata55.calculadora.core.operacao.binaria.Divisao;
import br.ufba.mata55.calculadora.core.operacao.binaria.Multiplicacao;
import br.ufba.mata55.calculadora.core.operacao.binaria.Soma;
import br.ufba.mata55.calculadora.core.operacao.binaria.Subtracao;
import br.ufba.mata55.calculadora.core.operacao.unaria.Fatorial;
import br.ufba.mata55.calculadora.core.operacao.unaria.Inverso;
import br.ufba.mata55.calculadora.core.operacao.unaria.Modulo;

public class CalculadoraMain {

	private double total = 0;
	
	public CalculadoraMain() {
		
		List<Operacao> operacoes = new ArrayList<Operacao>();
		
		operacoes.add(new Modulo(-10));
		operacoes.add(new Fatorial(25));
		operacoes.add(new Multiplicacao(4, 2));
		operacoes.add(new Soma(2, 2));
		operacoes.add(new Inverso(new Subtracao(4, 2)));
		operacoes.add(new Multiplicacao(new Soma(9, 3), new Subtracao(5, 7), new Soma(3, 4), new Divisao(4, 2)));
		
		for (Operacao<Number> operacao : operacoes) {
			((OperacaoInterface)operacao).operar();
			total += operacao.getResultado().doubleValue();
			System.out.println(operacao.toString());
		}
		System.out.println("Total: " + total);
	}

	public static void main(String[] args) {
		new CalculadoraMain();
	}

}

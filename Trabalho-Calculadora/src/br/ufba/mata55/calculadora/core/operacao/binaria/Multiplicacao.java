package br.ufba.mata55.calculadora.core.operacao.binaria;

import br.ufba.mata55.calculadora.core.operacao.Operacao;
import br.ufba.mata55.calculadora.core.operacao.OperacaoInterface;

public class Multiplicacao extends OperacaoBinaria<Double, Double, Double> implements OperacaoInterface {

	public Multiplicacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Multiplicacao(double a, double b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public Multiplicacao(Operacao... operacoes) {
		super(operacoes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void operar() {
		setResultado(getA() * getB());
	}
	
	@Override
	public String toString() {
		return "Multiplicação: " + getA() + " * " + getB() + " = " + getResultado();
	}
	
}

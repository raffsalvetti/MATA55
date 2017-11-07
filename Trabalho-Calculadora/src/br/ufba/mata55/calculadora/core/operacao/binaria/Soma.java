package br.ufba.mata55.calculadora.core.operacao.binaria;

import br.ufba.mata55.calculadora.core.operacao.Operacao;
import br.ufba.mata55.calculadora.core.operacao.OperacaoInterface;

public class Soma extends OperacaoBinaria<Double, Double, Double> implements OperacaoInterface {

	public Soma() { }
	
	public Soma(double a, double b) {
		super(a, b);
	}

	public Soma(Operacao... operacoes) {
		super(operacoes);
	}

	@Override
	public void operar() {
		setResultado(getA() + getB());
	}

	@Override
	public String toString() {
		return "Soma: " + getA() + " + " + getB() + " = " + getResultado();
	}
}

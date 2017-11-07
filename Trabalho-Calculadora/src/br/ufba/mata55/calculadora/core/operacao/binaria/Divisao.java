package br.ufba.mata55.calculadora.core.operacao.binaria;

import br.ufba.mata55.calculadora.core.operacao.Operacao;
import br.ufba.mata55.calculadora.core.operacao.OperacaoInterface;

public class Divisao extends OperacaoBinaria<Double, Double, Double> implements OperacaoInterface {

	public Divisao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Divisao(double a, double b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public Divisao(Operacao... operacoes) {
		super(operacoes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void operar() {
		if(getB() == 0) {
			setResultado(0D);
		} else {
			setResultado(getA() / getB());
		}
	}
	
	@Override
	public String toString() {
		return "Divisão: " + getA() + " / " + getB() + " = " + getResultado();
	}
}

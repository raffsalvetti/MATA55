package br.ufba.mata55.calculadora.core.operacao.unaria;

import br.ufba.mata55.calculadora.core.operacao.Operacao;
import br.ufba.mata55.calculadora.core.operacao.OperacaoInterface;

public class Inverso extends OperacaoUnaria<Double, Double> implements OperacaoInterface {

	public Inverso(Double a) {
		super(a);
	}
	
	public Inverso(Operacao<Double> operacao) {
		super(operacao);
	}

	@Override
	public void operar() {
		setResultado(1 / getA());
	}
	
	@Override
	public String toString() {
		return "Inverso: 1 / " + getA() + " = " + getResultado();
	}

}

package br.ufba.mata55.calculadora.core.operacao.unaria;

import br.ufba.mata55.calculadora.core.operacao.Operacao;
import br.ufba.mata55.calculadora.core.operacao.OperacaoInterface;

public class Modulo extends OperacaoUnaria<Double, Double> implements OperacaoInterface {

	public Modulo(double a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public Modulo(Operacao operacao) {
		super(operacao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void operar() {
		if(getA() < 0){
			setResultado(-1 * getA());
		} else {
			setResultado(getA());
		}
	}
	
	@Override
	public String toString() {
		return "Módulo: |" + getA() + "| = " + getResultado();
	}
}

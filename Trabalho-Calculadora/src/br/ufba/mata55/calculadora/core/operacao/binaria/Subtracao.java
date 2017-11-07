package br.ufba.mata55.calculadora.core.operacao.binaria;

import br.ufba.mata55.calculadora.core.operacao.Operacao;
import br.ufba.mata55.calculadora.core.operacao.OperacaoInterface;

public class Subtracao extends OperacaoBinaria<Double, Double, Double> implements OperacaoInterface {

	public Subtracao() {}
	
	public Subtracao(double a, double b) {
		super(a, b);
	}
	
	public Subtracao(Operacao... operacao) {
		super(operacao);
	}
	
	@Override
	public void operar() {
		setResultado(getA() - getB());
	}
	
	@Override
	public String toString() {
		return "Subtraçõa: " + getA() + " - " + getB() + " = " + getResultado();
	}
}

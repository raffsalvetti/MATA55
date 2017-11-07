package br.ufba.mata55.calculadora.core.operacao.unaria;

import br.ufba.mata55.calculadora.core.operacao.Operacao;
import br.ufba.mata55.calculadora.core.operacao.OperacaoInterface;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class OperacaoUnaria<A, R> extends Operacao<R> {

	private A a;
	
	public OperacaoUnaria(A a) {
		this.a = a;
	}
	
	public OperacaoUnaria(Operacao operacao) {
		((OperacaoInterface)operacao).operar();
		setA((A)operacao.getResultado());
		((OperacaoInterface)this).operar();
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
}

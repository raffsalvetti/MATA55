package br.ufba.mata55.calculadora.core.operacao.binaria;

import br.ufba.mata55.calculadora.core.operacao.Operacao;
import br.ufba.mata55.calculadora.core.operacao.OperacaoInterface;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class OperacaoBinaria<A, B, R> extends Operacao<R> {
	
	private A a;
	private B b;
	
	public OperacaoBinaria() {}
	
	public OperacaoBinaria(A a, B b) { 
		this.a = a;
		this.b = b;
	}
	
	public OperacaoBinaria(Operacao... operacoes) {
		for (int i = 0 ; i < operacoes.length ; i++) {
			((OperacaoInterface)operacoes[i]).operar();
			if(i == 0) {
				setResultado((R)operacoes[i].getResultado());
			} else {
				setA((A)getResultado());
				setB((B)operacoes[i].getResultado());
				((OperacaoInterface)this).operar();
			}
		}
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
}

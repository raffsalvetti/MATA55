package br.ufba.mata55.calculadora.core.operacao.unaria;

import br.ufba.mata55.calculadora.core.operacao.Operacao;
import br.ufba.mata55.calculadora.core.operacao.OperacaoInterface;

public class Fatorial extends OperacaoUnaria<Long, Long> implements OperacaoInterface {

	private StringBuffer buffer = new StringBuffer();
	
	public Fatorial(long a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public Fatorial(Operacao operacao) {
		super(operacao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void operar() {
		for (int i = getA().intValue(); i > 0 ; i--) {
			if(i == getA()) {
				setResultado((long)i);
				buffer.append(i);
			} else {
				setResultado(i * getResultado());
				buffer.append(" * " + i);
			}
		}
	}
	
	@Override
	public String toString() {
		return "Fatorial: " + buffer.append(" = " + getResultado()).toString();
	}
}

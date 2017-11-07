package br.ufba.mata55.calculadora.core.operacao;

public abstract class Operacao<R> {

	private R resultado;

	public Operacao() { }
	
	public R getResultado() {
		return resultado;
	}

	public void setResultado(R resultado) {
		this.resultado = resultado;
	}
}

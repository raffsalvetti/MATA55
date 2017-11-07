package br.ufba.mata55.lista6.q2;

public class ExcecaoElementoInexistente extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5000587119141107350L;

	public ExcecaoElementoInexistente(){
		super("A conta não existe!");
	}
}

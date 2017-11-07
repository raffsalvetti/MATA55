package br.ufba.mata55.lista6.q2;

public class ExcecaoElementoJaExistente extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4817247529441768189L;

	public ExcecaoElementoJaExistente(){
		super("Elemento já existe!");
	}
}

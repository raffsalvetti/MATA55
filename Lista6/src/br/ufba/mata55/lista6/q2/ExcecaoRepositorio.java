package br.ufba.mata55.lista6.q2;

public class ExcecaoRepositorio extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8707177127645252143L;
	public ExcecaoRepositorio(){
		super("Banco cheio!");
	}
}

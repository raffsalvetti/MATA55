package br.ufba.mata55.questao1;

public class CD implements OperacoesMidia {
	private boolean gravado;

	public boolean vazio() {
		return !gravado;
	}
	
	@Override
	public float setTamanho() {
		return 0.9f;
	}

	@Override
	public String getTipo() {
		return "CD";
	}

	@Override
	public boolean cheio() {
		return gravado;
	}
	
	
}

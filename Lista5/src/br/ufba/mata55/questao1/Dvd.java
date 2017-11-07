package br.ufba.mata55.questao1;

public class Dvd implements OperacoesMidia {

	private int codigo;
	private float espacoConsumidoReal;
	
	public boolean vazio() {
		return (espacoConsumidoReal == 0f);
	}
	
	@Override
	public float setTamanho() {
		return 4.7f;
	}

	@Override
	public String getTipo() {
		return "DVD";
	}

	@Override
	public boolean cheio() {
		return (espacoConsumidoReal == 4.7f);
	}
}

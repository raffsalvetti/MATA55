package br.ufba.mata55.questao2;

public class Alimento extends Produto implements Tributavel {

	@Override
	public double calcularcms() {
		return preco * .12D;
	}

}

package br.ufba.mata55.questao2;

public class Cosmetico extends Produto implements Tributavel {

	@Override
	public double calcularcms() {
		return preco * .25D;
	}
	
}

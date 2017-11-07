package br.ufba.mata55.questao2;

import java.util.ArrayList;
import java.util.List;

public class CadastroProdutos {

	private List<Tributavel> produtos = new ArrayList<Tributavel>();
	private Alimento a1, a2;
	private Cosmetico c1, c2;
	
	public CadastroProdutos() {
		a1 = new Alimento();
		a1.setPreco(18.50D);
		a2 = new Alimento();
		a2.setPreco(9.80D);
		c1 = new Cosmetico();
		c1.setPreco(31.99D);
		c2 = new Cosmetico();
		c2.setPreco(55.90D);
		
		produtos.add(a1);
		produtos.add(a2);
		produtos.add(c1);
		produtos.add(c2);
		
		for (Tributavel tributavel : produtos) {
			System.out.println("Produto do tipo " + tributavel.getClass().getSimpleName());
			System.out.println("Valores: " + tributavel.toString());
			System.out.println("ICMS: " + tributavel.calcularcms());
		}
	}
	
	public static void main(String[] args) {
		new CadastroProdutos();
	}

}

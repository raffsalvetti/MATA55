package br.ufba.mata55.questao2;

public abstract class Produto {
	protected int codigo;
	protected String descricao;
	protected double preco;

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao
				+ ", preco=" + preco + "]";
	}
}

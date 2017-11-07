package br.ufba.mata55.questao1;

public final class ContaEspecial extends ContaBancaria {

	private double limite;
	
	public ContaEspecial(int numero, String cliente, double limite) {
		super(numero, cliente);
		this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	@Override
	public boolean sacar(double valor) {
		if(getSaldo() + limite - valor >= 0) {
			setSaldo(getSaldo() - valor);
			return true;
		} else {
			return false;
		}
	}
	
	public void exibirInformacoes() {
		super.exibirInformacoes();
		System.out.println("Limite: " + limite);
	}
}

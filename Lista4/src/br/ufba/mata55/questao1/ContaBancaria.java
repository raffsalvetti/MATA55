package br.ufba.mata55.questao1;

public abstract class ContaBancaria {

	private String cliente;
	private int numero;
	private double saldo;
	
	public ContaBancaria(int numero, String cliente) {
		super();
		this.numero = numero;
		this.cliente = cliente;

	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean depositar(double valor) {
		saldo += valor;
		return true;
	}
	
	public boolean sacar(double valor) {
		if(saldo - valor >= 0) {
			saldo -= valor;
			return true;
		} else {
			return false;
		}
	}
	
	public void exibirInformacoes() {
		System.out.println("Cliente: " + cliente);
		System.out.println("Numero: " + numero);
		System.out.println("Saldo: " + saldo);
	}
	
	@Override
	public String toString() {
		return getCliente() + " (Numero = " + getNumero() + ")";
	}
}

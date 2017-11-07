package br.ufba.mata55.questao1;

public final class ContaPoupanca extends ContaBancaria {

	private int diasRelacionamento;
	
	public ContaPoupanca(int numero, String cliente) {
		super(numero, cliente);
	}

	public void calculaNovoSaldo(double taxaRendimentoPoupanca) {
		setSaldo(getSaldo() * (1 + taxaRendimentoPoupanca / 100));
		System.out.println("Saldo corrigido: " + getSaldo());
	}

	public int getDiasRelacionamento() {
		return diasRelacionamento;
	}

	public void setDiasRelacionamento(int diasRelacionamento) {
		this.diasRelacionamento = diasRelacionamento;
	}
	
	public void exibirInformacoes() {
		super.exibirInformacoes();
		System.out.println("Dias de Relacionamento: " + diasRelacionamento);
	}
}

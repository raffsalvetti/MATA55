package br.ufba.mata55.banco.data.po;

/**
 * Enumerdor customizado de tipos de movimentação
 * @author raffaello.salvetti
 *
 */
public enum TipoMovimentacao {
	CREDITO("Crédito", .005f),
	DEBITO("Débito", .002f);
	
	private String label;
	private double custo;
	private double lucroBanco;
	
	/**
	 * Construtor privado do enumerador 
	 * @param label Uma etiqueta para o tipo
	 * @param custo O custo da movimentação (Taxa cobrada pelo banco)
	 */
	private TipoMovimentacao(String label, double custo) {
		this.label = label;
		this.custo = custo;
	}
	
	/**
	 * Retorna a etiqueta
	 * @return Etiqueta
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Retorna o custo da movimentação baseada no valor 
	 * @param valor Valor
	 * @return O custo da operação
	 */
	public double getCusto(double valor) {
		return custo * valor;
	}
	
	/**
	 * Soma o lucro do bando a cada movimentação
	 * @param value Valor do lucro
	 */
	public void adicionarLucroBanco(double value) {
		lucroBanco += value;
	}
	
	/**
	 * Retorna o lucro da operação 
	 * @return Lucro do banco
	 */
	public double getLucroBanco(){
		return lucroBanco;
	}

	@Override
	public String toString() {
		return label;
	}
}

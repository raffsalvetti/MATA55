package br.ufba.mata55.banco.data.bean;

/**
 * 
 * @author raffaello.salvetti
 * Bean usado para renderizar relatórios ou gráficos 
 */
public final class RelatorioMovimentacaoBean {
	private String CONTA, DATA, DESCRICAO, DESCRICAO_ADICIONAL;
	private int COD_TIPO_MOVIMENTACAO;
	private double VALOR;
	public String getCONTA() {
		return CONTA;
	}
	public void setCONTA(String cONTA) {
		CONTA = cONTA;
	}
	public String getDATA() {
		return DATA;
	}
	public void setDATA(String dATA) {
		DATA = dATA;
	}
	public String getDESCRICAO() {
		return DESCRICAO;
	}
	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}
	public String getDESCRICAO_ADICIONAL() {
		return DESCRICAO_ADICIONAL;
	}
	public void setDESCRICAO_ADICIONAL(String dESCRICAO_ADICIONAL) {
		DESCRICAO_ADICIONAL = dESCRICAO_ADICIONAL;
	}
	public int getCOD_TIPO_MOVIMENTACAO() {
		return COD_TIPO_MOVIMENTACAO;
	}
	public void setCOD_TIPO_MOVIMENTACAO(int cOD_TIPO_MOVIMENTACAO) {
		COD_TIPO_MOVIMENTACAO = cOD_TIPO_MOVIMENTACAO;
	}
	public double getVALOR() {
		return VALOR;
	}
	public void setVALOR(double vALOR) {
		VALOR = vALOR;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + COD_TIPO_MOVIMENTACAO;
		result = prime * result + ((CONTA == null) ? 0 : CONTA.hashCode());
		result = prime * result + ((DATA == null) ? 0 : DATA.hashCode());
		result = prime * result + ((DESCRICAO == null) ? 0 : DESCRICAO.hashCode());
		result = prime * result + ((DESCRICAO_ADICIONAL == null) ? 0 : DESCRICAO_ADICIONAL.hashCode());
		long temp;
		temp = Double.doubleToLongBits(VALOR);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatorioMovimentacaoBean other = (RelatorioMovimentacaoBean) obj;
		if (COD_TIPO_MOVIMENTACAO != other.COD_TIPO_MOVIMENTACAO)
			return false;
		if (CONTA == null) {
			if (other.CONTA != null)
				return false;
		} else if (!CONTA.equals(other.CONTA))
			return false;
		if (DATA == null) {
			if (other.DATA != null)
				return false;
		} else if (!DATA.equals(other.DATA))
			return false;
		if (DESCRICAO == null) {
			if (other.DESCRICAO != null)
				return false;
		} else if (!DESCRICAO.equals(other.DESCRICAO))
			return false;
		if (DESCRICAO_ADICIONAL == null) {
			if (other.DESCRICAO_ADICIONAL != null)
				return false;
		} else if (!DESCRICAO_ADICIONAL.equals(other.DESCRICAO_ADICIONAL))
			return false;
		if (Double.doubleToLongBits(VALOR) != Double.doubleToLongBits(other.VALOR))
			return false;
		return true;
	}
	
}

package br.ufba.mata55.banco.data.bean;

import java.math.BigInteger;

/**
 * 
 * @author raffaello.salvetti
 * Bean usado para renderizar relatórios ou gráficos 
 */
public final class RelatorioContaBean {
	private String NUMERO, ESPECIAL, DATA_CRIACAO, STATUS;
	private int MOVIMENTACOES;
	private double LIMITE, SALDO;
	public String getNUMERO() {
		return NUMERO;
	}
	public void setNUMERO(String nUMERO) {
		NUMERO = nUMERO;
	}
	public String getESPECIAL() {
		return ESPECIAL;
	}
	public void setESPECIAL(String eSPECIAL) {
		ESPECIAL = eSPECIAL;
	}
	public String getDATA_CRIACAO() {
		return DATA_CRIACAO;
	}
	public void setDATA_CRIACAO(String dATA_CRIACAO) {
		DATA_CRIACAO = dATA_CRIACAO;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public int getMOVIMENTACOES() {
		return MOVIMENTACOES;
	}
	public void setMOVIMENTACOES(BigInteger mOVIMENTACOES) {
		MOVIMENTACOES = mOVIMENTACOES.intValue();
	}
	public double getLIMITE() {
		return LIMITE;
	}
	public void setLIMITE(double lIMITE) {
		LIMITE = lIMITE;
	}
	public double getSALDO() {
		return SALDO;
	}
	public void setSALDO(double sALDO) {
		SALDO = sALDO;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_CRIACAO == null) ? 0 : DATA_CRIACAO.hashCode());
		result = prime * result + ((ESPECIAL == null) ? 0 : ESPECIAL.hashCode());
		long temp;
		temp = Double.doubleToLongBits(LIMITE);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + MOVIMENTACOES;
		result = prime * result + ((NUMERO == null) ? 0 : NUMERO.hashCode());
		temp = Double.doubleToLongBits(SALDO);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((STATUS == null) ? 0 : STATUS.hashCode());
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
		RelatorioContaBean other = (RelatorioContaBean) obj;
		if (DATA_CRIACAO == null) {
			if (other.DATA_CRIACAO != null)
				return false;
		} else if (!DATA_CRIACAO.equals(other.DATA_CRIACAO))
			return false;
		if (ESPECIAL == null) {
			if (other.ESPECIAL != null)
				return false;
		} else if (!ESPECIAL.equals(other.ESPECIAL))
			return false;
		if (Double.doubleToLongBits(LIMITE) != Double.doubleToLongBits(other.LIMITE))
			return false;
		if (MOVIMENTACOES != other.MOVIMENTACOES)
			return false;
		if (NUMERO == null) {
			if (other.NUMERO != null)
				return false;
		} else if (!NUMERO.equals(other.NUMERO))
			return false;
		if (Double.doubleToLongBits(SALDO) != Double.doubleToLongBits(other.SALDO))
			return false;
		if (STATUS == null) {
			if (other.STATUS != null)
				return false;
		} else if (!STATUS.equals(other.STATUS))
			return false;
		return true;
	}
	
}

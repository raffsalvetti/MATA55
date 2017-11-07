package br.ufba.mata55.banco.data.bean;

import java.math.BigInteger;

/**
 * 
 * @author raffaello.salvetti
 * Bean usado para renderizar relatórios ou gráficos 
 */
public final class RelatorioOperacaoBean {
	private int COD_TIPO_MOVIMENTACAO, QUANTIDADE;

	public int getCOD_TIPO_MOVIMENTACAO() {
		return COD_TIPO_MOVIMENTACAO;
	}

	public void setCOD_TIPO_MOVIMENTACAO(int cOD_TIPO_MOVIMENTACAO) {
		COD_TIPO_MOVIMENTACAO = cOD_TIPO_MOVIMENTACAO;
	}

	public int getQUANTIDADE() {
		return QUANTIDADE;
	}

	public void setQUANTIDADE(BigInteger qUANTIDADE) {
		QUANTIDADE = qUANTIDADE.intValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + COD_TIPO_MOVIMENTACAO;
		result = prime * result + QUANTIDADE;
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
		RelatorioOperacaoBean other = (RelatorioOperacaoBean) obj;
		if (COD_TIPO_MOVIMENTACAO != other.COD_TIPO_MOVIMENTACAO)
			return false;
		if (QUANTIDADE != other.QUANTIDADE)
			return false;
		return true;
	}
	
}

package br.ufba.mata55.banco.data.bean;

/**
 * 
 * @author raffaello.salvetti
 * Bean usado para renderizar relatórios ou gráficos 
 */
public final class RelatorioLucroBean {
	private String NUMERO, TIPO;
	private double VALOR;
	
	public String getNUMERO() {
		return NUMERO;
	}

	public void setNUMERO(String nUMERO) {
		NUMERO = nUMERO;
	}

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
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
		result = prime * result + ((NUMERO == null) ? 0 : NUMERO.hashCode());
		result = prime * result + ((TIPO == null) ? 0 : TIPO.hashCode());
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
		RelatorioLucroBean other = (RelatorioLucroBean) obj;
		if (NUMERO == null) {
			if (other.NUMERO != null)
				return false;
		} else if (!NUMERO.equals(other.NUMERO))
			return false;
		if (TIPO == null) {
			if (other.TIPO != null)
				return false;
		} else if (!TIPO.equals(other.TIPO))
			return false;
		if (Double.doubleToLongBits(VALOR) != Double.doubleToLongBits(other.VALOR))
			return false;
		return true;
	}
	
}

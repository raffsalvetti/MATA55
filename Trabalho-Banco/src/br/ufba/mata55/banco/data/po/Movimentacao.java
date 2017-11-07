package br.ufba.mata55.banco.data.po;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufba.mata55.banco.annotation.Campo;
import br.ufba.mata55.banco.annotation.Tabela;

/**
 * Classe que define uma Movimentação
 * @author raffaello.salvetti
 *
 */
@Tabela(nome = "MOVIMENTACAO")
public class Movimentacao extends AbstractPO {
	private int codigo;
	private int codConta;
	private String descricao;
	private double valor;
	private TipoMovimentacao tipoMovimentacao;
	private Date data;
	
	public Movimentacao() {}
	
	/**
	 * Construtor que cria um objeto com os valores iniciais
	 * @param descricao Descrição da movimentação (Opcional)
	 * @param valor Valor da movimentação;
	 * @param tipoMovimentacao Tipo da movimentação
	 */
	public Movimentacao(String descricao, double valor, TipoMovimentacao tipoMovimentacao, Date data) {
		this.descricao = descricao;
		this.valor = valor;
		this.tipoMovimentacao = tipoMovimentacao;
		this.data = data;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	@Campo(nome = "CODIGO", chavePrimaria = true)
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	
	public int getCodConta(){
		return codConta;
	}
	
	@Campo(nome = "COD_CONTA")
	public void setCodConta(int codConta){
		this.codConta = codConta;
	}
	
	/**
	 * Retorna a descrição da movimentação
	 * @return Descrição que identifica a movimentação
	 */
	public String getDescricao() {
		return descricao;
	}
	
	@Campo(nome = "DESCRICAO")
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Retorna valor da movimentação
	 * @return Valor da movimentação
	 */
	public double getValor() {
		return valor;
	}
	
	@Campo(nome = "VALOR")
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	/**
	 * Retorna tipo da movimentação
	 * @return Tipo da movimentação
	 */
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	
	@Campo(nome = "TIPO_MOVIMENTACAO")
	public void setTipoMovimentacao(int codTipoMovimentacao) {
		this.tipoMovimentacao = TipoMovimentacao.values()[codTipoMovimentacao];
	}
	
	public void setTipoMovimentacao(String nomeTipoMovimentacao) {
		this.tipoMovimentacao = TipoMovimentacao.valueOf(nomeTipoMovimentacao);
	}
	
	/**
	 * Retorna data da operação
	 * @return Data da operação
	 */
	public Date getData(){
		return data;
	}
	
	@Campo(nome = "DATA")
	public void setData(Timestamp data){
		this.data = data;
	}
	
	public void setData(String data){
		try {
			this.data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codConta;
		result = prime * result + codigo;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime
				* result
				+ ((tipoMovimentacao == null) ? 0 : tipoMovimentacao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
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
		Movimentacao other = (Movimentacao) obj;
		if (codConta != other.codConta)
			return false;
		if (codigo != other.codigo)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tipoMovimentacao != other.tipoMovimentacao)
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movimentacao [codigo=" + codigo + ", codConta=" + codConta
				+ ", descricao=" + descricao + ", valor=" + valor
				+ ", tipoMovimentacao=" + tipoMovimentacao + ", data=" + data
				+ "]";
	}
	
}

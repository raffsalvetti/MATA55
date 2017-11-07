package br.ufba.mata55.banco.data.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Classe que define uma Movimentação
 * @author raffaello.salvetti
 *
 */
@Entity
@Table(name = "MOVIMENTACAO")
public class Movimentacao extends AbstractPO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOVIMENTACAO")
	@SequenceGenerator(name = "SEQ_MOVIMENTACAO", sequenceName="SEQ_MOVIMENTACAO", allocationSize=1)
	@Column(name = "CODIGO")
	private int codigo;

	@Column(name = "DESCRICAO", nullable = true)
	private String descricao;
	
	@Column(name = "DESCRICAO_ADICIONAL", nullable = true)
	private String descricaoAdicional;
	
	@Column(name = "VALOR", nullable = false)
	private double valor;
	
	@Column(name = "COD_TIPO_MOVIMENTACAO", nullable = false, columnDefinition="INTEGER")
	private TipoMovimentacao tipoMovimentacao;
	
	@Column(name = "DATA", nullable = false, insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="COD_CONTA", nullable = false)
	private Conta conta;	
	
	@Column(name = "IS_TAXA", nullable = false)
	private boolean isTaxa;
	
	public Movimentacao() {}
	
	/**
	 * Construtor que cria um objeto com os valores iniciais
	 * @param descricao Descrição da movimentação (Opcional)
	 * @param valor Valor da movimentação;
	 * @param tipoMovimentacao Tipo da movimentação
	 */
	public Movimentacao(Conta conta, String descricao, String descricaoAdicional, double valor, TipoMovimentacao tipoMovimentacao, boolean isTaxa) {
		this.conta = conta;
		this.descricao = descricao;
		this.descricaoAdicional = descricaoAdicional;
		this.valor = valor;
		this.tipoMovimentacao = tipoMovimentacao;
		this.isTaxa = isTaxa;
	}
	
	/**
	 * Retorna o código da movimentação
	 * @return Código da movimentação
	 */
	public int getCodigo(){
		return codigo;
	}
	
	/**
	 * Define o código da movimentação
	 * @param codigo Código da movimentação
	 */
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	
	/**
	 * Retorna a descrição da movimentação
	 * @return Descrição que identifica a movimentação
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Define a descricao de uma movimentação
	 * @param descricao Descricao de uma movimentação
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Retorna a descricao adicional de uma movimentação
	 * @return Descricao adicional de uma movimentação
	 */
	public String getDescricaoAdicional() {
		return descricaoAdicional;
	}

	/**
	 * Define a descricao adicional de uma movimentação
	 */
	public void setDescricaoAdicional(String descricaoAdicional) {
		this.descricaoAdicional = descricaoAdicional;
	}

	/**
	 * Retorna valor da movimentação
	 * @return Valor da movimentação
	 */
	public double getValor() {
		return valor;
	}
	
	/**
	 * Define valor da movimentação
	 */
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
	
	/**
	 * Define o tipo da movimentação pelo código
	 * @param codTipoMovimentacao Código do tipo de movimentação
	 */
	public void setTipoMovimentacao(int codTipoMovimentacao) {
		this.tipoMovimentacao = TipoMovimentacao.values()[codTipoMovimentacao];
	}
	
	/**
	 * Define o tipo da movimentação pelo nome do tipo de movimentação
	 * @param nomeTipoMovimentacao Nome do tipo de movimentação
	 */
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
	
	/**
	 * Define a data de uma movimentação
	 * @param data Data de uma movimentação
	 */
	public void setData(Date data){
		this.data = data;
	}
	
	/**
	 * Retoirna a conta da movimentação
	 * @return Conta da movimentação
	 */
	public Conta getConta() {
		return conta;
	}

	/**
	 * Define a conta da movimentação
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	/**
	 * Retorna indicador de taxa
	 * @return Indicador de taxa
	 */
	public boolean isIsTaxa() {
		return isTaxa;
	}

	/**
	 * Define indicador de taxa
	 */
	public void setIsTaxa(boolean isTaxa) {
		this.isTaxa = isTaxa;
	}

	@Transient
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
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

	@Transient
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		if (codigo != other.codigo)
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
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

	@Transient
	@Override
	public String toString() {
		return "Movimentacao [codigo=" + codigo + ", descricao=" + descricao
				+ ", valor=" + valor + ", tipoMovimentacao=" + tipoMovimentacao
				+ ", data=" + data + ", conta=" + conta + "]";
	}
	
}

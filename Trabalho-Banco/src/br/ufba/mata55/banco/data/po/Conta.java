package br.ufba.mata55.banco.data.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe que define uma Conta e suas operações
 * @author raffaello.salvetti
 *
 */
@Entity
@Table(name = "CONTA")
public class Conta extends AbstractPO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTA")
	@SequenceGenerator(name = "SEQ_CONTA", sequenceName="SEQ_CONTA", allocationSize=1)
	@Column(name = "CODIGO")
	private int codigo;
	
	@Column(name = "NUMERO", nullable = false)
	private String numero;
	
	@Column(name = "SALDO")
	private double saldo;
	
	@Column(name = "ESPECIAL")
	private boolean especial;
	
	@Column(name = "LIMITE")
	private double limite;
	
	@Column(name = "DATA_CRIACAO", nullable = false, insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date dataCriacao;
	
	@Column(name = "DATA_EXCLUSAO", nullable = true, insertable = false, updatable = true)
	private Date dataExclusao;
	
	@OneToMany(mappedBy="conta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Movimentacao> listMovimentacao = new ArrayList<Movimentacao>();
	
	public Conta(){}
	
	/**
	 * Construtor que cria um objeto com valores iniciais
	 * @param codigo Código da conta
	 * @param numero Número da conta
	 * @param limite Limite para operações de uma conta
	 * @param especial Marcador de conta especial
	 */
	public Conta(int codigo, String numero, double limite, boolean especial) {
		this.codigo = codigo;
		this.numero = numero;
		this.limite = limite;
		this.especial = especial;
	}
	
	/**
	 * Construtor que cria um objeto com valores iniciais 
	 * @param numero Número da conta
	 * @param limite Limite para operações de uma conta
	 * @param especial Marcador de conta especial
	 */
	public Conta(String numero, double limite, boolean especial) {
		this.numero = numero;
		this.limite = limite;
		this.especial = especial;
	}

	/**
	 * Define o código da conta
	 * @param codigo Código da conta
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Retorna o código da conta
	 * @return Código da conta
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Retorna o número da conta
	 * @return Número da conta
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Define o número da conta
	 * @param numero Número da conta
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Retorna o saldo da conta
	 * @return Saldo da conta
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * Define o saldo da conta
	 * @param saldo Saldo da conta
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * Retorna indicador de conta especial
	 * @return boleano que indica conta especial ou não
	 */
	public boolean isEspecial() {
		return especial;
	}

	/**
	 * Define se a conta é especial
	 * @param especial true para conta especial ou false para normal
	 */
	public void setEspecial(boolean especial) {
		this.especial = especial;
	}
	
	/**
	 * Retorna a data de criação da conta
	 * @return Data de criação da conta
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * Define a data de criação da conta
	 * @param dataCriacao Data de criação da conta
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * Retorna a data de exclusão da conta
	 * @return Data de exclusão da conta
	 */
	public Date getDataExclusao() {
		return dataExclusao;
	}

	/**
	 * Define a data de exclusão da conta
	 * @param dataExclusao Data de exclusão da conta
	 */
	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	/**
	 * Retorna o limite da conta
	 * @return Limite da conta
	 */
	public double getLimite() {
		return limite;
	}

	/**
	 * Define o limite da conta
	 * @param limite Limite da conta
	 */
	public void setLimite(double limite) {
		this.limite = limite;
	}

	/**
	 * Retorna a lista de movimentações da conta
	 * @return Lista de movimentações da conta
	 */
	public List<Movimentacao> getListMovimentacao() {
		return listMovimentacao;
	}
	
	/**
	 * Define a lista de movimentações da conta
	 * @param listaMovimentacoes Lista de movimentações da conta
	 */
	public void setListMovimentacao(List<Movimentacao> listaMovimentacoes) {
		this.listMovimentacao = listaMovimentacoes;
	}

	/**
	 * Método usado para realizar uma operação de credito na conta
	 * @param valor Valor do crédito
	 * @param descricao Descrição do crédito que aparece no extrato e relatório (Opcional)
	 * @return indicador de operação efetuada com sucesso; true para sucesso e false para falha 
	 */
	@Transient
	public boolean creditar(double valor, String descricao) {
		return creditar(valor, descricao, null);
	}
	
	/**
	 * Método usado para realizar uma operação de credito na conta
	 * @param valor Valor do crédito
	 * @param descricao Descrição do crédito que aparece no extrato e relatório (Opcional)
	 * @param descricaoAdicional Descrição do crédito que aparece no extrato e relatório (Interno)
	 * @return indicador de operação efetuada com sucesso; true para sucesso e false para falha 
	 */
	@Transient
	public boolean creditar(double valor, String descricao, String descricaoAdicional) {
		saldo += valor;
		listMovimentacao.add(new Movimentacao(this, descricao, descricaoAdicional, valor, TipoMovimentacao.CREDITO, false));
		
		double custo = TipoMovimentacao.CREDITO.getCusto(valor);
		if(especial)
			custo /= 10;
		saldo -= custo;
		listMovimentacao.add(new Movimentacao(this, "Taxa de depósito", descricaoAdicional, custo, TipoMovimentacao.DEBITO, true));
		return true; //sempre posso creditar
	}
	
	/**
	 * Método usado para realizar uma operação de débito na conta
	 * @param valor Valor do débito
	 * @param descricao Descrição do débito que aparece no extrato e relatório (Opcional)
	 * @return indicador de operação efetuada com sucesso; true para sucesso e false para falha 
	 */
	@Transient
	public boolean debitar(double valor, String descricao) {
		return debitar(valor, descricao, null);
	}
	
	/**
	 * Método usado para realizar uma operação de débito na conta
	 * @param valor Valor do débito
	 * @param descricao Descrição do débito que aparece no extrato e relatório (Opcional)
	 * @param descricaoAdicional Descrição do crédito que aparece no extrato e relatório (Interno)
	 * @return indicador de operação efetuada com sucesso; true para sucesso e false para falha 
	 */
	@Transient
	public boolean debitar(double valor, String descricao, String descricaoAdicional) {	
		
		double custo = TipoMovimentacao.DEBITO.getCusto(valor);
		if(especial)
			custo /= 10;
		if(saldo + limite - valor - custo>= 0) {
			saldo -= valor;
			listMovimentacao.add(new Movimentacao(this, descricao, descricaoAdicional, valor, TipoMovimentacao.DEBITO, false));
			
			saldo -= custo;
			listMovimentacao.add(new Movimentacao(this, "Taxa de saque", descricaoAdicional, custo, TipoMovimentacao.DEBITO, true));
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Método usado para transferir valores dessa conta para outra conta
	 * @param valor Valor da transferência
	 * @param conta Conta destino
	 * @return indicador de operação efetuada com sucesso; true para sucesso e false para falha 
	 */
	@Transient
	public boolean transferir(double valor, Conta conta, String descricao) {
		if(debitar(valor, descricao, "Transferência para conta " + conta.getNumero())) {
			conta.creditar(valor, descricao, "Transferência da conta " + getNumero());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Exibe o extrato da conta no console
	 */
	@Transient
	public void extrato() {
		for (Movimentacao movimentacao : listMovimentacao) {
			System.out.println(movimentacao);
		}
	}
	
	/**
	 * Método que retorna a lista de movimentações da conta 
	 * @return TableModel com a lista de movimentações da conta 
	 */
	@Transient
	public TableModel getTableModelMovimentacao() {
		DefaultTableModel defaultTableModel = new DefaultTableModel(
				new Object[][] {},
				new String[] {
								"Tipo", "Descrição", "Valor"
							}
				);
		for (Movimentacao movimentacao : listMovimentacao) {
			defaultTableModel.addRow(
					new Object[] {
								movimentacao.getTipoMovimentacao(), 
								movimentacao.getDescricao(), 
								String.format("R$ %.2f", movimentacao.getValor())
							}
					);
		}
		return defaultTableModel;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + (especial ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(limite);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		temp = Double.doubleToLongBits(saldo);
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
		Conta other = (Conta) obj;
		if (codigo != other.codigo)
			return false;
		if (especial != other.especial)
			return false;
		if (Double.doubleToLongBits(limite) != Double
				.doubleToLongBits(other.limite))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (Double.doubleToLongBits(saldo) != Double
				.doubleToLongBits(other.saldo))
			return false;
		return true;
	}

	/**
	 * Retorna string que identifica a conta
	 */
	@Override
	public String toString() {
		return getNumero() + " (" + (isEspecial()?"Especial":"Normal") + ")";
	}
}

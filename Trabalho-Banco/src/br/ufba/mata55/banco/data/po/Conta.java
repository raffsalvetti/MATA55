package br.ufba.mata55.banco.data.po;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.ufba.mata55.banco.annotation.Campo;
import br.ufba.mata55.banco.annotation.MapeadoPor;
import br.ufba.mata55.banco.annotation.Tabela;
import br.ufba.mata55.banco.data.dao.MovimentacaoDAO;

/**
 * Classe que define uma Conta e suas operações
 * @author raffaello.salvetti
 *
 */
@Tabela(nome = "CONTA")
public class Conta extends AbstractPO {

	private int codigo;
	private String numero;
	private double saldo;
	private boolean especial;
	private double limite;
	private List<Movimentacao> listMovimentacao = new ArrayList<Movimentacao>();
	
	public Conta(){}
	
	/**
	 * Construtor que cria um objeto com valores iniciais
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
	
	public Conta(String numero, double limite, boolean especial) {
		this.numero = numero;
		this.limite = limite;
		this.especial = especial;
	}

	@Campo(nome = "CODIGO", chavePrimaria = true)
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
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
	@Campo(nome = "NUMERO")
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
	@Campo(nome = "SALDO")
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
	@Campo(nome = "ESPECIAL")
	public void setEspecial(boolean especial) {
		this.especial = especial;
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
	@Campo(nome = "LIMITE")
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
	
	@MapeadoPor(DAO = MovimentacaoDAO.class, CampoOrigem = "CODIGO", CampoDestino = "COD_CONTA")
	public void setListMovimentacao(List<Movimentacao> listaMovimentacoes) {
		this.listMovimentacao = listaMovimentacoes;
	}

	/**
	 * Método usado para realizar uma operação de credito na conta
	 * @param valor Valor do crédito
	 * @param descricao Descrição do crédito que aparece no extrato e relatório (Opcional)
	 * @return indicador de operação efetuada com sucesso; true para sucesso e false para falha 
	 */
	public boolean creditar(double valor, String descricao) {
		saldo += valor;
		listMovimentacao.add(new Movimentacao(descricao, valor, TipoMovimentacao.CREDITO, Calendar.getInstance().getTime()));
		
		double custo = TipoMovimentacao.CREDITO.getCusto(valor);
		if(especial)
			custo /= 2;
		TipoMovimentacao.CREDITO.adicionarLucroBanco(custo);
		saldo -= custo;
		listMovimentacao.add(new Movimentacao("Taxa de depósito", custo, TipoMovimentacao.DEBITO, Calendar.getInstance().getTime()));

		return true; //sempre posso creditar
	}
	
	/**
	 * Método usado para realizar uma operação de débito na conta
	 * @param valor Valor do débito
	 * @param descricao Descrição do débito que aparece no extrato e relatório (Opcional)
	 * @return indicador de operação efetuada com sucesso; true para sucesso e false para falha 
	 */
	public boolean debitar(double valor, String descricao) {
		double custo = TipoMovimentacao.DEBITO.getCusto(valor);
		if(especial)
			custo /= 2;
		if(saldo + limite - valor - custo>= 0) {
			saldo -= valor;
			listMovimentacao.add(new Movimentacao(descricao, valor, TipoMovimentacao.DEBITO, Calendar.getInstance().getTime()));
			
			saldo -= custo;
			TipoMovimentacao.DEBITO.adicionarLucroBanco(TipoMovimentacao.DEBITO.getCusto(valor));
			listMovimentacao.add(new Movimentacao("Taxa de saque", custo, TipoMovimentacao.DEBITO, Calendar.getInstance().getTime()));
			
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
	public boolean transferir(double valor, Conta conta, String descricao) {
		if(debitar(valor, "Transferência para conta " + conta.getNumero() + (!descricao.isEmpty()?" (" + descricao + ")":"") )) {
			conta.creditar(valor, "Transferência da conta " + getNumero()  + (!descricao.isEmpty()?" (" + descricao + ")":""));
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Exibe o extrato da conta no console
	 */
	public void extrato() {
		for (Movimentacao movimentacao : listMovimentacao) {
			System.out.println(movimentacao);
		}
	}
	
	/**
	 * Método que retorna a lista de movimentações da conta 
	 * @return TableModel com a lista de movimentações da conta 
	 */
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
	
	/**
	 * Retorna string que identifica a conta
	 */
	@Override
	public String toString() {
		return getNumero() + " (" + (isEspecial()?"Especial":"Normal") + ")";
	}
}

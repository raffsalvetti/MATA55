package br.ufba.mata55.banco.gui;

import br.ufba.mata55.banco.data.po.Conta;

/**
 * Interface de eventos para formulário que trata contas
 * @author raffaello.salvetti
 *
 */
public interface ContaListener {
	/**
	 * Evento disparado quando uma nova conta é criada
	 * @param conta
	 */
	void novaConta(Conta conta);
	
	/**
	 * Evento disparado quando uma conta é modificada
	 * @param conta
	 */
	void modificarConta(Conta conta);
	
	/**
	 * Evento disparado quando uma conta é removida
	 * @param conta
	 */
	void removerConta(Conta conta);
	
	/**
	 * Evento disparado quando uma conta é encontrada
	 * @param conta
	 */
	void buscarConta(Conta conta);
}

package br.ufba.mata55.banco.gui;

import br.ufba.mata55.banco.data.po.Conta;

/**
 * Interface de eventos para formulário de transferência
 * @author raffaello.salvetti
 *
 */
public interface SelecaoContaListener {
	
	/**
	 * Evento de transferência
	 * @param conta Conta destino
	 * @param valor Valor da transferência
	 */
	void transferir(Conta conta, double valor, String descricao);
}

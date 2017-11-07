package br.ufba.mata55.banco.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufba.mata55.banco.data.po.Conta;

/**
 * Formulário para selecionar uma conta destino para transferência
 * @author raffaello.salvetti
 *
 */
public class SelecaoContaForm extends JDialog {

	private static final long serialVersionUID = -6038865869180599534L;
	private JPanel panelButtons, panelControles;
	private JLabel labelConta;
	private JComboBox<Conta> comboBoxConta;
	private JButton buttonTransferir, buttonFechar;
	private double valor;
	private String descricao;
	private List<SelecaoContaListener> selecaoContaListeners = Collections.synchronizedList(new ArrayList<SelecaoContaListener>());
	
	/**
	 * Construtor que recebe uma instância do formulário pai
	 * @param parent Instânica do formulário pai
	 */
	public SelecaoContaForm(Component parent) {
		setTitle("Seleção de Conta");
		setModal(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(300, 120);
		setLocationRelativeTo(parent);
		
		panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		buttonTransferir = new JButton("Transferir");
		buttonTransferir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBoxConta.getSelectedItem() instanceof Conta) {
					for (SelecaoContaListener selecaoContaListener : selecaoContaListeners) {
						selecaoContaListener.transferir((Conta)comboBoxConta.getSelectedItem(), valor, descricao);
					}
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(SelecaoContaForm.this, "Selecione uma conta", "Ops...", JOptionPane.OK_OPTION);
				}
			}
		});
		panelButtons.add(buttonTransferir);
		
		buttonFechar = new JButton("Cancelar");
		buttonFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panelButtons.add(buttonFechar);
		
		panelControles = new JPanel();
		getContentPane().add(panelControles, BorderLayout.NORTH);
		
		labelConta = new JLabel("Conta:");
		panelControles.add(labelConta);
		
		comboBoxConta = new JComboBox<Conta>();
		panelControles.add(comboBoxConta);
	}
	
	/**
	 * Recebe o valor de uma transfêrencia
	 * @param valor Valor da transferência
	 */
	public void setValoresTransferencia(double valor, String descricao){
		this.valor = valor;
		this.descricao = descricao;
	}
	
	/**
	 * Recebe uma lista de contas disponíveis para uma transferência
	 * @param contas Lista de contas disponíveis para uma transferência
	 */
	public void setListaContas(List<Conta> contas) {
		comboBoxConta.setModel(new DefaultComboBoxModel<Conta>(contas.toArray(new Conta[contas.size()])));
	}
	
	/**
	 * Adiciona um ouvinte de eventos de transferência
	 * @param listener Ouvinte de eventos de transferência
	 */
	public void addSelecaoContaListener(SelecaoContaListener listener){
		selecaoContaListeners.add(listener);
	}
	
	/**
	 * Remove um ouvinte de eventos de transferência
	 * @param listener Ouvinte de eventos de transferência
	 */
	public void removeSelecaoContaListener(SelecaoContaListener listener){
		selecaoContaListeners.remove(listener);
	}
}

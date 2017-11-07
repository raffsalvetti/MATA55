package br.ufba.mata55.banco.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import br.ufba.mata55.banco.data.po.Conta;

/**
 * Formulário para tratar contas (criar e modificar)
 * @author raffaello.salvetti
 *
 */
public class ContaForm extends JDialog {
	
	private static final long serialVersionUID = -174348223292247877L;
	private JTextField textFieldNumero;
	private JTextField textFieldLimite;
	private JCheckBox chckbxNewCheckBox;
	private JLabel labelLimite;
	
	private JButton buttonSalvar, buttonRemover, buttonFechar, buttonBuscar;
	
	private Conta modificarConta = null;
	
	private List<ContaListener> contaListeners = Collections.synchronizedList(new ArrayList<ContaListener>()); 
	
	/**
	 * Construtor que recebe a instância do componente que está criando esse formulário 
	 * e uma interface de eventos para tratar contas
	 * @param parent Instância do formulário pai
	 * @param listener Interface de eventos que escutará este formulário
	 */
	public ContaForm(Component parent, ContaListener listener) {
		this(parent);
		addContaListener(listener);
	}
	
	/**
	 * Construtor que recebe a instância do componente que está criando esse formulário 
	 * @param parent Instância do formulário pai
	 */
	public ContaForm(Component parent) {
		setModal(true);
		setTitle("Nova Conta");
		setSize(250, 200);
		setAlwaysOnTop(true);
		setResizable(false);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		buttonSalvar = new JButton("Salvar");
		buttonSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (ContaListener contaListener : contaListeners) {
					try{
						if(modificarConta == null){
							contaListener.novaConta(
									new Conta(
											0,
											textFieldNumero.getText().trim(),
											Double.parseDouble(textFieldLimite.getText().trim()), 
											chckbxNewCheckBox.isSelected()
											)
									);
						} else {
							modificarConta.setNumero(textFieldNumero.getText().trim()); 
							modificarConta.setLimite(Double.parseDouble(textFieldLimite.getText().trim())); 
							modificarConta.setEspecial(chckbxNewCheckBox.isSelected());
							contaListener.modificarConta(modificarConta);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(ContaForm.this, "O número da conta e o limite devem ser números", "Ops...", JOptionPane.OK_OPTION);
						return;
					}
				}
				dispose();
			}
		});
		panelButtons.add(buttonSalvar);
		
		buttonBuscar = new JButton("Buscar");
		buttonBuscar.setVisible(false);
		panelButtons.add(buttonBuscar);
		
		buttonRemover = new JButton("Remover");
		buttonRemover.setVisible(false);
		buttonRemover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(ContaForm.this, "Deseja remover a conta?", "Remover Conta!!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					for (ContaListener contaListener : contaListeners) {
						contaListener.removerConta(modificarConta);
					}
					dispose();
				}
			}
		});
		panelButtons.add(buttonRemover);
		
		buttonFechar = new JButton("Fechar");
		buttonFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelButtons.add(buttonFechar);
		
		JPanel panelControls = new JPanel();
		getContentPane().add(panelControls, BorderLayout.CENTER);
		
		JPanel panelNumero = new JPanel();
		panelControls.add(panelNumero);
		
		JLabel labelNumero = new JLabel("Número:");
		panelNumero.add(labelNumero);
		
		textFieldNumero = new JTextField();
		labelNumero.setLabelFor(textFieldNumero);
		panelNumero.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		JPanel panelLimite = new JPanel();
		panelControls.add(panelLimite);
		
		labelLimite = new JLabel("Limite:");
		panelLimite.add(labelLimite);
		
		textFieldLimite = new JTextField();
		panelLimite.add(textFieldLimite);
		textFieldLimite.setColumns(10);
		
		JPanel panelAdicional = new JPanel();
		panelControls.add(panelAdicional);
		
		chckbxNewCheckBox = new JCheckBox("Conta especial?");
		panelAdicional.add(chckbxNewCheckBox);
	}
	
	/**
	 * Limpa os dados do formulário
	 */
	public void clear() {
		textFieldLimite.setText("");
		textFieldNumero.setText("");
		chckbxNewCheckBox.setSelected(false);
	}
	
	/**
	 * Adiciona um ouvinte de eventos
	 * @param listener Ouvinte de eventos
	 */
	public void addContaListener(ContaListener listener) {
		contaListeners.add(listener);
	}
	
	/**
	 * Remove um ouvinte de eventos
	 * @param listener Ouvinte de eventos 
	 */
	public void removeContaListener(ContaListener listener) {
		contaListeners.remove(listener);
	}

	/**
	 * Prepara esse formulário para uma nova conta
	 */
	public void nova() {
		modificarConta = null;
		setTitle("Nova Conta");
		textFieldNumero.setEditable(true);
		setVisible(true);
	}
	
	/**
	 * Prepara esse formulário para modificar uma conta
	 * @param contaSelecionada Conta a ser modificada
	 */
	public void modificar(Conta contaSelecionada) {
		modificarConta = contaSelecionada;
		setTitle("Modificar Conta");
		textFieldNumero.setText(String.valueOf(contaSelecionada.getNumero()));
		textFieldNumero.setEditable(false);
		textFieldLimite.setText(String.valueOf(contaSelecionada.getLimite()));
		chckbxNewCheckBox.setSelected(contaSelecionada.isEspecial());
		setVisible(true);
	}
	
	/**
	 * Prepara esse formulário para remover uma conta
	 * @param contaSelecionada Conta a ser removida
	 */
	public void remover(Conta contaSelecionada) {
		modificarConta = contaSelecionada;
		setTitle("Remover Conta");
		buttonSalvar.setVisible(false);
		buttonRemover.setVisible(true);
		textFieldNumero.setText(String.valueOf(contaSelecionada.getNumero()));
		textFieldNumero.setEditable(false);
		textFieldLimite.setText(String.valueOf(contaSelecionada.getLimite()));
		textFieldLimite.setEditable(false);
		chckbxNewCheckBox.setSelected(contaSelecionada.isEspecial());
		chckbxNewCheckBox.setEnabled(false);
		setVisible(true);
	}

	/**
	 * Prepara esse formulário para buscar uma conta
	 * @param listConta Lista de contas cadastradas no sistema
	 */
	public void buscar(final List<Conta> listConta) {
		setTitle("Buscar Conta");
		buttonSalvar.setVisible(false);
		buttonBuscar.setVisible(true);
		labelLimite.setVisible(false);
		buttonBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Conta conta : listConta) {
					if(String.valueOf(conta.getNumero()).equals(textFieldNumero.getText())) {
						for (ContaListener contaListener : contaListeners) {
							contaListener.buscarConta(conta);
						}
						dispose();
						return;
					}
				}
				JOptionPane.showMessageDialog(ContaForm.this, "Nenhuma conta foi encontrada!", "Ops...", JOptionPane.OK_OPTION);
			}
		});
		
		textFieldLimite.setVisible(false);
		chckbxNewCheckBox.setVisible(false);
		setVisible(true);
	}

}

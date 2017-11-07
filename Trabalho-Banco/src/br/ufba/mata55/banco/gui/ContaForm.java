package br.ufba.mata55.banco.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import org.hibernate.exception.ConstraintViolationException;

import javax.swing.JCheckBox;

import br.ufba.mata55.banco.data.dao.ContaDAO;
import br.ufba.mata55.banco.data.po.Conta;

/**
 * Formulário para tratar contas (criar e modificar)
 * @author raffaello.salvetti
 *
 */
public class ContaForm extends JDialog {
	
	private static final long serialVersionUID = -174348223292247877L;
	private JFormattedTextField textFieldNumero;
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
	 * @throws ParseException 
	 */
	public ContaForm(Component parent, ContaListener listener) {
		this(parent);
		addContaListener(listener);
	}
	
	/**
	 * Construtor que recebe a instância do componente que está criando esse formulário 
	 * @param parent Instância do formulário pai
	 * @throws ParseException 
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
				double input = 0;
				for (ContaListener contaListener : contaListeners) {
					try{
						if(textFieldNumero.getText().trim().isEmpty())
							throw new NullPointerException();
						
						input = Double.parseDouble(textFieldLimite.getText().trim());
						if(input < 0)
							throw new NumberFormatException();
						
						if(modificarConta == null){
							contaListener.novaConta(
									new Conta(
											0,
											textFieldNumero.getText().trim(),
											input, 
											chckbxNewCheckBox.isSelected()
											)
									);
							dispose();
						} else {
							modificarConta.setNumero(textFieldNumero.getText().trim()); 
							modificarConta.setLimite(input); 
							modificarConta.setEspecial(chckbxNewCheckBox.isSelected());
							contaListener.modificarConta(modificarConta);
							dispose();
						}
					} catch (NullPointerException ex1){
						JOptionPane.showMessageDialog(ContaForm.this, "O número da conta e o limite são obrigatórios!", "Ops...", JOptionPane.OK_OPTION);
						ex1.printStackTrace();
					} catch (NumberFormatException ex2) {
						JOptionPane.showMessageDialog(ContaForm.this, "O valor do campo limite deve ser numérico positivo!", "Ops...", JOptionPane.OK_OPTION);
						ex2.printStackTrace();
					} catch (ConstraintViolationException ex3) {
						JOptionPane.showMessageDialog(ContaForm.this, "O número da conta já foi cadastrado!", "Ops...", JOptionPane.OK_OPTION);
						ex3.printStackTrace();
					}
				}
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
		
		try {
			textFieldNumero = new JFormattedTextField(new MaskFormatter("####-#"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
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
		((AbstractDocument) textFieldLimite.getDocument()).setDocumentFilter(new DocumentFilter(){
			@Override
			public void insertString(DocumentFilter.FilterBypass fb, int offset,
		            String text, AttributeSet attr) throws BadLocationException {
				if(offset < 10)
					fb.insertString(offset, text, attr);
		    }
			@Override
		    public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
		            String text, AttributeSet attrs) throws BadLocationException {
				if(offset < 10)
					fb.insertString(offset, text, attrs);
		    }
		});
		
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
	 */
	public void buscar() {
		setTitle("Buscar Conta");
		buttonSalvar.setVisible(false);
		buttonBuscar.setVisible(true);
		labelLimite.setVisible(false);
		buttonBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ContaDAO cdao = new ContaDAO();
				Conta conta = cdao.findByNumero(textFieldNumero.getText());
				if(conta != null) {
					for (ContaListener contaListener : contaListeners) {
						contaListener.buscarConta(conta);
					}
					dispose();
					return;
				}
				JOptionPane.showMessageDialog(ContaForm.this, "Nenhuma conta foi encontrada!", "Ops...", JOptionPane.OK_OPTION);
			}
		});
		
		textFieldLimite.setVisible(false);
		chckbxNewCheckBox.setVisible(false);
		setVisible(true);
	}

}

package br.ufba.mata55.banco.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import br.ufba.mata55.banco.data.dao.ContaDAO;
import br.ufba.mata55.banco.data.po.Conta;
import br.ufba.mata55.banco.gui.relatoriografico.GraficoMovimentacao;
import br.ufba.mata55.banco.gui.relatoriografico.GraficoRendimentoPorTipoMovimentacao;
import br.ufba.mata55.banco.gui.relatoriografico.RelatorioMovimentacao;

/**
 * Formulário principal da aplicação
 * @author raffaello.salvetti
 *
 */
public class BancoMainForm extends JFrame implements ActionListener, ContaListener, SelecaoContaListener {

	private static final long serialVersionUID = 4499805651753011704L;
	private JPanel panelSaldo, panelConta, panelOperacao, panelButtons, panelExtrato, panelDescricao, panelValor;
	private JLabel labelSaldo, labelValor, labelConta, labelLabelSaldo, labelDescricao;
	private JTextField textFieldValor, textFieldDescricao;
	private JScrollPane scrollPaneExtrato;
	private JTable tableExtrato;
	private JButton buttonSacar, buttonDepositar, buttonTransferir, buttonNovaConta;
	private JComboBox<Conta> comboBoxSelecaoContaConta;
	//private List<Conta> listConta = new ArrayList<Conta>();
	private ContaDAO contaDAO;
	private SelecaoContaForm selecaoContaForm;
	private Conta contaSelecionada = null;
	private JMenuBar menuBar;
	private JMenu jMenuConta, jMenuOperacao, jMenuRelatorio, jMenuGrafico, jMenuAjuda;
	private JMenuItem 	jMenuItemNovaConta, jMenuItemModificarConta, jMenuItemTransferir, 
						jMenuItemRelatorioOperacoes, jMenuItemGraficoOperacoe, jMenuItemGraficoLucroBancoMovimentacao, 
						jMenuItemSobre, jMenuItemRemoverConta, jMenuItemBuscarConta, jMenuItemSacar, jMenuItemDepositar;

	/**
	 * Construtor padrão
	 */
	public BancoMainForm() {
		setResizable(false);
		setTitle("O Banco do POVO - A vida pede mais que um banco.");
		setBounds(100, 100, 450, 520);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)((dimension.getWidth() / 2) - (getWidth() / 2)), (int)((dimension.getHeight() / 2) - (getHeight() / 2)));
		
		panelConta = new JPanel();
		panelConta.setBorder(new TitledBorder(null, "Seleção de Conta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelConta);
		
		labelConta = new JLabel("Conta:");
		panelConta.add(labelConta);
		labelConta.setLabelFor(comboBoxSelecaoContaConta);
		
		comboBoxSelecaoContaConta = new JComboBox<Conta>();
		comboBoxSelecaoContaConta.addActionListener(this);
		panelConta.add(comboBoxSelecaoContaConta);
		comboBoxSelecaoContaConta.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma conta"}));
		
		panelOperacao = new JPanel();
		panelOperacao.setBorder(new TitledBorder(null, "Dados da operação", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelOperacao);
		panelOperacao.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panelDescricao = new JPanel();
		panelOperacao.add(panelDescricao);
		
		labelDescricao = new JLabel("Descrição:");
		panelDescricao.add(labelDescricao);
		
		textFieldDescricao = new JTextField();
		panelDescricao.add(textFieldDescricao);
		textFieldDescricao.setColumns(10);
		labelDescricao.setLabelFor(textFieldDescricao);
		
		panelValor = new JPanel();
		panelOperacao.add(panelValor);
		
		labelValor = new JLabel("Valor:");
		panelValor.add(labelValor);
		labelValor.setLabelFor(textFieldValor);
		
		textFieldValor = new JTextField();
		panelValor.add(textFieldValor);
		textFieldValor.setColumns(10);
		
		panelButtons = new JPanel();
		panelButtons.setBorder(new TitledBorder(null, "Operações", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelButtons);
		
		buttonSacar = new JButton("Sacar");
		panelButtons.add(buttonSacar);
		
		buttonDepositar = new JButton("Depositar");
		panelButtons.add(buttonDepositar);
		
		buttonTransferir = new JButton("Transferir");
		buttonTransferir.addActionListener(this);
		panelButtons.add(buttonTransferir);
		
		buttonNovaConta = new JButton("Nova Conta");
		buttonNovaConta.addActionListener(this);
		panelButtons.add(buttonNovaConta);
		buttonDepositar.addActionListener(this);
		buttonSacar.addActionListener(this);
		
		panelSaldo = new JPanel();
		getContentPane().add(panelSaldo);
		
		labelLabelSaldo = new JLabel("Saldo:");
		panelSaldo.add(labelLabelSaldo);
		
		labelSaldo = new JLabel("R$ 000000000.00");
		panelSaldo.add(labelSaldo);
		labelSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelExtrato = new JPanel();
		panelExtrato.setBorder(new TitledBorder(null, "Movimentações", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(panelExtrato);
		panelExtrato.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		scrollPaneExtrato = new JScrollPane();
		scrollPaneExtrato.setPreferredSize(new Dimension(370, 200));
		panelExtrato.add(scrollPaneExtrato);
		
		tableExtrato = new JTable();
		
		tableExtrato.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Tipo", "Descrição", "Valor"
				}
			));
		scrollPaneExtrato.setViewportView(tableExtrato);
		
		selecaoContaForm = new SelecaoContaForm(this);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		jMenuConta = new JMenu("Conta");
		menuBar.add(jMenuConta);
		
		jMenuItemNovaConta = new JMenuItem("Nova");
		jMenuItemNovaConta.addActionListener(this);
		jMenuConta.add(jMenuItemNovaConta);
		
		jMenuItemModificarConta = new JMenuItem("Modificar");
		jMenuItemModificarConta.addActionListener(this);
		jMenuConta.add(jMenuItemModificarConta);
		
		jMenuItemRemoverConta = new JMenuItem("Remover");
		jMenuItemRemoverConta.addActionListener(this);
		jMenuConta.add(jMenuItemRemoverConta);
		
		jMenuItemBuscarConta = new JMenuItem("Buscar");
		jMenuItemBuscarConta.addActionListener(this);
		jMenuConta.add(jMenuItemBuscarConta);
		
		jMenuOperacao = new JMenu("Operações");
		menuBar.add(jMenuOperacao);
		
		jMenuItemTransferir = new JMenuItem("Transferir");
		jMenuItemTransferir.addActionListener(this);
		jMenuOperacao.add(jMenuItemTransferir);
		
		jMenuItemSacar = new JMenuItem("Sacar");
		jMenuItemSacar.addActionListener(this);
		jMenuOperacao.add(jMenuItemSacar);
		
		jMenuItemDepositar = new JMenuItem("Depositar");
		jMenuItemDepositar.addActionListener(this);
		jMenuOperacao.add(jMenuItemDepositar);
		
		jMenuRelatorio = new JMenu("Relatório");
		menuBar.add(jMenuRelatorio);
		
		jMenuGrafico = new JMenu("Gráficos");
		menuBar.add(jMenuGrafico);
		
		jMenuItemRelatorioOperacoes = new JMenuItem("Operações");
		jMenuItemRelatorioOperacoes.addActionListener(this);
		jMenuRelatorio.add(jMenuItemRelatorioOperacoes);
		
		jMenuItemGraficoOperacoe = new JMenuItem("Operações");
		jMenuItemGraficoOperacoe.addActionListener(this);
		jMenuGrafico.add(jMenuItemGraficoOperacoe);
		
		jMenuItemGraficoLucroBancoMovimentacao = new JMenuItem("Lucro");
		jMenuItemGraficoLucroBancoMovimentacao.addActionListener(this);
		jMenuGrafico.add(jMenuItemGraficoLucroBancoMovimentacao);
		
		jMenuAjuda = new JMenu("Ajuda");
		menuBar.add(jMenuAjuda);
		
		jMenuItemSobre = new JMenuItem("Sobre");
		jMenuItemSobre.addActionListener(this);
		jMenuAjuda.add(jMenuItemSobre);
		selecaoContaForm.addSelecaoContaListener(this);
		
		contaDAO = new ContaDAO();
		List<Conta> listConta = contaDAO.getAll();
		if(!listConta.isEmpty()) {
			comboBoxSelecaoContaConta.setModel(new DefaultComboBoxModel<Conta>(listConta.toArray(new Conta[listConta.size()])));
			contaSelecionada = listConta.get(0); 
			updateTableModel();
	        showSaldo();
		}
	}

	/**
	 * Tratamento de eventos de entrada como clique de mouse em botões 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	    Double input = null;
	    
	    if(e.getSource() == buttonNovaConta || e.getSource() == jMenuItemNovaConta) {
	        new ContaForm(this, this).nova();
	    } else if(e.getSource() == jMenuItemModificarConta && validaContaSelecionada()){
	    	new ContaForm(this, this).modificar(contaSelecionada);
	    } else if(e.getSource() == jMenuItemRemoverConta && validaContaSelecionada()) {
	    	new ContaForm(this, this).remover(contaSelecionada);
	    } else if(e.getSource() == jMenuItemBuscarConta){
	    	new ContaForm(this, this).buscar(contaDAO.getAll());
	    } else if(e.getSource() == jMenuItemSobre) {
	    	new Sobre(this).setVisible(true);;
	    } else if(e.getSource() == jMenuItemRelatorioOperacoes) {
	    	new RelatorioMovimentacao(this, contaDAO.getAll()).mostrar();
	    } else if(e.getSource() == jMenuItemGraficoOperacoe) {
	    	new GraficoMovimentacao(this, contaDAO.getAll()).mostrar();
	    } else if(e.getSource() == jMenuItemGraficoLucroBancoMovimentacao){
	    	new GraficoRendimentoPorTipoMovimentacao(this).mostrar();
	    } else if(e.getSource() == comboBoxSelecaoContaConta) {
	    	if(comboBoxSelecaoContaConta.getSelectedItem() instanceof Conta) {
	    		contaSelecionada = (Conta)comboBoxSelecaoContaConta.getSelectedItem();
	    	}
	        if(contaSelecionada != null) {
		        updateTableModel();
		        showSaldo();
	        }
	    } else if((e.getSource() == buttonSacar || e.getSource() == jMenuItemSacar) && (input = validarInputs()) != null) {
	    	if(contaSelecionada.getSaldo() + contaSelecionada.getLimite() - input < 0) {
	            JOptionPane.showMessageDialog(this, "Sem limite para essa operação!", "Ops...", JOptionPane.OK_OPTION);
	            return;
	        }
	        if(contaSelecionada.getSaldo() - input < 0) {
	            if(JOptionPane.showConfirmDialog(this, "Você usará seu limite, continuar?", "Uso do limite", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
	                return;
	        }
	        contaSelecionada.debitar(input, textFieldDescricao.getText());
	        updateTableModel();
	        showSaldo();
	    } else if((e.getSource() == buttonDepositar || e.getSource() == jMenuItemDepositar) && (input = validarInputs()) != null) {
	        contaSelecionada.creditar(input, textFieldDescricao.getText());
	        updateTableModel();
	        showSaldo();
	    } else if ((e.getSource() == buttonTransferir || e.getSource() == jMenuItemTransferir) && (input = validarInputs()) != null) {
	    	ArrayList<Conta> lista = new ArrayList<Conta>(contaDAO.getAll());
	    	lista.remove(contaSelecionada);
	    	if(lista.size() == 0) {
	    		JOptionPane.showMessageDialog(this, "Não existe outra conta no sistema para fazer a transferencia", "Ops...", JOptionPane.OK_OPTION);
	            return;
	    	}
	    	if(contaSelecionada.getSaldo() + contaSelecionada.getLimite() - input < 0) {
	            JOptionPane.showMessageDialog(this, "Sem limite para essa operação!", "Ops...", JOptionPane.OK_OPTION);
	            return;
	        }
	        if(contaSelecionada.getSaldo() - input < 0) {
	            if(JOptionPane.showConfirmDialog(this, "Você usará seu limite, continuar?", "Uso do limite", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
	                return;
	        }
	    	selecaoContaForm.setListaContas(lista);
	    	selecaoContaForm.setValoresTransferencia(input, textFieldDescricao.getText().trim());
	    	selecaoContaForm.setVisible(true);
	    	updateTableModel();
	    	showSaldo();
	    }
	}
	
	/**
	 * Método que valida a seleção de uma conta.
	 * Retorna true caso uma conta tenha sido selecionada e falso caso contrario
	 * @return conta selecionada ou não
	 */
	private boolean validaContaSelecionada(){
		if(contaSelecionada == null) {
	        JOptionPane.showMessageDialog(this, "Selecione uma conta corrente", "Ops...", JOptionPane.OK_OPTION);
	        return false;
	    }
		return true;
	}
	
	/**
	 * Método que valida entrada do cliente para efetuar operações.
	 * @return valor da que será operado
	 */
	private Double validarInputs() {
		Double d = null;
		if(validaContaSelecionada()){
		    if(textFieldValor.getText().trim().equals("")) {
		        JOptionPane.showMessageDialog(this, "Digite um valor...", "Ops...", JOptionPane.OK_OPTION);
		    } else {
				try {
					d = Double.parseDouble(textFieldValor.getText().trim());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "Isso não é um valor válido", "Ops...", JOptionPane.OK_OPTION);
					ex.printStackTrace();
					d = null;
				}
		    }
		}
		return d;
	}
	
	/**
	 * Mostra o saldo atual da conta selecionada
	 */
	private void showSaldo() {
		textFieldValor.setText("");
		textFieldDescricao.setText("");
		if(contaSelecionada.getSaldo() < 0) {
			labelSaldo.setForeground(Color.RED);
		} else {
			labelSaldo.setForeground(Color.BLACK);
		}
		labelSaldo.setText(String.format("R$ %012.2f", contaSelecionada.getSaldo()));
	}
	
	/**
	 * Atualiza dados da tabela de operações de uma conta (extrato imediato)
	 */
	private void updateTableModel() {
		tableExtrato.setModel(contaSelecionada.getTableModelMovimentacao());
	}

	/**
	 * Evento de criação de conta 
	 */
	@Override
	public void novaConta(Conta conta) {
		contaDAO.save(conta);
		//listConta.add(conta);
		List<Conta> listConta = contaDAO.getAll();
		comboBoxSelecaoContaConta.setModel(new DefaultComboBoxModel<Conta>(listConta.toArray(new Conta[listConta.size()])));
		comboBoxSelecaoContaConta.getModel().setSelectedItem(conta);
	}

	/**
	 * Evento de transferencia entre contas
	 */
	@Override
	public void transferir(Conta conta, double valor, String descricao) {
		contaSelecionada.transferir(valor, conta, descricao);
		updateTableModel();
        showSaldo();
	}

	/**
	 * Evento de modificação de contas
	 */
	@Override
	public void modificarConta(Conta conta) {
		System.out.println("Conta modificada: " + conta);
	}

	@Override
	public void removerConta(Conta conta) {
		contaDAO.remove(conta.getCodigo());
		//listConta.remove(conta);
		List<Conta> listConta = contaDAO.getAll();
		if(listConta.isEmpty()){
			contaSelecionada = null;
			comboBoxSelecaoContaConta.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma conta"}));
			tableExtrato.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Tipo", "Descrição", "Valor"
					}
				));
		} else {
			comboBoxSelecaoContaConta.setModel(new DefaultComboBoxModel<Conta>(listConta.toArray(new Conta[listConta.size()])));
			comboBoxSelecaoContaConta.getModel().setSelectedItem(listConta.get(0));
			updateTableModel();
	        showSaldo();
		}
	}
	
	@Override
	public void buscarConta(Conta conta) {
		comboBoxSelecaoContaConta.getModel().setSelectedItem(conta);
	}
}

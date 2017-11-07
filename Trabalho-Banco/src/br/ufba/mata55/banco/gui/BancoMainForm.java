package br.ufba.mata55.banco.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.hibernate.exception.ConstraintViolationException;

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
import br.ufba.mata55.banco.data.dao.MovimentacaoDAO;
import br.ufba.mata55.banco.data.po.Conta;
import br.ufba.mata55.banco.gui.relatoriografico.GraficoTipoMovimentacao;
import br.ufba.mata55.banco.gui.relatoriografico.GraficoValorMovimentacao;
import br.ufba.mata55.banco.gui.relatoriografico.GraficoRendimentoPorTipoMovimentacao;
import br.ufba.mata55.banco.gui.relatoriografico.RelatorioConta;
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
	private JTextField textFieldDescricao;
	private JTextField textFieldValor;
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
						jMenuItemRelatorioOperacoes, jMenuItemRelatorioContas, jMenuItemGraficoOperacoe, jMenuItemGraficoLucroBancoMovimentacao, jMenuItemGraficoValorMovimentacao, 
						jMenuItemSobre, jMenuItemRemoverConta, jMenuItemBuscarConta, jMenuItemSacar, jMenuItemDepositar;

	/**
	 * Construtor padrão
	 * @throws ParseException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		textFieldDescricao.addKeyListener(new KeyListener() {
			MovimentacaoDAO mdao = new MovimentacaoDAO();
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(textFieldDescricao.getText().trim().length() >= 3 && !(
						e.getKeyCode() == KeyEvent.VK_LEFT ||
						e.getKeyCode() == KeyEvent.VK_RIGHT ||
						e.getKeyCode() == KeyEvent.VK_SHIFT ||
						e.getKeyCode() == KeyEvent.VK_CONTROL || 
						e.getKeyCode() == KeyEvent.VK_HOME || 
						e.getKeyCode() == KeyEvent.VK_PAGE_DOWN ||
						e.getKeyCode() == KeyEvent.VK_PAGE_UP ||
						e.getKeyCode() == KeyEvent.VK_END ||
						e.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
						e.getKeyCode() == KeyEvent.VK_DELETE
						)){
					List<String> lista = mdao.listDescricaoMovimentacaoPorCodConta(contaSelecionada.getCodigo(), textFieldDescricao.getText().trim());
					if(!lista.isEmpty()) {
						int p = textFieldDescricao.getText().length(); 
						textFieldDescricao.setText(lista.get(0));
						textFieldDescricao.select(p, textFieldDescricao.getText().length());
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
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
		((AbstractDocument) textFieldValor.getDocument()).setDocumentFilter(new DocumentFilter(){
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
				if(text.isEmpty())
					fb.remove(offset, length);
		    }
		});
		
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
		
		jMenuItemRelatorioContas = new JMenuItem("Contas");
		jMenuItemRelatorioContas.addActionListener(this);
		jMenuRelatorio.add(jMenuItemRelatorioContas);
		
		jMenuItemGraficoOperacoe = new JMenuItem("Operações");
		jMenuItemGraficoOperacoe.addActionListener(this);
		jMenuGrafico.add(jMenuItemGraficoOperacoe);
		
		jMenuItemGraficoLucroBancoMovimentacao = new JMenuItem("Lucro");
		jMenuItemGraficoLucroBancoMovimentacao.addActionListener(this);
		jMenuGrafico.add(jMenuItemGraficoLucroBancoMovimentacao);
		
		jMenuItemGraficoValorMovimentacao = new JMenuItem("Movimentação");
		jMenuItemGraficoValorMovimentacao.addActionListener(this);
		jMenuGrafico.add(jMenuItemGraficoValorMovimentacao);
		
		jMenuAjuda = new JMenu("Ajuda");
		menuBar.add(jMenuAjuda);
		
		jMenuItemSobre = new JMenuItem("Sobre");
		jMenuItemSobre.addActionListener(this);
		jMenuAjuda.add(jMenuItemSobre);
		selecaoContaForm.addSelecaoContaListener(this);
		
		contaDAO = new ContaDAO();
		List<Conta> listConta = contaDAO.findAll();
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
	    	new ContaForm(this, this).buscar();
	    } else if(e.getSource() == jMenuItemSobre) {
	    	new Sobre(this).setVisible(true);
	    } else if(e.getSource() == jMenuItemRelatorioOperacoes) {
	    	new RelatorioMovimentacao(this).mostrar();
	    } else if(e.getSource() == jMenuItemRelatorioContas) {
	    	new RelatorioConta(this).mostrar();
	    } else if(e.getSource() == jMenuItemGraficoOperacoe) {
	    	new GraficoTipoMovimentacao(this).mostrar();
	    } else if(e.getSource() == jMenuItemGraficoLucroBancoMovimentacao){
	    	new GraficoRendimentoPorTipoMovimentacao(this).mostrar();
	    } else if(e.getSource() == jMenuItemGraficoValorMovimentacao){
	    	new GraficoValorMovimentacao(this).mostrar();
	    } else if(e.getSource() == comboBoxSelecaoContaConta) {
	    	if(comboBoxSelecaoContaConta.getSelectedItem() instanceof Conta) {
	    		contaSelecionada = contaDAO.findById(((Conta)comboBoxSelecaoContaConta.getSelectedItem()).getCodigo());
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
	        if(JOptionPane.showConfirmDialog(this, "Confirma saque de R$" + input + "?", "Saque", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		        contaSelecionada.debitar(input, textFieldDescricao.getText());
		        updateTableModel();
		        showSaldo();
		        contaDAO.update(contaSelecionada);
	        }
	    } else if((e.getSource() == buttonDepositar || e.getSource() == jMenuItemDepositar) && (input = validarInputs()) != null) {
	    	if(JOptionPane.showConfirmDialog(this, "Confirma deposito de R$" + input + "?", "Deposito", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		        contaSelecionada.creditar(input, textFieldDescricao.getText());
		        updateTableModel();
		        showSaldo();
		        contaDAO.update(contaSelecionada);
	    	}
	    } else if ((e.getSource() == buttonTransferir || e.getSource() == jMenuItemTransferir) && (input = validarInputs()) != null) {
	    	ArrayList<Conta> lista = new ArrayList<Conta>(contaDAO.findAll());
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
					if(d <= 0) 
						throw new Exception();
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void atualizarControles() {
		List<Conta> listConta = contaDAO.findAll();
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
			contaSelecionada = listConta.get(0);
			updateTableModel();
	        showSaldo();
		}
	}

	/**
	 * Evento de criação de conta 
	 */
	@Override
	public void novaConta(Conta conta) {
		if(contaDAO.findByNumero(conta.getNumero()) != null)
			throw new ConstraintViolationException(null, null, null);
		contaDAO.persist(conta);
		atualizarControles();
	}

	/**
	 * Evento de transferencia entre contas
	 */
	@Override
	public void transferir(Conta conta, double valor, String descricao) {
		contaSelecionada.transferir(valor, conta, descricao);
        contaDAO.update(contaSelecionada);
        contaDAO.update(conta);
		updateTableModel();
        showSaldo();
	}

	/**
	 * Evento de modificação de contas
	 */
	@Override
	public void modificarConta(Conta conta) {
		contaDAO.update(conta);
		atualizarControles();
	}

	
	@Override
	public void removerConta(Conta conta) {
		contaDAO.delete(conta);
		atualizarControles();
	}
	
	@Override
	public void buscarConta(Conta conta) {
		comboBoxSelecaoContaConta.getModel().setSelectedItem(conta);
	}
}

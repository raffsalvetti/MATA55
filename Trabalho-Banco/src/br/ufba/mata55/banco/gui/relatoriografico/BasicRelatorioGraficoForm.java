package br.ufba.mata55.banco.gui.relatoriografico;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe base para todas as implementações de gráficos e relatórios
 * @author raffaello.salvetti
 *
 */
public abstract class BasicRelatorioGraficoForm extends JFrame {
	private static final long serialVersionUID = -6512541402466001365L;
	private JPanel panel, panelContent;
	private JButton buttonFechar, buttonImprimir;
	
	/**
	 * Construtor que recebe um frame pai e um titulo para a janela
	 * @param parent Frame pai
	 * @param title Titulo da janela
	 */
	public BasicRelatorioGraficoForm(Component parent, String title) {
		setTitle(title);
		setMinimumSize(new Dimension(800, 600));
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		buttonImprimir = new JButton("Imprimir");
		buttonImprimir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(BasicRelatorioGraficoForm.this, "Essa função não foi implementada ainda!", "Não Implementado", JOptionPane.OK_OPTION);
			}
		});
		panel.add(buttonImprimir);
		
		buttonFechar = new JButton("Fechar");
		buttonFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(buttonFechar);
		panelContent = new JPanel(new FlowLayout());
		getContentPane().add(panelContent, BorderLayout.CENTER);
	}
	
	/**
	 * Usado para mostrar os dados do componente
	 */
	public void mostrar() {
		pack();
		panelContent.add(generateContent(panelContent));
		setVisible(true);
	}
	
	/**
	 * Deve ser implementado de forma a retornar um componente gráfico
	 * @return Compoenete gráfico para ser adicionado à janela
	 */
	abstract protected Component generateContent(JPanel parent);
}

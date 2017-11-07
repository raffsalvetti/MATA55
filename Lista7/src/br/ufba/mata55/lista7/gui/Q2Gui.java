package br.ufba.mata55.lista7.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Q2Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1658212155846713493L;
	private JPanel panelTabelaBotoes;
	private List<String> listaNomeBotoes = new ArrayList<String>(Arrays.asList(new String[] {
			"7", "8", "9", "+",
			"4", "5", "6", "-",
			"1", "2", "3", "*",
			"C", "0", "=", "/"
			}));
	
	public Q2Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(320, 240));
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		setLocation((int)(tk.getScreenSize().getWidth() / 2 - getWidth() / 2), (int)(tk.getScreenSize().getHeight() / 2 - getHeight() / 2));
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(new TextField(), BorderLayout.NORTH);
		
		panelTabelaBotoes = new JPanel(new GridLayout(4, 4));
		JButton b;
		for (String nomeBotao : listaNomeBotoes) {
			b = new JButton(nomeBotao);
			panelTabelaBotoes.add(b);
		}
		getContentPane().add(panelTabelaBotoes, BorderLayout.CENTER);
	}
}

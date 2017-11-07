package br.ufba.mata55.lista8;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;

public class Q8Gui extends JFrame {

	private static final long serialVersionUID = -5523322030565221475L;
	private JPanel jPanelA, jPanelB;
	private JButton buttonProximo, buttonAnterior;
	private CardLayout cardLayout;
	private JPanel panelButtonProximo;
	private JPanel panelButtonAnterior;
	public Q8Gui() {
		Dimension dimension = new Dimension(320, 240); 
		setTitle("Programa Lista 8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(dimension);
		setPreferredSize(dimension);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setLocation((int)(toolkit.getScreenSize().getWidth() - getWidth()) / 2, (int)(toolkit.getScreenSize().getHeight() - getHeight()) / 2);		
		
		setResizable(false);
		
		cardLayout = new CardLayout(10, 10);
		getContentPane().setLayout(cardLayout);
		
		jPanelA = new JPanel();
		jPanelA.setLayout(new GridLayout(3, 1));
		JLabel labelPanelA = new JLabel("Painel A");
		labelPanelA.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelA.add(labelPanelA);
		getContentPane().add(jPanelA, "ANTERIOR");
		
		panelButtonProximo = new JPanel();
		jPanelA.add(panelButtonProximo);
		
		buttonProximo = new JButton("Proximo");
		panelButtonProximo.add(buttonProximo);
		buttonProximo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(getContentPane(), "PROXIMO");
			}
		});
		
		jPanelB = new JPanel();
		jPanelB.setLayout(new GridLayout(3, 1));
		JLabel labelPanelB = new JLabel("Painel B");
		labelPanelB.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelB.add(labelPanelB);
		getContentPane().add(jPanelB, "PROXIMO");
		
		panelButtonAnterior = new JPanel();
		jPanelB.add(panelButtonAnterior);
		
		buttonAnterior = new JButton("Anterior");
		panelButtonAnterior.add(buttonAnterior);
		buttonAnterior.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(getContentPane(), "ANTERIOR");
			}
		});
		
		cardLayout.show(getContentPane(), "ANTERIOR");
		setVisible(true);
	}
}

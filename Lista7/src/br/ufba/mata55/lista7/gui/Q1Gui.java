package br.ufba.mata55.lista7.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Q1Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5681010535534026305L;
	private JPanel panelCalculo, panelTabelaPlanetas, panelPlanetas;
	private ButtonGroup buttonGroupPanetas;
	private List<String> listaNomePlanetas = new ArrayList<String>(Arrays.asList(new String[] {
			"Mercúrio",
			"Marte",
			"Saturno",
			"Vênus",
			"Júpter",
			"Urano"
			}));
	
	public Q1Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(320, 240));
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		setLocation((int)(tk.getScreenSize().getWidth() / 2 - getWidth() / 2), (int)(tk.getScreenSize().getHeight() / 2 - getHeight() / 2));
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		
		panelCalculo = new JPanel(new GridLayout(2, 2){{setHgap(10); setVgap(5);}});
		panelCalculo.add(new JLabel("Peso na Terra (kg): "));
		panelCalculo.add(new JLabel());
		panelCalculo.add(new TextField(10));
		panelCalculo.add(new JButton("Calcular Peso"));
		getContentPane().add(panelCalculo);
		
		panelTabelaPlanetas = new JPanel(new GridLayout(2, 3));
		buttonGroupPanetas = new ButtonGroup();
		JRadioButton p;
		for (String planeta : listaNomePlanetas) {
			p = new JRadioButton(planeta);
			p.setSelected(planeta.equalsIgnoreCase("mercúrio"));
			buttonGroupPanetas.add(p);
			panelTabelaPlanetas.add(p);
		}
		
		panelPlanetas = new JPanel(new BorderLayout(0, 10));
		panelPlanetas.add(new JLabel("Selecione o Planeta:"){{setHorizontalTextPosition(JLabel.LEFT);}}, BorderLayout.NORTH);
		panelPlanetas.add(panelTabelaPlanetas, BorderLayout.CENTER);
		getContentPane().add(panelPlanetas);
	}
}

package br.ufba.mata55.banco.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Formulário de informações sobre o aplicativo
 * @author raffaello.salvetti
 *
 */
public class Sobre extends JDialog implements KeyListener {

	private static final long serialVersionUID = 9114433940470400756L;
	private final JPanel contentPanel = new JPanel();
	private JLabel labelSobre;
	private JPanel buttonPane;
	private JButton okButton;
	
	/**
	 * Construtor que recebe uma instancia do formulário pai
	 * @param parent Instancia do formulário pai
	 */
	public Sobre(Component parent) {
		super((JFrame)parent);
		setAlwaysOnTop(true);
		setModal(true);
		setTitle("Sobre");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setMinimumSize(new Dimension(400, 250));
		setPreferredSize(new Dimension(400, 250));
		pack();
		setLocationRelativeTo(parent);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		addKeyListener(this);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		labelSobre = new JLabel();
		labelSobre.setHorizontalAlignment(SwingConstants.CENTER);
		labelSobre.setText("<html>Universidade Federal da Bahia - UFBA<br>Programação Orientada a Objetos - MATA55<br>Aplicação para estudo de interface gráfica<br><br>Raffaello Salvetti<br>06/09/2016</html>");
		labelSobre.setIcon(new ImageIcon(getClass().getResource("/resources/images/ufba.png")));
		contentPanel.add(labelSobre, BorderLayout.CENTER);
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("OK");
		okButton.addKeyListener(this);
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Sobre.this.dispose();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}

	@Override
	public void keyTyped(KeyEvent e) {
						
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			dispose();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
}

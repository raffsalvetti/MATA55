package br.ufba.mata55.banco;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import org.h2.tools.Server;

import br.ufba.mata55.banco.gui.BancoMainForm;

/**
 * Classe principal que inicia a interface web do banco de dados (porta 9092 HTTP), o programa e a interface gráfica
 * @author raffaello.salvetti
 *
 */
public class BancoMain {
	private BancoMainForm window;
	private Server h2HttpInterface, h2TcpInterface;
	
	/**
	 * Construtor
	 */
	public BancoMain() {
		startDatabase();
		startGui();
	}
	
	/**
	 * Inicia todos os serviços de base de dados
	 */
	private void startDatabase() {
		try {
			h2HttpInterface = Server.createWebServer(new String[]{"-webPort", "9092"}).start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{
			h2HttpInterface = Server.createTcpServer("-tcpPort", "9123", "-tcpAllowOthers").start();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Finaliza todos os serviços de base de dados
	 */
	private void stopDatabase() {
		System.out.println("stopDatabase");
		try {
			if(h2TcpInterface != null && h2TcpInterface.isRunning(false)) {
				h2TcpInterface.shutdown();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(h2HttpInterface != null && h2HttpInterface.isRunning(false)) {
				h2HttpInterface.shutdown();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inicializa interface gráfica
	 */
	private void startGui(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new BancoMainForm();
					window.addWindowListener(new WindowListener() {
						
						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowClosing(WindowEvent e) {
							stopDatabase();
						}
						
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Método principal que executa o programa
	 * @param args Parâmetros de entrada (por console)
	 */
	public static void main(String[] args) {
		new BancoMain();
	}

}

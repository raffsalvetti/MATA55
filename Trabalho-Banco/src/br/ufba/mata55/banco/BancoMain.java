package br.ufba.mata55.banco;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import org.h2.tools.Server;

import br.ufba.mata55.banco.data.dao.ContaDAO;
import br.ufba.mata55.banco.data.dao.MovimentacaoDAO;
import br.ufba.mata55.banco.data.po.Conta;
import br.ufba.mata55.banco.data.po.Movimentacao;
import br.ufba.mata55.banco.gui.BancoMainForm;

/**
 * Classe principal que inicia a interface web do banco de dados (porta 9092 HTTP), o programa e a interface gráfica
 * @author raffaello.salvetti
 *
 */
public class BancoMain {
	private Server server;
	
	public BancoMain() {
		startDatabase();
		startGui();
	}
	
	private void startDatabase() {
		try {
			server = Server.createWebServer(new String[]{"-webPort", "9092"}).start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			new ContaDAO().createTable();
			new MovimentacaoDAO().createTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private void startGui(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BancoMainForm window = new BancoMainForm();
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

package br.ufba.mata55.banco.data.dao;

import br.ufba.mata55.banco.data.po.Conta;

public class ContaDAO extends AbstractDAO<Conta, Integer> {

	public ContaDAO() {
		super(Conta.class);
	}

	@Override
	public String creationScript() {
		String sql = "CREATE TABLE IF NOT EXISTS CONTA("
				+ "codigo INT PRIMARY KEY AUTO_INCREMENT, "
				+ "numero VARCHAR(6) DEFAULT '',"
				+ "saldo DOUBLE,"
				+ "especial BOOLEAN DEFAULT FALSE,"
				+ "limite DOUBLE"
				+ ");";
		return sql;
	}

}

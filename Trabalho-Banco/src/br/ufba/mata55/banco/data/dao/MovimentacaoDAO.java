package br.ufba.mata55.banco.data.dao;

import br.ufba.mata55.banco.data.po.Movimentacao;

public class MovimentacaoDAO extends AbstractDAO<Movimentacao, Integer> {

	public MovimentacaoDAO() {
		super(Movimentacao.class);
	}

	@Override
	public String creationScript() {
		String sql = "CREATE TABLE IF NOT EXISTS MOVIMENTACAO("
				+ "codigo INT PRIMARY KEY AUTO_INCREMENT, "
				+ "cod_conta INT,"
				+ "descricao VARCHAR DEFAULT '',"
				+ "valor INTEGER,"
				+ "tipo_movimentacao INT,"
				+ "data TIMESTAMP"
				+ ");";
		return sql;
	}
}

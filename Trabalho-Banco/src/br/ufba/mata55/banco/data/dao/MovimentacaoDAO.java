package br.ufba.mata55.banco.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import br.ufba.mata55.banco.data.bean.RelatorioLucroBean;
import br.ufba.mata55.banco.data.bean.RelatorioOperacaoBean;
import br.ufba.mata55.banco.data.po.Movimentacao;

/**
 * 
 * @author raffaello.salvetti
 * Classe que gerencia o acesso a dados para o modelo Movimentação
 */
public class MovimentacaoDAO extends AbstractDAO implements DaoInterface <Movimentacao, Integer> {

	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#persist(java.lang.Object)
	 */
	public void persist(Movimentacao entity) {
		openCurrentSessionwithTransaction().save(entity);
		closeCurrentSessionwithTransaction();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#update(java.lang.Object)
	 */
	public void update(Movimentacao entity) {
		openCurrentSessionwithTransaction().update(entity);
		closeCurrentSessionwithTransaction();
	}

	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#findById(java.io.Serializable)
	 */
	public Movimentacao findById(Integer id) {
		Movimentacao m = (Movimentacao) openCurrentSessionwithTransaction().get(Movimentacao.class, id);
		closeCurrentSessionwithTransaction();
		return m;
	}

	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#delete(java.lang.Object)
	 */
	public void delete(Movimentacao entity) {
		openCurrentSessionwithTransaction().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Movimentacao> findAll() {
		List<Movimentacao> list;
		Criteria criteria = openCurrentSessionwithTransaction().createCriteria(Movimentacao.class, "m");
		list = (List<Movimentacao>) criteria.list();
		closeCurrentSessionwithTransaction();
		return list;
	}
	
	/**
	 * Gera dados para montar o relatório de Lucro
	 * @return Bean com dados para montar o relatório
	 */
	@SuppressWarnings("unchecked")
	public List<RelatorioLucroBean> relatorioLucro() {
		List<RelatorioLucroBean> list;		
		String sql = "SELECT "
				+ "C.NUMERO, "
				+ "CASE "
				+ "		WHEN C.ESPECIAL THEN "
				+ "		'Especial' ELSE "
				+ "		'Normal' "
				+ "END AS TIPO, "
				+ "COALESCE(M.SUM(VALOR),0) VALOR "
				+ "FROM "
				+ "CONTA C "
				+ "LEFT JOIN MOVIMENTACAO M ON M.COD_CONTA = C.CODIGO AND M.IS_TAXA = TRUE "
				+ "WHERE C.DATA_EXCLUSAO IS NULL "
				+ "GROUP BY C.NUMERO, C.ESPECIAL, C.DATA_CRIACAO "
				+ "ORDER BY C.DATA_CRIACAO";
		SQLQuery query = openCurrentSessionwithTransaction().createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(RelatorioLucroBean.class));
		list = (List<RelatorioLucroBean>) query.list();
		closeCurrentSessionwithTransaction();
		return list;
	}
	
	/**
	 * Gera dados para montar o relatório de movimentações
	 * @return Bean com dados para montar o relatorio de movimentações
	 */
	@SuppressWarnings("unchecked")
	public List<RelatorioLucroBean> graficoMovimentacao() {
		List<RelatorioLucroBean> list;		
		String sql = "SELECT "
				+ "C.NUMERO, "
				+ "M.COD_TIPO_MOVIMENTACAO || '' AS TIPO, "
				+ "COALESCE(M.SUM(VALOR),0) VALOR "
				+ "FROM "
				+ "CONTA C "
				+ "JOIN MOVIMENTACAO M ON M.COD_CONTA = C.CODIGO AND M.IS_TAXA = FALSE "
				+ "WHERE C.DATA_EXCLUSAO IS NULL "
				+ "GROUP BY C.NUMERO, M.COD_TIPO_MOVIMENTACAO  "
				+ "ORDER BY C.DATA_CRIACAO";
		SQLQuery query = openCurrentSessionwithTransaction().createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(RelatorioLucroBean.class));
		list = (List<RelatorioLucroBean>) query.list();
		closeCurrentSessionwithTransaction();
		return list;
	}
	
	/**
	 * Gera dados para montar o relatório de operações
	 * @return Bean com dados para montar o relatorio de operações
	 */
	@SuppressWarnings("unchecked")
	public List<RelatorioOperacaoBean> relatorioOperacoes() {
		List<RelatorioOperacaoBean> list;		
		String sql = "SELECT T.COD_TIPO_MOVIMENTACAO, COUNT(M.VALOR) AS QUANTIDADE "
				+ "FROM "
				+ "( "
				+ "		SELECT 0 AS COD_TIPO_MOVIMENTACAO "
				+ "		UNION "
				+ "		SELECT 1 AS COD_TIPO_MOVIMENTACAO "
				+ ") AS T "
				+ "LEFT JOIN MOVIMENTACAO M ON T.COD_TIPO_MOVIMENTACAO = M.COD_TIPO_MOVIMENTACAO AND M.IS_TAXA = FALSE "
				+ "GROUP by T.COD_TIPO_MOVIMENTACAO "
				+ "ORDER BY T.COD_TIPO_MOVIMENTACAO ";
		SQLQuery query = openCurrentSessionwithTransaction().createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(RelatorioOperacaoBean.class));
		list = (List<RelatorioOperacaoBean>) query.list();
		closeCurrentSessionwithTransaction();
		return list;
	}
	
	/**
	 * Busca uma lista de descrições de movimentações para o "auto complete"
	 * @param codConta Código da conta 
	 * @param descricaoParcial Descrição parcial da movimentação
	 * @return Lista com descrções
	 */
	@SuppressWarnings("unchecked")
	public List<String> listDescricaoMovimentacaoPorCodConta(int codConta, String descricaoParcial) {
		List<String> list;		
		String sql = "SELECT DISTINCT "
				+ "UCASE(DESCRICAO) "
				+ "FROM MOVIMENTACAO "
				+ "WHERE IS_TAXA = FALSE "
				+ "AND COD_CONTA = :COD_CONTA "
				+ "AND UCASE(DESCRICAO) LIKE UCASE(:DESCRICAO) "
				+ "ORDER BY UCASE(DESCRICAO)";
		SQLQuery query = openCurrentSessionwithTransaction().createSQLQuery(sql);
		query
		.setParameter("COD_CONTA", codConta)
		.setParameter("DESCRICAO", descricaoParcial + "%");
		list = (List<String>) query.list();
		closeCurrentSessionwithTransaction();
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#deleteAll()
	 */
	public void deleteAll() {
		List<Movimentacao> entityList = findAll();
		for (Movimentacao entity : entityList) {
			delete(entity);
		}
	}
}

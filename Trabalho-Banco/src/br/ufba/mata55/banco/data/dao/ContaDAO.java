package br.ufba.mata55.banco.data.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.ufba.mata55.banco.data.bean.RelatorioContaBean;
import br.ufba.mata55.banco.data.bean.RelatorioMovimentacaoBean;
import br.ufba.mata55.banco.data.po.Conta;

/**
 * 
 * @author raffaello.salvetti
 * Classe que gerencia o acesso a dados para o modelo Conta
 */
public class ContaDAO extends AbstractDAO implements DaoInterface <Conta, Integer> {

	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#persist(java.lang.Object)
	 */
	public void persist(Conta entity) {
		openCurrentSessionwithTransaction().save(entity);
		closeCurrentSessionwithTransaction();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#update(java.lang.Object)
	 */
	public void update(Conta entity) {
		openCurrentSessionwithTransaction().update(entity);
		closeCurrentSessionwithTransaction();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#findById(java.io.Serializable)
	 */
	public Conta findById(Integer id) {
		Conta c = (Conta) openCurrentSessionwithTransaction().get(Conta.class, id);
		closeCurrentSessionwithTransaction();
		return c;
	}
	
	/**
	 * Busca uma conta pelo número
	 * @param numero Número da conta
	 * @return Conta
	 */
	public Conta findByNumero(String numero) {
		Conta c = null;
		Criteria criteria = openCurrentSessionwithTransaction().createCriteria(Conta.class, "c");
		criteria.
		add(Restrictions.like("numero", numero)).
		add(Restrictions.isNull("dataExclusao"));
		c = (Conta)criteria.uniqueResult();
		closeCurrentSessionwithTransaction();
		return c;
	}

	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#delete(java.lang.Object)
	 */
	public void delete(Conta entity) {
		entity.setDataExclusao(Calendar.getInstance().getTime());
		openCurrentSessionwithTransaction().update(entity);
		closeCurrentSessionwithTransaction();
	}

	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Conta> findAll() {
		List<Conta> list;
		Criteria criteria = openCurrentSessionwithTransaction().createCriteria(Conta.class, "c");
		criteria.add(Restrictions.isNull("dataExclusao"));
		list = (List<Conta>) criteria.list();
		closeCurrentSessionwithTransaction();
		return list;
	}
	
	/**
	 * Gera dados para montas o relatório de movimentações
	 * @return Bean com dados para montar o relatório
	 */
	@SuppressWarnings("unchecked")
	public List<RelatorioMovimentacaoBean> relatorioMovimentacoes() {
		List<RelatorioMovimentacaoBean> list;		
		String sql = "SELECT "
				+ "C.NUMERO AS CONTA, "
				+ "M.COD_TIPO_MOVIMENTACAO, "
				+ "TO_CHAR(M.DATA, 'DD/MM/YYYY HH24:MI:SS') AS DATA, "
				+ "M.DESCRICAO, "
				+ "M.DESCRICAO_ADICIONAL, "
				+ "M.VALOR "
				+ "FROM CONTA C "
				+ "JOIN MOVIMENTACAO M ON M.COD_CONTA = C.CODIGO "
				+ "WHERE C.DATA_EXCLUSAO IS NULL "
				+ "ORDER BY M.DATA";
		SQLQuery query = openCurrentSessionwithTransaction().createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(RelatorioMovimentacaoBean.class));
		list = (List<RelatorioMovimentacaoBean>) query.list();
		closeCurrentSessionwithTransaction();
		return list;
	}
	
	/**
	 * Gera dados para o relatório de contas
	 * @return Bean com dados para montar o relatório
	 */
	@SuppressWarnings("unchecked")
	public List<RelatorioContaBean> relatorioContas() {
		List<RelatorioContaBean> list;		
		String sql = "SELECT "
				+ "C.NUMERO, "
				+ "C.LIMITE, "
				+ "C.SALDO, "
				+ "CASE WHEN C.ESPECIAL THEN 'Sim' ELSE 'Não' END AS ESPECIAL, "
				+ "TO_CHAR(C.DATA_CRIACAO, 'DD/MM/YYYY') AS DATA_CRIACAO, "
				+ "CASE "
				+ "		WHEN C.DATA_EXCLUSAO IS NULL THEN "
				+ "			'ATIVA' ELSE "
				+ "			'EXCLUIDA (' || TO_CHAR(C.DATA_EXCLUSAO, 'DD/MM/YYYY') || ')' "
				+ "END AS STATUS, "
				+ "COUNT(M.CODIGO) AS MOVIMENTACOES "
				+ "FROM CONTA C "
				+ "LEFT JOIN MOVIMENTACAO M ON M.COD_CONTA = C.CODIGO AND M.IS_TAXA = FALSE "
				+ "GROUP BY C.NUMERO, C.LIMITE, C.SALDO, C.ESPECIAL, C.DATA_CRIACAO, C.DATA_EXCLUSAO "
				+ "ORDER BY C.DATA_CRIACAO ";
		SQLQuery query = openCurrentSessionwithTransaction().createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(RelatorioContaBean.class));
		list = (List<RelatorioContaBean>) query.list();
		closeCurrentSessionwithTransaction();
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.ufba.mata55.banco.data.dao.DaoInterface#deleteAll()
	 */
	public void deleteAll() {
		List<Conta> entityList = findAll();
		for (Conta entity : entityList) {
			delete(entity);
		}
	}
}

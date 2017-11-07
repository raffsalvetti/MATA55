package br.ufba.mata55.banco.data.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * 
 * @author raffaello.salvetti
 * Classe de abstração de base de dados
 */
public abstract class AbstractDAO {
	protected Session currentSession;
	protected Transaction currentTransaction;
	
	/**
	 * Abra a sessão para trabalhar com a base de dados
	 * @return Sessão
	 */
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	/**
	 * Abra a sessão com transação para trabalhar com a base de dados
	 * @return Sessão com transação
	 */
	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	/**
	 * Fecha uma sessão aberta
	 */
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	/**
	 * Finaliza uma transação e fecha uma sessão aberta
	 */
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	/**
	 * Configura sistema basico para acesso a dados
	 * @return Construtor de sessão
	 */
	private static SessionFactory getSessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		.configure() // configures settings from hibernate.cfg.xml
		.build();
		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		return sessionFactory;
	}

	/**
	 * Cria e retorna uma sessão
	 * @return Sessão da base de dados
	 */
	public Session getCurrentSession() {
		if(currentSession == null)
			currentSession = getSessionFactory().getCurrentSession();
		return currentSession;
	}

	/**
	 * Define uma sessão de base de dados
	 * @param currentSession Uma sessão de base de dados
	 */
	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	/**
	 * Retorna uma transação ativa
	 * @return Transação ativa
	 */
	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	/**
	 * Define uma transação ativa
	 * @param currentTransaction Uma transação ativa 
	 */
	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}
}

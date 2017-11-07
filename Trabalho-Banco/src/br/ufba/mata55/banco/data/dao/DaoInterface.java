package br.ufba.mata55.banco.data.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author raffaello.salvetti
 * Interface que todos os objetos de acesso a dados devem implementar
 * @param <T> Tipo da classe de acesso a dados
 * @param <Id> Tipo da chave primária
 */
public interface DaoInterface <T, Id extends Serializable> {
	
	/**
	 * Insere um objeto no banco de dados
	 * @param entity Objeto a ser inserido
	 */
	public void persist(T entity);
	
	/**
	 * Modifica um objeto existente na base de dados
	 * @param entity Objeto a ser modificado
	 */
	public void update(T entity);
	
	/**
	 * Busca um objeto por chave primaria
	 * @param id Chave primaria
	 * @return Objeto encontrado
	 */
	public T findById(Id id);
	
	/**
	 * Remove objeto da base de dados
	 * @param entity Objeto a ser removido
	 */
	public void delete(T entity);
	
	/**
	 * Retorna uma lista com todos os objetos na base de dados
	 * @return Lista de objetos na base de dados
	 */
	public List<T> findAll();
	
	/**
	 * Remove todos os objetos da base de dados
	 */
	public void deleteAll();
}

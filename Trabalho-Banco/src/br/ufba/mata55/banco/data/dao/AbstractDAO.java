package br.ufba.mata55.banco.data.dao;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufba.mata55.banco.annotation.Campo;
import br.ufba.mata55.banco.annotation.MapeadoPor;
import br.ufba.mata55.banco.annotation.Tabela;
import br.ufba.mata55.banco.data.po.AbstractPO;

public abstract class AbstractDAO<T extends AbstractPO, I extends Serializable> {
	private Connection conn;
	private Class<T> poClass;

	public AbstractDAO(Class<T> poClass) {
		this.poClass = poClass;
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean openConnection() {
		try {
			if(conn == null || conn.isClosed())
				conn = DriverManager.getConnection("jdbc:h2:~/Desktop/MATA55-BANCO/database/banco", "sa", "");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean closeConnection() {
		try {
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public T save(T entity) {
		openConnection();
		Method chavePrimaira = null;
		Method[] methods = poClass.getDeclaredMethods();
		List<String> keys = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		for (Method method : methods) {
			Campo c = method.getAnnotation(Campo.class);
			if(c != null && !c.chavePrimaria()) {
				keys.add(c.nome());
				try {
					Method m = null;
					if(method.getGenericParameterTypes().length > 0 && (method.getGenericParameterTypes()[0] == boolean.class || method.getGenericParameterTypes()[0] == Boolean.class)){
						m = entity.getClass().getMethod(method.getName().replace("set", "is"));
					} else {
						m = entity.getClass().getMethod(method.getName().replace("set", "get"));
					}
					values.add(m.invoke(entity));
				} catch (IllegalArgumentException | NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			} else if (c != null && c.chavePrimaria()) {
				chavePrimaira = method;
			}
		}
		StringBuilder sql = new StringBuilder("INSERT INTO " + poClass.getAnnotation(Tabela.class).nome() + " ");
		sql.append("(" + join(keys.toArray(new String[keys.size()]), ", ") + ") VALUES ");
		String[] interrogationArray = new String[values.size()];
		Arrays.fill(interrogationArray, "?");
		sql.append("(" + join(interrogationArray, ", ") + ")");
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			for(int i = 1 ; i <= values.size() ; i++) {
				preparedStatement.setObject(i, values.get(i - 1));	
			}
			preparedStatement.execute();
			ResultSet primary = null;
			if(chavePrimaira != null && (primary = preparedStatement.getGeneratedKeys()) != null && primary.next())
				chavePrimaira.invoke(entity, primary.getObject(1));
		} catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	private String join(String[] values, String glue) {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0 ; i < values.length ; i++) {
			if(i == 0) {
				stringBuilder.append(values[i]);
			} else {
				stringBuilder.append(glue + values[i]);
			}
		}
		return stringBuilder.toString();
	}

	public void remove(I pk) {
		openConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM " + poClass.getAnnotation(Tabela.class).nome() + " WHERE " + getChavePrimaria() + " = ?");
			preparedStatement.setObject(1, pk);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public T getById(I pk) {
		openConnection();
		ResultSet rs;
		PreparedStatement preparedStatement;
		T t = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM " + poClass.getAnnotation(Tabela.class).nome() + " WHERE " + getChavePrimaria() + " = ?");
			preparedStatement.setObject(1, pk);
			rs = preparedStatement.executeQuery();
			t = buildList(rs).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public List<T> getAllByProperty(String[] fields, Object[] values) {
		openConnection();
		List<T> all = new ArrayList<T>();
		ResultSet rs;
		try {
			String sql = "SELECT * FROM " + poClass.getAnnotation(Tabela.class).nome() + " WHERE ";
			for(int i = 0 ; i < fields.length ; i++) {
				if(i == 0)
					sql += fields[i] + " = ? ";
				else 
					sql += "AND " + fields[i] + " = ? ";
			}
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			for(int i = 1 ; i <= values.length ; i++) {
				preparedStatement.setObject(i, values[i - 1]);
			}
			rs = preparedStatement.executeQuery();
			all = buildList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all; 
	}

	public List<T> getAll() {
		openConnection();
		List<T> all = new ArrayList<T>();
		ResultSet rs;
		try {
			rs = conn.createStatement().executeQuery("SELECT * FROM " + poClass.getAnnotation(Tabela.class).nome());
			all = buildList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all; 
	}
	
	public List<T> buildList(ResultSet resultSet) {
		List<T> all = new ArrayList<T>();
		try {
			while(resultSet.next()) {
				T generic = poClass.newInstance();
				Method[] methods = poClass.getDeclaredMethods();
				for (Method method : methods) {
					Campo c = method.getAnnotation(Campo.class);
					if(c != null) {
						method.invoke(generic, resultSet.getObject(c.nome()));
					}
				}
				for (Method method : methods) {
					MapeadoPor m = method.getAnnotation(MapeadoPor.class);
					if(m != null) {
						Object o = getObjectValueByCampoNome(generic, m.CampoOrigem());
						List<AbstractPO> poList = ((AbstractDAO)m.DAO().newInstance()).getAllByProperty(new String[]{m.CampoDestino()}, new Object[]{o});
						method.invoke(generic, poList);
					}
				}
				
				all.add(generic);
			}
		} catch (InstantiationException | IllegalAccessException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | SQLException e) {
			e.printStackTrace();
		}
		return all;
	}
	
	private Object getObjectValueByCampoNome(T po, String nome) {
		Method[] methods = po.getClass().getDeclaredMethods();
		for (Method method : methods) {
			Campo c = method.getAnnotation(Campo.class);
			if(c != null) {
				if(c.nome().equals(nome)) {
					String mName = "";
					if(method.getReturnType() == Boolean.class) {
						mName = method.getName().replace("set", "is");
					} else {
						mName = method.getName().replace("set", "get");
					}
					try {
						return po.getClass().getDeclaredMethod(mName).invoke(po);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return null;
	}
	
	private String getChavePrimaria() throws SQLException {
		String chave = null;
		Annotation[] annotations = poClass.getAnnotations();
		for (Annotation annotation : annotations) {
			if(annotation instanceof Campo && ((Campo)annotation).chavePrimaria())
				chave = ((Campo)annotation).nome();
		}
		if(chave == null)
			throw new SQLException("Nenhuma chave primaria foi definida por anotacao.");
		return chave;
	}

	public abstract String creationScript();

	public boolean createTable() throws SQLException {
		openConnection();
		boolean r = (conn.createStatement().executeUpdate(creationScript()) > 0);
		closeConnection();
		return r;
	}
}

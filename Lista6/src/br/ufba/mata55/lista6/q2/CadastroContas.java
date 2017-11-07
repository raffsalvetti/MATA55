package br.ufba.mata55.lista6.q2;

import java.util.ArrayList;
import java.util.List;

public class CadastroContas {
	
	private List<Object> contas = new ArrayList<Object>();
	
	public boolean inserir(Object conta) throws ExcecaoRepositorio, ExcecaoElementoJaExistente {
		boolean r = contas.add(conta);
		
		if(contas.indexOf(conta) >= 0)
			throw new ExcecaoElementoJaExistente();
		
		if(!r)
			throw new ExcecaoRepositorio();
		return r;
	}
	
	public int buscar(Object conta) throws ExcecaoElementoInexistente {
		int r = contas.indexOf(conta);
		if(r < 0)
			throw new ExcecaoElementoInexistente();
		
		return r;
	}
}

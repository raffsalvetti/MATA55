package br.ufba.mata55.lista6.q2;

public class ClasseBasicaDeNegocio {
	private Object o1,o2,o3,o4;

	public void setO1(ClasseBasicaDeNegocioValidavel o1) throws ExcecaoDadoInvalido {
		if(!o1.isValid())
			throw new ExcecaoDadoInvalido();
		this.o1 = o1;
	}

	public void setO2(ClasseBasicaDeNegocioValidavel o2) throws ExcecaoDadoInvalido {
		if(!o2.isValid())
			throw new ExcecaoDadoInvalido();
		this.o2 = o2;
	}

	public void setO3(ClasseBasicaDeNegocioValidavel o3) throws ExcecaoDadoInvalido {
		if(!o3.isValid())
			throw new ExcecaoDadoInvalido();
		this.o3 = o3;
	}

	public void setO4(ClasseBasicaDeNegocioValidavel o4) throws ExcecaoDadoInvalido {
		if(!o4.isValid())
			throw new ExcecaoDadoInvalido();
		this.o4 = o4;
	}
	
}

package br.ufba.mata55.lista6.q1;

public class Divisao {
	public double calcular(double a, double b) throws ExcecaoDivisaoPorZero {
		if(b == 0)
			throw new ExcecaoDivisaoPorZero();
		return a / b;
	}
}

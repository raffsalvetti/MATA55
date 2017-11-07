package br.ufba.mata55.lista6.q1;

public class AplicacaoDivisao {
	public AplicacaoDivisao(double a, double b) {
		try {
			System.out.println(a + " / " + b + " = " + new Divisao().calcular(a, b));
		} catch (ExcecaoDivisaoPorZero e) {
			System.out.println(e.getMessage());
		}
	}
}

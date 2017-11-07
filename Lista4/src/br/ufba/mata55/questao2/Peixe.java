package br.ufba.mata55.questao2;

public class Peixe extends Animal {

	private boolean barbatana, cauda;
	private static int CRIADOS = 0;
	
	public Peixe(String nome, double comprimento, double velocidade, boolean barbatana, boolean cauda) {
		super(nome, "cinzento", "mar", comprimento, velocidade, 0);
		this.barbatana = barbatana;
		this.cauda = cauda;
		CRIADOS++;
	}
	
	public Peixe(){
		super();
		System.out.println("TEM BARBATANA? [SIM/NAO]");
		alteraBarbatana(lerEntrada().equalsIgnoreCase("SIM"));
		System.out.println("TEM CAUDA? [SIM/NAO]");
		alteraCauda(lerEntrada().equalsIgnoreCase("SIM"));
		CRIADOS++;
	}
	
	public static int NASCIMENTOS() {
		return CRIADOS;
	}

	public boolean isBarbatana() {
		return barbatana;
	}

	public void alteraBarbatana(boolean barbatana) {
		this.barbatana = barbatana;
	}

	public boolean isCauda() {
		return cauda;
	}

	public void alteraCauda(boolean cauda) {
		this.cauda = cauda;
	}
	
	public void dados() {
		super.dados();
		System.out.println(isBarbatana()?"Tem barbatanas":"Nao tem barbatanas");
		System.out.println(isCauda()?"Tem cauda":"Nao tem cauda");
	}
}

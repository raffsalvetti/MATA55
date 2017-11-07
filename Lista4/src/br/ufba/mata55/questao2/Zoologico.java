package br.ufba.mata55.questao2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;

public class Zoologico {
	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	private List<Animal> animais = new ArrayList<Animal>(); 
	
	public void cadastrarAnimal() {
		String tipoAnimal = null;
		Animal animal = null;
		System.out.println("CADASTRO DE ANIMAL");
		System.out.println("TIPO DE ANIMAL [M = MAMIFERO, P = PEIXE]: ");
		try {
			tipoAnimal = bufferedReader.readLine(); 
			if(tipoAnimal.trim().equalsIgnoreCase("M")) {
				animal = new Mamifero();
			} else if(tipoAnimal.trim().equalsIgnoreCase("P")) {
				animal = new Peixe();
			} else {
				System.out.println("TIPO DE ANIMAL NAO RECONHECIDO");
			}
		} catch (Exception e) {
			System.out.println("Erro lendo dados");
		}
		if(animal != null)
			animais.add(animal);
	}
	
	public void exibirAnimais() {
		if(animais.isEmpty()) {
			System.out.println("NAO EXISTEM ANIMAIS CADASTRADOS...");
			cadastrarAnimal();
			return;
		}
		
		for (Animal animal : animais) {
			if(animal instanceof Mamifero) {
				((Mamifero)animal).dados();
			} else if(animal instanceof Peixe) {
				((Peixe)animal).dados();
			} else {
				System.out.println("ANIMAL DESCONHECIDO!");
			}
			System.out.println(" -------------------------------- ");
		}
	}
	
	public void exibirEstatisticas() {
		System.out.println("ANIMAIS: " + Animal.NASCIMENTOS());
		System.out.println("MAMIFEROS: " + Mamifero.NASCIMENTOS());
		System.out.println("PEIXES: " + Peixe.NASCIMENTOS());
	}
	
	public Zoologico() {
		int op = -1;
		while(op != 0) {
			clear();
			System.out.println("0 - SAIR");
			System.out.println("1 - CADASTRAR ANIMAL");
			System.out.println("2 - EXIBIR ANIMAIS CADASTRADOS");
			System.out.println("3 - EXIBIR ESTATISTICAS DAS CLASSES");
			try {
				op = Integer.parseInt(bufferedReader.readLine());
				
				switch (op) {
				case 0:
					System.out.println("SAINDO... FUI!");
					System.exit(0);
					pressToContinue();
					break;
				case 1:
					cadastrarAnimal();
					pressToContinue();
					break;
				case 2:
					exibirAnimais();
					pressToContinue();
					break;
				case 3:
					exibirEstatisticas();
					pressToContinue();
					break;
				default:
					System.out.println("OPCAO INVALIDA");
					pressToContinue();
					break;
				}
				
			} catch (Exception e) {
				System.out.println("Erro lendo opcao!");
				op = -1;
			}
		}
	}

	public static void main(String[] args) {
		new Zoologico();
	}
	
	public void pressToContinue(){
		System.out.println("Pressione enter para continuar...");
		try {
			bufferedReader.readLine();
		} catch (IOException e) {}
	}
	
	private void clear() {
		final String operatingSystem = System.getProperty("os.name").toLowerCase();
		try {
			if (operatingSystem.contains("windows")) {
				new ProcessBuilder("cmd", "/c", "cls").start().waitFor();
			}
			else {
				new ProcessBuilder("/bin/sh", "-c", "clear").start().waitFor();
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}

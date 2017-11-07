package br.ufba.mata55.lista3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum OpcoesMenu {
	DESCONHECIDO(0, "Desconhecido"),
	INSERIR(1, "Inserir nome na lista"),
	CONSULTAR(2, "Consultar nome"),
	EXCLUIR(3, "Excluir um nome da lista"),
	EXIBIR(4, "Exibir nomes"),
	EXIBIR_ORDENADO(5, "Exibir nomes (ordenado)"),
	FINALIZAR(6, "Sair do programa")
	;	

	private OpcoesMenu(int codigo, String texto) {
		this.codigo = codigo;
		this.texto = texto;
	}
	
	private int codigo;
	private String texto;
	
	public String getTexto(){
		return texto;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public static OpcoesMenu getByCodigo(String codigo) {
		try{
			for (OpcoesMenu menu : OpcoesMenu.values()) {
				if(menu.getCodigo() == Integer.parseInt(codigo))
					return menu;
			}
		}catch(Exception ex){
			return OpcoesMenu.DESCONHECIDO;
		}
		return OpcoesMenu.DESCONHECIDO;
	}
}

public class Lista3Main {
	
	private List<String> nomes = new ArrayList<String>();
	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	
	public Lista3Main() {
		OpcoesMenu op = OpcoesMenu.DESCONHECIDO;
		try {
			while((op = printMenu()) != null) {
				switch (op) {
				case CONSULTAR:
					clear();
					System.out.print("Digite um nome: ");
					verificarNome(bufferedReader.readLine());
					pressToContinue();
					break;
				case EXIBIR:
					clear();
					exibirNomes();
					pressToContinue();
					break;
				case EXIBIR_ORDENADO:
					clear();
					exibirNomesOrdenados();
					pressToContinue();
					break;
				case FINALIZAR:
					clear();
					System.out.println("Saindo...");
					System.exit(0);
					break;
				case EXCLUIR:
					clear();
					System.out.print("Digite um nome: ");
					excluirNome(bufferedReader.readLine());
					pressToContinue();
					break;
				case INSERIR:
					clear();
					System.out.print("Digite um nome: ");
					inserirNome(bufferedReader.readLine());
					pressToContinue();
					break;
				default:
					System.out.println("Comando desconhecido, tente novamente");
					pressToContinue();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Erro lendo opcoes");
		}
	}
	
	public void inserirNome(String nome) {
		if(!_verificarNome(nome))
			nomes.add(nome);
		else
			System.out.println("O nome ja esta na lista!");
	}
	
	private boolean _verificarNome(String nome) {
		return (nomes.indexOf(nome) >= 0);
	}
	
	public void verificarNome(String nome) {
		System.out.println("O nome " + (!_verificarNome(nome)?"NAO":"") + " pertence a lista!!!");
	}
	
	public void exibirNomes() {
		_exibirNomes(false);
	}
	
	public void exibirNomesOrdenados() {
		_exibirNomes(true);
	}
	
	@SuppressWarnings("unchecked")
	private void _exibirNomes(boolean ordenar) {
		if(nomes.isEmpty()) {
			System.out.println("A Lista Esta Vazia!");
		} else {
			List<String> copy = (List<String>) ((ArrayList<String>)nomes).clone();
			if(ordenar)
				Collections.sort(copy, String.CASE_INSENSITIVE_ORDER);
			
			for (String nome : copy) {
				System.out.println(nome);
			}
		}
	}
	
	public void excluirNome(String nome) {
		System.out.println(nomes.remove(nome)?"Nome Excluido com Sucesso!!!":"Nome NAO Encontrado na Lista!");
	}
	
	public void pressToContinue(){
		System.out.println("Pressione enter para continuar...");
		try {
			bufferedReader.readLine();
		} catch (IOException e) {}
	}
	
	private void clear(){
		final String operatingSystem = System.getProperty("os.name").toLowerCase();
		try {
			if (operatingSystem.contains("windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}
			else {
				new ProcessBuilder("/bin/sh", "-c", "clear").inheritIO().start().waitFor();
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	private OpcoesMenu printMenu() throws IOException {
		clear();
		for (OpcoesMenu menu : OpcoesMenu.values()) {
			if(menu != OpcoesMenu.DESCONHECIDO)
				System.out.println(String.format("[%d] %s", menu.getCodigo(), menu.getTexto()));
		}
		System.out.println("Escolha uma opcao");
		
		return OpcoesMenu.getByCodigo(bufferedReader.readLine());
	}

	public static void main(String args[]) {
		new Lista3Main();
	}
}

package br.ufba.mata55.questao1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Contas {
	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	private List<ContaBancaria> contas = new ArrayList<ContaBancaria>();
	private ContaBancaria contaSelecionada = null;
	
	//questao a
	public void incluirConta() {
		String tipoConta = ""; 
		ContaBancaria conta = null;
		System.out.println("INCLUSAO DE CONTA: ");
		System.out.println("TIPO DE CONTA [1 = POUPANCA; 2 = ESPECIAL]");
		try {
			tipoConta = bufferedReader.readLine(); 
			if(tipoConta.trim().equals("1")) {
				System.out.print("DIGITE UM NUMERO PARA A CONTA:");
				int numero = Integer.parseInt(bufferedReader.readLine());
				System.out.print("DIGITE O NOME DO DONO DA CONTA:");
				String nome = bufferedReader.readLine();
				conta = new ContaPoupanca(numero, nome);
			} else if(tipoConta.trim().equals("2")) {
				System.out.print("DIGITE UM NUMERO PARA A CONTA:");
				int numero = Integer.parseInt(bufferedReader.readLine());
				System.out.print("DIGITE O NOME DO DONO DA CONTA:");
				String nome = bufferedReader.readLine();
				System.out.print("DIGITE O LIMITE DA CONTA:");
				double limite = Double.parseDouble(bufferedReader.readLine());
				conta = new ContaEspecial(numero, nome, limite);
			} else {
				System.out.println("TIPO DE CONTA NAO RECONHECIDO");
			}
		} catch (Exception e) {
			System.out.println("Erro lendo dados");
		}
		if(conta != null)
			contas.add(conta);
	}
	
	//questao b
	public void sacarValor(double valor) {
		validarContaSelecionada();
		System.out.println("O saque " + (contaSelecionada.sacar(valor) ? "":"nao ") + "foi efetuado!");
	}
	
	//questao c
	public void depositarValor(double valor) {
		validarContaSelecionada();
		System.out.println("O deposito " + (contaSelecionada.depositar(valor) ? "":"nao ") + "foi efetuado!");
	}
	
	//questao d
	public void novoSaldoContaPoupanca(double taxaRendimentoPoupanca) {
		validarContaSelecionada();
		if(contaSelecionada instanceof ContaPoupanca) {
			((ContaPoupanca)contaSelecionada).calculaNovoSaldo(taxaRendimentoPoupanca);
		} else {
			System.out.println("A CONTA SELECIONADA NAO EH DO TIPO POUPANCA!!");
		}
	}
	
	//questao e
	public void exibirInformacoesConta() {
		validarContaSelecionada();
		if(contaSelecionada instanceof ContaPoupanca)
			((ContaPoupanca)contaSelecionada).exibirInformacoes();
		else if(contaSelecionada instanceof ContaEspecial)
			((ContaEspecial)contaSelecionada).exibirInformacoes();
	}
	
	public void validarContaSelecionada() {
		if(contaSelecionada == null){
			System.out.println("NENHUMA CONTA SELECIONADA");
			selecionarConta();
		}
	}
	
	public void selecionarConta() {
		if(contas.isEmpty()) {
			System.out.println("NENHUMA CONTA CADASTRADA!");
			incluirConta();
		}
		
		System.out.println("SELECIONE UMA CONTA PELO NUMERO: ");
		for (ContaBancaria contaBancaria : contas) {
			String tipo = "";
			if(contaBancaria instanceof ContaPoupanca)
				tipo = "POUPANCA";
			else if(contaBancaria instanceof ContaEspecial)
				tipo = "ESPECIAL";
			else
				tipo = "DESCONHECIDO";
			System.out.println(String.format("%d - %s : TIPO:%s", contaBancaria.getNumero(), contaBancaria.getCliente(), tipo));
		}
		try {
			int numero = Integer.parseInt(bufferedReader.readLine());
			for (ContaBancaria contaBancaria : contas) {
				if(contaBancaria.getNumero() == numero) {
					contaSelecionada = contaBancaria;
					System.out.println("NOVA CONTA SELECIONADA!");
					return;
				}
			}
			System.out.println("CONTA NAO ENCONTRADA");
		} catch (Exception e) {
			System.out.println("Erro lendo conta");
		}
	}
	
	public Contas() {
		int op = -1;
		while(op != 0) {
			clear();
			System.out.println("0 - SAIR");
			System.out.println("1 - INCLUIR CONTA");
			System.out.println("2 - SELECIONAR CONTA SELECIONADA");
			System.out.println("3 - SACAR VALOR DA CONTA SELECIONADA");
			System.out.println("4 - DEPOSITAR VALOR NA CONTA SELECIONADA");
			System.out.println("5 - CORRIGIR RENDIMENTO DE CONTA POUPACA");
			System.out.println("6 - EXIBIR INFORMACOES DA CONTA SELECIONADA");
			System.out.println("");
			System.out.println("CONTA SELECIONADA: " + (contaSelecionada!=null?contaSelecionada:"Nenhuma"));
			try {
				op = Integer.parseInt(bufferedReader.readLine());
				
				switch (op) {
				case 0:
					System.out.println("SAINDO... FUI!");
					System.exit(0);
					pressToContinue();
					break;
				case 1:
					incluirConta();
					pressToContinue();
					break;
				case 2:
					selecionarConta();
					pressToContinue();
					break;
				case 3:
					System.out.print("DIGITE O VALOR DO SAQUE: ");
					sacarValor(Double.parseDouble(bufferedReader.readLine()));
					pressToContinue();
					break;
				case 4:
					System.out.print("DIGITE O VALOR DO DEPOSITO: ");
					depositarValor(Double.parseDouble(bufferedReader.readLine()));
					pressToContinue();
					break;
				case 5:
					System.out.print("DIGITE O VALOR DO NOVO RENDIMENTO DE POUPANCA: ");
					novoSaldoContaPoupanca(Double.parseDouble(bufferedReader.readLine()));
					pressToContinue();
					break;
				case 6:
					exibirInformacoesConta();
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
		new Contas();
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

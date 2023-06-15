package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.Controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
	
	public static Scanner leia = new Scanner(System.in);
	
	public static void main(String[] args) {
		
	
		ContaController contas = new ContaController();

		Scanner leia = new Scanner(System.in);
		
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		System.out.println("\nCriar Contas\n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "Joao Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 1, "Mariana Silva", 4000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Silva", 8000f, 15);
		contas.cadastrar(cp2);
		
		contas.listarTodas();
		
		
		

		
		// Teste da Classe Conta Corrente
		ContaCorrente cc11 = new ContaCorrente(1, 123, 1, "José da Silva", 0.0f, 1000.0f);
		cc11.visualizar();
		cc11.sacar(12000.0f);
		cc11.visualizar();
		cc11.depositar(5000.0f);
		cc11.visualizar();
		
        
		// Teste da Classe Conta Poupança
		ContaPoupanca cp11 = new ContaPoupanca(2, 123, 2, "Maria dos Santos", 100000.0f, 15);
		cp11.visualizar();
        cp11.sacar(1000.0f);
		cp11.visualizar();
		cp11.depositar(5000.0f);
		cp11.visualizar();
				
		while (true) {
			
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND + "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     "); 
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);
			
			try {
				opcao = leia.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = 0;
				
			}							
			
			if(opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
				leia.close();
				System.exit(0);
			}
			
			switch(opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta \n\n");
				
				System.out.println("Digite o Numero da Agencia: ");
				agencia = leia.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				do {
					System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();
				}while(tipo <1 && tipo >2);
				
				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();
				
				switch(tipo){
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversário da Conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}	
				
				}
			   	keyPress();
                 break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
                 break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n ");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				contas.procurarPorNumero(numero);
				
				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
				
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				if (contas.buscarNaCollection(numero) != null) {
					
					System.out.println("Digite o numero da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					
					System.out.println("Digite o saldo da conta (R$): ");
					saldo = leia.nextFloat();
					
					tipo = contas.retornaTipo(numero);
					
					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o Limite de Crédito (R$): ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversario da Conta ");
						aniversario = leia.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
					System.out.println("Tipo de conta Inválido!");
					}
					}					
					
				}else
					System.out.println("\nConta não encontrada!");
									
				keyPress();
                 break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar Conta\\n\\n");
				
				System.out.println("Digite o número da conta");
				numero = leia.nextInt();
				
				contas.deletar(numero);
				
				keyPress();
                 break;
			case 6:
				System.out.println(Cores.TEXT_WHITE + "Saque\\n\\n");
				
				System.out.println("Digite o número da conta");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o Valor do Saque (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.sacar(numero, valor);
				
				keyPress();
				break;
             case 7:
				System.out.println(Cores.TEXT_WHITE + "Depósito\\n\\n");
				
				System.out.println("Digite o número da conta");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o Valor do Depósito (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.depositar(numero, valor);
				
				keyPress();
				break;
             case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\\n\\n");
				
				System.out.println("Digite o número da conta de Origem");
				numero = leia.nextInt();
				System.out.println("Digite o número da conta de Destino");
				numeroDestino = leia.nextInt();
				
				do {
					System.out.println("Digite o Valor da Transfência(R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_WHITE + "Opção Inválida\\n\\n");

                 break;
			}
		}
	}
			
		public static void keyPress() {
			
			try {
				
				System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
				System.in.read();
				
			} catch (IOException e) {
				
				System.out.println("Você pressionou a tecla diferente de enter!");
			}
		}
		
	}

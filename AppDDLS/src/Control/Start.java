package Control;

import java.util.Scanner;

import DAO.UserDao;

public class Start extends Metods{
	public static void main(String[] args) {
		//----------------------------------------------------------------------------------------------

		// TRATAR DOS COMANDOS DO BASH
		
		//Modelo da entrada: ddls command -parameter registryUser
		//                   Password: ********
		
			switch (args[1].toUpperCase()) {
				case "LOGIN":
					Scanner scan = new Scanner(System.in);
					String scn = new String();	
					
					switch (args[2].toUpperCase()) {
						case "-U":
							UserDao ud = new UserDao();
							
							if (args[3].length() != 12 || args[3].length() != 11 || args[3].length() != 7) {
								System.out.println("ERRO: MATRICULA INVALIDA!");
							} else {
								ud.connect();
								if (ud.verify(args[3])) {
									System.out.print("Password: ");
									//Esconder senha
									scn = scan.nextLine();
									if (scn != ud.search()) {
										//Autentica usuario e senha atraves da inteface Autentication
										//Entra no ambiente do programa
									} else {
										System.out.println("Erro: Senha invalida!");
									}
								} else {
									System.out.println("Erro: Matricula invalida!");
								}
							}
							ud.disconnect();
							break;
							
						case "-A":
							
							break;
					}
					break;
			
				case "LOGGOUT":
					break;

				default:
					break;
			}
		
		//----------------------------------------------------------------------------------------------
			
			
		//----------------------------------------------------------------------------------------------
		
		// SE O USUARIO LOGGAR COMO ADMIN
			
			Scanner scan = new Scanner(System.in);
			String scn = scan.nextLine().toUpperCase();
			
			switch (scn) {
				case "ADDUSER":
					addUser();
					break;
				case "ADDADMIN":
					addAdmin();
					break;
				case "DELUSER":
					delUser();
					break;
				default:
					break;
			}
			
			scan.close();
			
		//----------------------------------------------------------------------------------------------
	}
}
package Control;

import java.util.Scanner;

import DAO.AdminDao;
import DAO.UserDao;

public class Start extends Metods{
	/*public Start() {
		System.out.println("************************ DDLS ***********************");
		System.out.println("-----------------------------------------------------");
		System.out.println("Utilize a seguinte linha de comando para fazer login:");
		System.out.println("ddls comando -parametro suaMatricula"                 );
		System.out.println("-----------------------------------------------------");
		System.out.println("Pos login, os comandos seguirao o seguinte padrao:"   );
		System.out.println("ddls comando -parametro"                              );
	}*/
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//----------------------------------------------------------------------------------------------

		// TRATAR DOS COMANDOS DO BASH
		
			switch (args[1].toUpperCase()) {
				case "LOGIN":
					Scanner scan = new Scanner(System.in);
					String scn = new String();	
					
					switch (args[2].toUpperCase()) {
						case "-U":
							UserDao ud = new UserDao();
							
							if (args[3].length() == 12 || args[3].length() == 11 || args[3].length() == 7) {
								ud.connect();
								if (ud.verify(args[3])) {
									System.out.print("Password: ");
									//Esconder senha
									scn = scan.nextLine();
									if (true /*scn != ud.search() search precisa retornar senha ou as infos do user*/) {
										//Entra no ambiente do user
									} else {
										System.out.println("Erro: Senha invalida!");
									}
								} else {
									System.out.println("Erro: Matricula nao cadastrada!");
								}
							} else {
								System.out.println("Erro: Matricula invalida!");
							}
							ud.disconnect();
							break;
							
						case "-A":
							AdminDao ad = new AdminDao();
							
							if (args[3].length() == 12 || args[3].length() == 11 || args[3].length() == 7) {
								ad.connect();
								if (ad.verify(args[3])) {
									System.out.print("Password: ");
									//Esconder senha
									scn = scan.nextLine();
									if (true /*scn != ad.search() search precisa retornar senha ou as infos do admin*/) {
										//Entra no ambiente do admin
									} else {
										System.out.println("Erro: Senha invalida!");
									}
								} else {
									System.out.println("Erro: Matricula nao cadastrada!");
								}
							} else {
								System.out.println("Erro: Matricula invalida!");
							}
							ad.disconnect();
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
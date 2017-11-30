package Control;

import java.util.Scanner;

public class Start extends Metods{
	public static void main(String[] args) {
		/*switch (args[1].toUpperCase()) {
		
		//Modelo da entrada: command -parameter registryUser
		//                   Password: ********
		
		case "LOGIN":
			Scanner scan = new Scanner(System.in);
			String scn = new String();	
			UserDao ad = new UserDao();
					
			//lembrar do parametro args[2]
			
			if (args[3].length() > 12 || args[3].length() < 7) {
					System.out.println("Erro: Matrícula inválida!");
			} else {
				ad.connect();
				String retsrch = ad.search(args[3]);
				if (retsrch == /*RESPOSTAPOSITIVADAFUNCAO/ null) {
					System.out.print("Password: ");
					//Esconder senha
					scn = scan.nextLine();
					if (scn != ad.search(bummand -pascaSenhaReferente)) {
						//Autentica usuario e senha atraves da inteface Autentication
						//Entra no ambiente do programa
					} else {
						System.out.println("Erro: Senha inválida!");
					}
				} else {
					System.out.println("Erro: Matrícula inválida!");
				}
			}
			ad.disconnect();
			break;
			
		case "LOGGOUT":
			break;

		default:
			break;
		}*/
		
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
		
		//----------------------------------------------------------------------------------------------
		
		scan.close();
	}
}
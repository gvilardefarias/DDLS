package Control;

import java.util.Scanner;

import Bean.Admin;
import Bean.User;
import DAO.AdminDao;
import DAO.UserDao;

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
			
			if (args[3].length() > 12 || args[3].length() < 11) {
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
			
			
		UserDao ad = new UserDao();
		ad.connect();
		
		User a = new User();

		a.setNameUser("Phulano");
		a.setRegistryUser("201610010012");
		a.setPassUser(encrypt("phulano12345"));
		a.setEmailUser("phulano@gmail.com");
		a.setCourseUser("Engenharia Da Computação");
		a.setClassUser(4);
		
		//ad.save(a); //Fazer verificação antes de inserir no BD
		ad.verify(a.getRegistryUser());
		//ad.delete(a);
		
		/*AdminDao admd = new AdminDao();
		admd.connect();
		
		Admin adm = new Admin();

		adm.setNameAdmin("Admin");
		adm.setRegistryAdmin("201610010022");
		adm.setPassAdmin(encrypt("admin12345"));
		adm.setEmailAdmin("admin@gmail.com");
		
		admd.save(adm);
		admd.verify(adm.getRegistryAdmin());
		admd.delete(adm);*/
		
		ad.disconnect();
		//admd.disconnect();
	}
}
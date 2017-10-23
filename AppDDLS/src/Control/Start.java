package Control;

import java.util.Scanner;

import Bean.Admin;
import Bean.User;
import DAO.AdminDao;
import DAO.UserDao;

public class Start extends Metods{
	public static void main(String[] args) {
		switch (args[1].toUpperCase()) {
		case "LOGIN":
			Scanner scan = new Scanner(System.in);
			UserDao ad = new UserDao();
			
			//lembrar do parametro args[2]
			
			if (args[3].length() > 12 || args[3].length() < 11) {
					System.out.println("Matrícula inválida!");
			} else {
				/*String retsrch = ad.search(args[3];
				if (retsrch == null) {
					System.out.println("Matrícula inválida!");
				} else {
					if (args[4] != ad.search(buscaSenhaReferente)) {
						
					} else {
						//Executa comando de login
					}
				}*/
			}
			break;

		default:
			break;
		}
			
			
		UserDao ad = new UserDao();
		ad.connect();
		
		User a = new User();

		a.setNameUser("Phulano");
		a.setRegistryUser("201610010012");
		a.setPassUser(encrypt("phulano12345"));
		a.setEmailUser("phulano@gmail.com");
		a.setCourseUser("Engenharia Da Computação");
		a.setClassUser(4);
		
		ad.save(a);
		ad.search(a.getRegistryUser());
		ad.delete(a);
		
		AdminDao admd = new AdminDao();
		admd.connect();
		
		Admin adm = new Admin();

		adm.setNameAdmin("Admin");
		adm.setRegistryAdmin("201610010022");
		adm.setPassAdmin(encrypt("admin12345"));
		adm.setEmailAdmin("admin@gmail.com");
		
		admd.save(adm);
		admd.search(adm.getRegistryAdmin());
		admd.delete(adm);
		
		ad.disconnect();
		admd.disconnect();
	}
}
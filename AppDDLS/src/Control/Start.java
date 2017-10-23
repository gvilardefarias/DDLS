package Control;

import java.util.Scanner;

import Bean.Admin;
import Bean.User;
import DAO.AdminDao;
import DAO.UserDao;

public class Start extends Metods{
	public static void main(String[] args) {
<<<<<<< HEAD:AppDDLS/src/Controle/Start.java
		int commands = args.length;
		System.out.println(commands);
		
		//comandos de entrada para uppercase
		
		if (args[1].toUpperCase() == "LOGIN") {
			Scanner scan = new Scanner(System.in);
			UserDao ad = new UserDao();
			
			if (args[3].length() > 12 || args[3].length() < 11) {
					System.out.println("MatrÌcula inv·lida!");
			} else {
				String retsrch = /*ad.search(args[3]*/);
				if (retsrch == null) {
					System.out.println("MatrÌcula inv·lida!");
				} else {
					if (args[4] != /*ad.search(buscaSenhaReferente)*/) {
						
					} else {
						//Executa comando de login
					}
				}
			}
			
		}
		
=======
		UserDao ad = new UserDao();
		AdminDao admd = new AdminDao();
		
		ad.connect(); admd.connect();
>>>>>>> 2e1f09bf7c48c0600a5943bd070740e69dccebc7:AppDDLS/src/Control/Start.java
		
		User a = new User();

		a.setNameUser("Phulano");
		a.setRegistryUser("201610010012");
		a.setPassUser(encrypt("phulano12345"));
		a.setEmailUser("phulano@gmail.com");
		a.setCourseUser("Engenharia Da Computa√ß√£o");
<<<<<<< HEAD:AppDDLS/src/Controle/Start.java
		a.setClassUser(4);

		UserDao ad = new UserDao();
		AdminDao admd = new AdminDao();
		
		ad.connect();
		admd.connect();
		
		ad.save(a);
		ad.search(a.getRegistryUser());
		ad.delete(a);
=======
		a.setClassUser(4);		
>>>>>>> 2e1f09bf7c48c0600a5943bd070740e69dccebc7:AppDDLS/src/Control/Start.java
		
		ad.save(a);
		ad.search(a.getRegistryUser());
		ad.delete(a);
		
		Admin adm = new Admin();

		adm.setNameAdmin("Admin");
		adm.setRegistryAdmin("201610010022");
		adm.setPassAdmin(encrypt("admin12345"));
		adm.setEmailAdmin("admin@gmail.com");
		
		admd.save(adm);
		admd.search(adm.getRegistryAdmin());
		admd.delete(adm);
<<<<<<< HEAD:AppDDLS/src/Controle/Start.java
		
		ad.disconnect();
		admd.disconnect();
=======
		
		//stmt.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		
		ad.disconnect(); admd.disconnect();
>>>>>>> 2e1f09bf7c48c0600a5943bd070740e69dccebc7:AppDDLS/src/Control/Start.java
	}
}
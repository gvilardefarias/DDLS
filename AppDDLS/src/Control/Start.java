package Control;

import Bean.Admin;
import Bean.User;
import DAO.AdminDao;
import DAO.UserDao;

public class Start extends Metods{
	public static void main(String[] args) {
		User a = new User();

		a.setNameUser("Phulano");
		a.setRegistryUser("201610010012");
		a.setPassUser(encrypt("phulano12345"));
		a.setEmailUser("phulano@gmail.com");
		a.setCourseUser("Engenharia Da Computação");
		a.setClassUser(4);

		UserDao ad = new UserDao();
		AdminDao admd = new AdminDao();
		
		ad.connect();
		admd.connect();
		
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
		
		ad.disconnect();
		admd.disconnect();
	}
}
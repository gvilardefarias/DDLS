package Bean;

import Controle.Autentication;

public class User implements Autentication{
	private String nameUser;
	private String registryUser;
	private String passUser;
	private String emailUser;
	private String courseUser;
	private int classUser;

	//@Override
	//public boolean login(String matricula, String senha) {
		
	//	return false;
	//}
	
	public String getNameUser() {
		return nameUser;
	}

	public String getPassUser() {
		return passUser;
	}

	public void setPassUser(String passUser) {
		this.passUser = passUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getRegistryUser() {
		return registryUser;
	}

	public void setRegistryUser(String registryUser) {
		this.registryUser = registryUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getCourseUser() {
		return courseUser;
	}

	public void setCourseUser(String courseUser) {
		this.courseUser = courseUser;
	}

	public int getClassUser() {
		return classUser;
	}

	public void setClassUser(int classUser) {
		this.classUser = classUser;
	}
}
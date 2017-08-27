package Bean;

import Controle.Autentication;

public class Admin implements Autentication{
	private String nameAdmin;
	private String registryAdmin;
	private String emailAdmin;
	private String passAdmin;

	public String getNameAdmin() {
		return nameAdmin;
	}

	public void setNameAdmin(String nameAdmin) {
		this.nameAdmin = nameAdmin;
	}

	public String getRegistryAdmin() {
		return registryAdmin;
	}

	public void setRegistryAdmin(String registryAdmin) {
		this.registryAdmin = registryAdmin;
	}

	public String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public String getPassAdmin() {
		return passAdmin;
	}

	public void setPassAdmin(String passAdmin) {
		this.passAdmin = passAdmin;
	}
}
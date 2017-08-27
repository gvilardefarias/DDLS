package Bean;

import Controle.Autenticacao;

public class Admin implements Autenticacao{
	private String nome;
	private String matricula_admin;
	private String email;
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatriculaAdmin() {
		return matricula_admin;
	}

	public void setMatriculaAdmin(String matricula_admin) {
		this.matricula_admin = matricula_admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
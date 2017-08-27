package Bean;

import Controle.Autenticacao;

public class Aluno implements Autenticacao{
	private String nome;
	private String matricula_aluno;
	private String senha;
	private String email;
	private String curso;
	private int periodo;

	//@Override
	//public boolean login(String matricula, String senha) {
		
	//	return false;
	//}
	
	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatriculaAluno() {
		return matricula_aluno;
	}

	public void setMatriculaAluno(String matricula_aluno) {
		this.matricula_aluno = matricula_aluno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
}
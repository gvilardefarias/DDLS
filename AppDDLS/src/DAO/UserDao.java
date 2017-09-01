package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Bean.User;

public class UserDao {
	static Connection conn;

	public void conectar() {
		Conector.getConnection();
	}

	public void desconectar() {
		Conector.close();
	}
	
	public void buscar(String matricula_aluno){
		String sql = "SELECT * FROM aluno_tb WHERE matricula_aluno=\"" + matricula_aluno + "\";";
		
		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);

			ps.execute();
			System.out.println("- Busca realizada!\n");
			//return ps.execute();
		} catch (SQLException e) {
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA ALUNO_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void salvar(User a) {
		String sql = "INSERT INTO aluno_tb (nome, matricula_aluno, senha, email, curso, periodo) VALUES (?,?,?,?,?,?);";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);
			
			ps.setString(1, a.getNameUser());
			ps.setString(2, a.getRegistryUser());
			ps.setString(3, a.getPassUser());
			ps.setString(4, a.getEmailUser());
			ps.setString(5, a.getCourseUser());
			ps.setInt(6, a.getClassUser());

			ps.execute();
			System.out.println("- Linha inserida!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA ALUNO_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	public void apagar(User a) {
		String sql = "DELETE FROM aluno_tb WHERE matricula_aluno = " + a.getRegistryUser() + ";";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);
			ps.execute();
			
			System.out.println("- Linha apagada!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO DELETAR LINHAS DA TABELA ALUNO_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}
}
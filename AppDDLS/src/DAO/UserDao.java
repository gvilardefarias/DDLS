package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Bean.User;

public class UserDao {
	static Connection con;

	public void connect() {
		Conector.getConnection();
	}

	public void disconnect() {
		Conector.close();
	}
	
	public void search(String registryUser){
		String sql = "SELECT * FROM user_tb WHERE registryUser = \"" + registryUser + "\";";
		
		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);

			ps.execute();
			System.out.println("- Busca realizada!\n");
			//return ps.execute();
		} catch (SQLException e) {
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA USER_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void save(User a) {
		String sql = "INSERT INTO user_tb (nameUser, registryUser, passUser, emailUser, courseUser, classUser) VALUES (?,?,?,?,?,?);";

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
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA USER_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(User a) {
		String sql = "DELETE FROM user_tb WHERE registryUser = \"" + a.getRegistryUser() + "\";";

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
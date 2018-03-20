package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public boolean verify(String registryUser){
		String sql = "SELECT * FROM user_tb WHERE registryUser = \"" + registryUser + "\";";
		
		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
            String[] arr = null;
            
            try {
            	while (rs.next()) {
                	arr = rs.getString("registryUser").split("\n");
                }
            } catch (java.lang.NullPointerException e) {
            	System.out.println("\nERRO: " + e + "\n");
            	arr = null;
            }
            
			if (arr != null) {
				System.out.println("\n- Busca realizada! --> True\n");
				return true;
			} else {
				System.out.println("\n- Busca realizada! --> False\n");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("- ERRO AO BUSCAR NA TABELA USER_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void save(User a) {
		String sql = "INSERT INTO user_tb (nameUser, registryUser, passUser, emailUser, courseUser, classUser, numberGp) VALUES (?,?,?,?,?,?,?);";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);
			
			ps.setString(1, a.getNameUser());
			ps.setString(2, a.getRegistryUser());
			ps.setString(3, a.getPassUser());
			ps.setString(4, a.getEmailUser());
			ps.setString(5, a.getCourseUser());
			ps.setString(6, a.getClassUser());
			ps.setInt(7, a.getNumberGp());

			ps.execute();
			System.out.println("- Linha inserida!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA USER_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(String registryUser) {
		String sql = "DELETE FROM user_tb WHERE registryUser = \"" + registryUser + "\";";

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
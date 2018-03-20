package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Bean.Admin;

public class AdminDao {
	static Connection con;

	public void connect() {
		Conector.getConnection();
	}

	public void disconnect() {
		Conector.close();
	}

	public boolean verify(String registryAdmin){
		String sql = "SELECT * FROM admin_tb WHERE registryAdmin = \"" + registryAdmin + "\";";
		
		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
            String[] arr = null;
            
            try {
            	while (rs.next()) {
                	arr = rs.getString("registryAdmin").split("\n");
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
			System.out.println("- ERRO AO BUSCAR NA TABELA ADMIN_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	public void save(Admin a) {
		String sql = "INSERT INTO admin_tb (nameAdmin, registryAdmin, passAdmin, emailAdmin) VALUES (?,?,?,?);";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);

			ps.setString(1, a.getNameAdmin());
			ps.setString(2, a.getRegistryAdmin());
			ps.setString(3, a.getPassAdmin());
			ps.setString(4, a.getEmailAdmin());

			ps.execute();
			System.out.println("- Linha inserida!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA ADMIN_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(String registryAdmin) {
		String sql = "DELETE FROM admin_tb WHERE registryAdmin = \"" + registryAdmin + "\";";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);
			ps.execute();
			
			System.out.println("- Linha apagada!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO DELETAR LINHAS DA TABELA ADMIN_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}
}
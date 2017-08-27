package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Bean.Admin;

public class AdminDao {
	static Connection conn;

	public void conectar() {
		Conector.getConnection();
	}

	public void desconectar() {
		Conector.close();
	}

	public void buscar(String registryAdmin) {
		String sql = "SELECT * FROM admin_tb WHERE registryAdmin=\"" + registryAdmin + "\";";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);

			ps.execute();
			System.out.println("- Busca realizada!\n");
			// return ps.execute();
		} catch (SQLException e) {
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA TB_ADMIN!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	public void salvar(Admin a) {
		String sql = "INSERT INTO admin_tb (name, registryAdmin, passAdmin, email) VALUES (?,?,?,?);";

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

	public void apagar(Admin a) {
		String sql = "DELETE FROM admin_tb WHERE matricula_admin = " + a.getRegistryAdmin() + ";";

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
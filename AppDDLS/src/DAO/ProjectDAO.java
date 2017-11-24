package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Bean.Project;

public class ProjectDAO {
	static Connection con;

	public void connect() {
		Conector.getConnection();
	}

	public void disconnect() {
		Conector.close();
	}

	/*public boolean verify(String registryAdmin) {
		String sql = "SELECT * FROM admin_tb WHERE registryAdmin = \"" + registryAdmin + "\";";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);

			ps.execute();
			System.out.println("- Busca realizada!\n");
			// return ps.execute();
		} catch (SQLException e) {
			System.out.println("- ERRO AO BUSCAR NA TABELA PROJECT_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}*/

	public void save(Project p) {
		String sql = "INSERT INTO project_tb (projectId, title, referenceFiles, testbId, registryAdmin) VALUES (?,?,?,?,?);";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);

			ps.setString(2, p.getTitle());
			ps.setString(3, p.getReferenceFiles());
			ps.setInt(4, p.getTestbId());
			ps.setString(5, p.getRegistryAdmin());

			ps.execute();
			System.out.println("- Linha inserida!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA PROJECT_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(Project p) {
		String sql = "DELETE FROM project_tb WHERE projectId = \"" + p.getProjectId() + "\";";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);
			ps.execute();

			System.out.println("- Linha apagada!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO DELETAR LINHAS DA TABELA PROJECT_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}
}
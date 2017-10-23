package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Bean.TestBench;

public class TestBenchDao {
	static Connection con;

	public void connect() {
		Conector.getConnection();
	}

	public void disconnect() {
		Conector.close();
	}

	/*public void search(String registryAdmin) {
		String sql = "SELECT * FROM admin_tb WHERE registryAdmin = \"" + registryAdmin + "\";";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);

			ps.execute();
			System.out.println("- Busca realizada!\n");
			// return ps.execute();
		} catch (SQLException e) {
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA ADMIN_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}*/

	public void save(TestBench tb) {
		String sql = "INSERT INTO testbench_tb (testbId, referenceFiles, testbDateServiceUp, registryAdmin) VALUES (?,?,now(),?);";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);

			ps.setString(2, tb.getReferenceFiles());
			ps.setString(4, tb.getRegistryAdmin());

			ps.execute();
			System.out.println("- Linha inserida!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA TESTBENCH_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(TestBench tb) {
		String sql = "DELETE FROM testbench_tb WHERE testbId = \"" + tb.getTestbId() + "\";";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);
			ps.execute();

			System.out.println("- Linha apagada!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO DELETAR LINHAS DA TABELA TESTBENCH_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}
}
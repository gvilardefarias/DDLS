package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Bean.Dut;

public class DutDAO {
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
			System.out.println("- ERRO AO BUSCAR NA TABELA DUT_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}*/

	public void save(Dut d) {
		String sql = "INSERT INTO dut_tb (dutId, dutPath, dutDateUp, dutCheckTest, dutResultPath, registryUser, testbId) VALUES (?,?,now(),?,?,?,?);";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);

			ps.setString(2, d.getDutPath());
			ps.setBoolean(4, d.isDutCheckTest());
			ps.setString(5, d.getDutResultPath());
			ps.setString(6, d.getRegistryUser());
			ps.setInt(7, d.getTestbId());

			ps.execute();
			System.out.println("- Linha inserida!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO INSERIR LINHAS NA TABELA DUT_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(Dut d) {
		String sql = "DELETE FROM dut_tb WHERE dutId = \"" + d.getDutId() + "\";";

		try {
			PreparedStatement ps = Conector.con.prepareStatement(sql);
			ps.execute();

			System.out.println("- Linha apagada!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO DELETAR LINHAS DA TABELA DUT_TB!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}
}
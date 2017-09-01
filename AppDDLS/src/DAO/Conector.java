package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	static Connection con;

	public static Connection getConnection() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://localhost/ddls", "root", "123456");
			}
			System.out.println("- Conectado à DDLS como root!\n");
			return con;
		} catch (SQLException e) {
			System.out.println("- ERRO AO CONECTAR AO BD!!\n\n");
			throw new RuntimeException(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("- ERRO AO CONECTAR AO BD!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void close() {
		try {
			con.close();
			System.out.println("- Desconectado do BD!\n");
		} catch (SQLException e) {
			System.out.println("- ERRO AO DESCONECTAR DO BD!!\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}
}
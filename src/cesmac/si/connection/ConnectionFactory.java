package cesmac.si.connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String DRIVER_CLASS = "org.postgresql.Driver";

    
    private static final String URL = "jdbc:postgresql://";
    private static final String USER = "";
    private static final String PASS = "";

	public static Connection getConnection() {

		Connection conexao = null;
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		try {
			conexao = DriverManager.getConnection(URL, USER, PASS);
			conexao.setAutoCommit(false);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conexao;
	}
}

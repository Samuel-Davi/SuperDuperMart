package config;
import java.sql.*;

/**
 * Classe da conexão com o banco de dados
 */
public class DatabaseConnection {
	private static String url = "jdbc:mysql://localhost:3306/superdupermart";
	private static String user = "root";
	private static String pass = "0907";
	protected static Connection conexao = null;
	
	
	/**
	 * Contrutor da classe
	 */
	public DatabaseConnection() {
		if(conexao == null) conecta();
	}
	
	
	private static boolean conecta() {
		try {
			conexao = DriverManager.getConnection(url, user, pass);
			System.out.println("conexao com banco de dados com sucesso");
			return true;
		} catch (SQLException e) {System.out.println("mensagem de erro: " + e.getMessage());return false;}
	}
	
	public static boolean desconecta() {
		try {
			conexao.close();
			return true;
		} catch (SQLException e) {return false;}
	}

}

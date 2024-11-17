package dao;
import java.sql.*;
import java.util.HashMap;

import config.DatabaseConnection;
import model.Admin;

public class AdminDAO extends DatabaseConnection{
	
	HashMap<String, String> listaDeAdmin = new HashMap<String, String>();
	
	public AdminDAO() {
		getDados();
	}
	
	public boolean addAdmin(Admin a) {
		getDados();
		if(listaDeAdmin.containsKey(a.getUsername().toLowerCase())) {
			System.out.println("Usuário com esse nome já existe!"); 
			return false;
		}
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("insert into admin values (null, "+ "\""+ a.getUsername().toLowerCase()+"\"" + ", "+ "\""+ a.getSenha()+"\"" +")");
			return true;
		}catch(SQLException e) {System.out.println(e.getMessage()); return false;}
		
	}
	public boolean authenticate(Admin a){
		getDados();
		if(a.getUsername().isEmpty() || a.getSenha().isEmpty()) return false;
		if(listaDeAdmin.containsKey(a.getUsername())){
			return listaDeAdmin.get(a.getUsername()).equals(a.getSenha());
		}
		else return false;
	}

	private void getDados(){
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select * from admin");
			while (rs.next()) {
				listaDeAdmin.put(rs.getString(2), rs.getString(3));
			}
		}catch(SQLException e) {System.out.println("erro: " + e.getMessage());}
	}
}

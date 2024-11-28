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

	public boolean addUsuario(Admin a){
		getDados();
		if(listaDeAdmin.containsKey(a.getUsername().toLowerCase())) {
			System.out.println("Usuário com esse nome já existe!"); 
			return false;
		}
		try {
			String sql = "insert into admin values (?, ?, ?, default)";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, a.getUsername().toLowerCase());
			ps.setString(2, a.getSenha());
			ps.setString(3, a.getPapel());
			ps.executeUpdate();
			
			return true;
		}catch(SQLException e) {System.out.println(e.getMessage()); return false;}
	}
	public boolean authenticate(Admin a){
		getDados();
		if(a.getUsername().isEmpty() || a.getSenha().isEmpty()) return false;
		if(listaDeAdmin.containsKey(a.getUsername())){
			if(listaDeAdmin.get(a.getUsername()).equals(a.getSenha())){
				ativaUsuario(a);
				return true;
			}
			return false;
		}
		else return false;
	}

	public void ativaUsuario(Admin a){
		String sql = "update admin set ativo = true where username =?";
		String sql2 = "select username from admin where ativo = true";
        try {
			PreparedStatement ps2 = conexao.prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			while(rs.next()){
                desativaUsuario(rs.getString("username"));
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, a.getUsername().toLowerCase());
            ps.executeUpdate();
        }catch(SQLException e) {System.out.println("Erro ao ativar usuário: " + e.getMessage());}
	}

	public void desativaUsuario(String nome){
		String sql = "update admin set ativo = false where username =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, nome.toLowerCase());
            ps.executeUpdate();
        }catch(SQLException e) {System.out.println("Erro ao desativar usuário: " + e.getMessage());}
	}

	public boolean validarAdministrador(String nome, String senha){
		try{
			PreparedStatement ps = conexao.prepareStatement("select * from admin where username = ? and papel = 'adm'");
            ps.setString(1, nome.toLowerCase());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("password").equals(senha);
            }
            return false;
		}catch(SQLException e){
			System.out.println("Erro ao validar administrador: " + e.getMessage());
            return false;
		}
	}

	private void getDados(){
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select * from admin");
			while (rs.next()) {
				listaDeAdmin.put(rs.getString(1), rs.getString(2));
			}
		}catch(SQLException e) {System.out.println("erro: " + e.getMessage());}
	}

	public Admin getUsuarioAtivo(){
		try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from admin where ativo = true");
            if(rs.next()){
				//System.out.println("nome: " + rs.getString(1));
                return new Admin(rs.getString(1), rs.getString(2), rs.getString(3));
            }
            return null;
        }catch(SQLException e) {System.out.println("Erro ao pegar usuario ativo : " + e.getMessage()); return null;}
	}

	public boolean isAdm(String nome){
		try{
			PreparedStatement ps = conexao.prepareStatement("select * from admin where papel = 'adm'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
                if(rs.getString(1).equals(nome.toLowerCase())){
					return true;
				}
            }
			return false;
		}catch(SQLException e){
			System.out.println("Erro ao verificar se é administrador: " + e.getMessage());
            return false;
		}
	}
}

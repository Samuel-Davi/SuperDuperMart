package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.DatabaseConnection;
import model.Fornecedores;

public class FornecedoresDAO extends DatabaseConnection{
    ArrayList<Fornecedores> listaDeFornecedores = new ArrayList<Fornecedores>();

    public FornecedoresDAO() {
        getDados();
    }

    public boolean addFornecedor(Fornecedores f){
        try {
			Statement st = conexao.createStatement();
			st.executeUpdate("insert into fornecedores values ("+ "\""+ f.getNome().toLowerCase()+"\"" + ", "+ "\""+ f.getContato()+"\"" +")");
			return true;
		}catch(SQLException e) {System.out.println(e.getMessage()); return false;}
        
    }

    public boolean fornecedorExiste(Fornecedores f){
        return listaDeFornecedores.contains(f);
    }

    private void getDados(){
        try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select * from fornecedores");
			while (rs.next()) {
                Fornecedores f = new Fornecedores(rs.getString(1), rs.getString(2));
                listaDeFornecedores.add(f);
			}
		}catch(SQLException e) {System.out.println("erro: " + e.getMessage());}
    }
}

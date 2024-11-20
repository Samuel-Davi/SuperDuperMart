package dao;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.DatabaseConnection;
import model.Compras;
import model.Fornecedores;
import model.Produtos;

public class ComprasDAO extends DatabaseConnection{

    public boolean addCompras(Produtos p, Fornecedores f, Integer quantidade){
        try {
			Statement st = conexao.createStatement();
			st.executeUpdate("insert into compras values (null, "+ "\""+ f.getNome().toLowerCase()+
            "\"" + "," + "\""+ p.getNome_produto().toLowerCase()+ "\"" + "," + quantidade + "," +
            p.getPreco_compra()  + ",default , "+ (p.getPreco_compra().multiply(BigDecimal.valueOf(quantidade))) +")");
			return true;
		}catch(SQLException e) {System.out.println("erro ao add compra: " + e.getMessage()); return false;}
        
    }

    public void alterarCompra(Compras compra){
        try{
            Statement st2 = conexao.createStatement();
            st2.executeUpdate("update compras set nome_fornecedor = " + "\"" + compra.getNomeFornecedor() +
             "\"" +",nome_produto = " + "\"" + compra.getNomeProduto() + "\"" + ",quantidade = " + compra.getQuantidade() +
                 " where nome = '" + compra + "'");
//finalizar isso aqui
        }catch(SQLException e){
            System.out.println("erro no alterar produto: " + e.getMessage());
        }
    }

    public ArrayList<String> getIds(){
        try{
            Statement st = conexao.createStatement();
            ArrayList<String> ids = new ArrayList<>();
            ResultSet rs = st.executeQuery("select id from compras");
            while(rs.next()){
                Integer id = rs.getInt(1);
                ids.add(id.toString());
            }
            return ids;
        }catch(SQLException e){
            System.out.println("Erro ao pegar ids: " + e.getMessage());
            return null;
        }
    }

    public Compras getCompraPorIds(String idParam) {
        Integer id = Integer.parseInt(idParam);

        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from compras where id = " + id);
            if(rs.next()){
                Compras compra = new Compras(rs.getString("nome_fornecedor"),
                rs.getString("nome_produto"), rs.getBigDecimal("preco_unitario"), rs.getInt("quantidade"));
                return compra;
            }
            return null;
        }catch (SQLException e) {
            System.out.println("Erro ao pegar ids: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<String> getFornecedores() {
        ArrayList<String> fornecedores = new ArrayList<>();
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select nome from fornecedores");
            while(rs.next()){
                fornecedores.add(rs.getString("nome"));
            }
            return fornecedores;
        }catch (SQLException e) {
            System.out.println("Erro ao pegar fornecedores: " + e.getMessage());
            return null;
        }
    }
}
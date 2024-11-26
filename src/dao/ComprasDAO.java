package dao;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import config.DatabaseConnection;
import model.Compras;
import model.Produtos;

public class ComprasDAO extends DatabaseConnection{

    public boolean addCompras(Produtos p, Integer quantidade){
        try {
			String query = "insert into compras (id, nome_produto, quantidade, preco_unitario, data_compra, valor_total)" + 
            "values (null, ?, ?, ?, default, ?)";
			PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, p.getNome_produto());
            ps.setInt(2, quantidade);
            ps.setBigDecimal(3, p.getPreco_compra());
            ps.setBigDecimal(4, p.getPreco_compra().multiply(new BigDecimal(quantidade)));
            ps.executeUpdate();
			return true;
		}catch(SQLException e) {System.out.println("erro ao add compra: " + e.getMessage()); return false;}
        
    }

    public void alterarCompra(Compras compra){
        try{
            Statement st2 = conexao.createStatement();
            st2.executeUpdate("update compras set nome_produto = " + "\"" +
            compra.getNomeProduto() + "\"" + ",quantidade = " + compra.getQuantidade() +
            ", preco_unitario = " + compra.getPrecoUnitario() + ", data_compra = default, valor_total = " + 
            compra.getTotalCompra() +" where id = " + compra.getId());

        }catch(SQLException e){
            System.out.println("erro no alterar compra: " + e.getMessage());
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
                Compras compra = new Compras(rs.getInt("id"), rs.getString("nome_produto"),
                rs.getBigDecimal("preco_unitario"), rs.getInt("quantidade"));
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

    public Timestamp getDataPorId(Integer id){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select data_compra from compras where id = " + id);
            if(rs.next()){
                Timestamp data = rs.getTimestamp("data_compra");
                return data;
            }
            return null;
        }catch(SQLException e){
            System.out.println("Erro ao pegar data: " + e.getMessage());
            return null;
        }
    }
}
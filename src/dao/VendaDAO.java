package dao;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import config.DatabaseConnection;
import javafx.scene.control.ComboBox;
import model.Compras;
import model.Produtos;
import model.Vendas;

public class VendaDAO extends DatabaseConnection{

    public boolean addVenda(Vendas v){

        String queryVenda = "insert into vendas (id, data_venda, valor_total, id_produto, valor_pago," +
        "troco, quantidade,forma_pagamento) values (?, default, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexao.prepareStatement(queryVenda);
            ps.setNull(1, java.sql.Types.INTEGER);
            ps.setBigDecimal(2, v.getValorTotal());
            ps.setInt(3, v.getProduto().getId());
            ps.setBigDecimal(4, v.getValorPago());
            ps.setBigDecimal(5, v.getTroco());
            ps.setInt(6, v.getQuantidade());
            ps.setString(7, v.getFormaPagamento());
            ps.executeUpdate();
            alterarProduto(v.getProduto().getNomeTotal(),
            getEstoqueProduto(v.getProduto().getNomeTotal()) - v.getQuantidade());
            return true;
		}catch(SQLException e) {System.out.println("erro ao add venda: " + e.getMessage()); return false;}
        
    }

    public ArrayList<String> getNomesProdutos(){
        ArrayList<String> nomes = new ArrayList<>();
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select nome from produtos");
            while(rs.next()){
                nomes.add(rs.getString("nome"));
            }
            return nomes;
        }catch(SQLException e){System.out.println("erro ao carregar produtos: " + e.getMessage()); return nomes;}
    }

    public Double getValorUnitarioPorNome(String nome){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select preco_venda from produtos where nome = " + "\"" + nome + "\"");
            if(rs.next()){
                System.out.println("Preco venda: " + rs.getBigDecimal("preco_venda"));
                return Double.parseDouble(rs.getBigDecimal("preco_venda").toString());
            }
            return 0.0;
        }catch(SQLException e){
            System.out.println("erro ao carregar valor unitario: " + e.getMessage());
            return 0.0;
        }
    }

    public void alterarProduto(String nome, int estoqueAtual){
        try{
            Statement st = conexao.createStatement();
            st.executeUpdate("update produtos set estoque_atual = " + estoqueAtual + " where nome = '" + nome + "'");
        }catch(SQLException e){
            System.out.println("erro no alterar produto: " + e.getMessage());
        }
    }

    public Integer getEstoqueProduto(String nome){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select estoque_atual from produtos where nome = " + "\"" + nome + "\"");
            if(rs.next()){
                return rs.getInt(1);
            }else{
                return 0;
            }
        }catch(SQLException e){
            System.out.println("Erro ao carregar estoque: " + e.getMessage());
            return 0;
        }
    }

    public void alterarVenda(Vendas venda){
        try{
            Statement st2 = conexao.createStatement();
            st2.executeUpdate("update vendas set forma_pagamento = " + "\"" + venda.getFormaPagamento() +
            "\"" + ",troco = " + venda.getTroco() + ", valor_pago = " + venda.getValorPago() + ",id_produto = " 
            + "\"" +  venda.getProduto().getId() + "\"" +", data_venda = default, valor_total = " +
            venda.getValorTotal() +" where id = " + venda.getId());
        }catch(SQLException e){
            System.out.println("erro no alterar venda: " + e.getMessage());
        }
    }

    public ArrayList<String> getIds(){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select id from vendas");
            ArrayList<String> ids = new ArrayList<>();
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

    public Vendas getVendasPorIds(String idParam) {
        Integer id = Integer.parseInt(idParam);

        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from vendas where id = " + id);
            if(rs.next()){
                Integer idProduto = rs.getInt("id_produto");
                ProdutosDAO pdao = new ProdutosDAO();
                Produtos p = pdao.getProdutoPorId(idProduto.toString());
                Vendas venda = new Vendas(rs.getInt("id"), rs.getBigDecimal("valor_pago"),
                rs.getString("forma_pagamento"), rs.getBigDecimal("troco"), p,
                new BigDecimal(p.getPreco_venda().toString()), rs.getInt("quantidade"));
                return venda;
            }
            return null;
        }catch (SQLException e) {
            System.out.println("Erro ao pegar ids: " + e.getMessage());
            return null;
        }
    }

    public Timestamp getDataPorId(Integer id){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select data_venda from vendas where id = " + id);
            if(rs.next()){
                Timestamp data = rs.getTimestamp("data_venda");
                return data;
            }
            return null;
        }catch(SQLException e){
            System.out.println("Erro ao pegar data: " + e.getMessage());
            return null;
        }
    }
}
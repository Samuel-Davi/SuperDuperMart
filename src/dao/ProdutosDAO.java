package dao;
import java.lang.Thread.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import config.DatabaseConnection;
import model.Produtos;

public class ProdutosDAO extends DatabaseConnection{
    ArrayList<Produtos> listaDeProdutos = new ArrayList<Produtos>();

    public ProdutosDAO() {
        getDados();
    }

    public boolean addProduto(Produtos p){
        try {
            String query = "insert into produtos (nome, descricao, preco_compra, preco_venda, estoque_atual, data_cadastro)" + 
            "values (?, ?, ?, ?, ?, default)";
			PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, p.getNome_produto());
            ps.setString(2, p.getDescricao());
            ps.setBigDecimal(3, p.getPreco_compra());
            ps.setBigDecimal(4, p.getPreco_venda());
            ps.setInt(5, p.getEstoque_atual());
            ps.executeUpdate();
			return true;
		}catch(SQLException e) {System.out.println("erro ao add produto: " + e.getMessage()); return false;}
        
    }

    public boolean ProdutoExiste(Produtos p){
        for(Produtos pro: listaDeProdutos){
            if(pro.getNome_produto().equals(p.getNome_produto())) return true;
        }
        return false;
    }

    public Produtos getProdutoPorNome(String nome){
        for(Produtos p: listaDeProdutos){
            if(p.getNome_produto().equals(nome)) return p;
        }
        return null;
    }

    public void alterarProduto(String nome, String desc, double precoCompra, double precoVenda, int estoqueAtual){
        try{
            Statement st2 = conexao.createStatement();
            st2.executeUpdate("update produtos set descricao = " + "\"" + desc + "\"" + ",preco_compra = " + precoCompra + ", " +
                "preco_venda = " + precoVenda + ", estoque_atual = " + estoqueAtual +
                " where nome = '" + nome + "'");

        }catch(SQLException e){
            System.out.println("erro no alterar produto: " + e.getMessage());
        }
    }

    public void atualizaEstoque(String nome, Integer mudancaEstoque){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select estoque_atual from produtos where nome = " + "\"" + nome + "\"");
            if(rs.next()){
                Statement st2 = conexao.createStatement();
                Integer estoqueRes = rs.getInt(1) + mudancaEstoque;
                System.out.println("novo estoque: "+ estoqueRes);
                st2.executeUpdate("update produtos set estoque_atual = " + estoqueRes +
                    " where nome = '" + nome + "'");
        
            }
        }catch(SQLException e){
            System.out.println("erro no alterar estoque: " + e.getMessage());
        }
    }

    private void getDados(){
        try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select * from produtos");
			while (rs.next()) {
                Produtos f = new Produtos(rs.getString(1), rs.getString(2), rs.getBigDecimal(3), rs.getBigDecimal(4),
                rs.getInt(5));
                listaDeProdutos.add(f);
			}
		}catch(SQLException e) {System.out.println("erro no pegar dados dos produtos: " + e.getMessage());}
    }

    public ArrayList<String> getNomeProdutos(){
        ArrayList<String> nomes = new ArrayList<>();
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select nome from produtos");
            while(rs.next()){
                nomes.add(rs.getString(1));
            }
            return nomes;
        }catch(SQLException e){
            System.out.println("Erro ao pegar nomes dos produtos: " + e.getMessage());
        }
        return null;
    }

    public Timestamp getDataPorNome(String nome){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select data_cadastro from produtos where nome = " + "\"" + nome + "\"");
            if(rs.next()){
                Timestamp data = rs.getTimestamp("data_cadastro");
                return data;
            }
            return null;
        }catch(SQLException e){
            System.out.println("Erro ao pegar data: " + e.getMessage());
            return null;
        }
    }

    public void deleteProduto(String nome){
        try{
            Statement st1 = conexao.createStatement();
            Statement st2 = conexao.createStatement();
            Statement st3 = conexao.createStatement();
            st3.executeUpdate("delete from compras where nome_produto = '" + nome + "'");
            st2.executeUpdate("delete from vendas where nome_produto = '" + nome + "'");
            st1.executeUpdate("delete from produtos where nome = '" + nome + "'");
        }catch(SQLException e){
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        }
    }
}

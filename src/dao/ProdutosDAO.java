package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			Statement st = conexao.createStatement();
			st.executeUpdate("insert into produtos values ("+ "\""+ p.getNome_produto().toLowerCase()+
            "\"" + ", "+ p.getPreco_compra() +", "+ p.getPreco_venda() +", "+ p.getEstoque_atual() + ", default)");
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

    public void alterarProduto(String nome, double precoCompra, double precoVenda, int estoqueAtual){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select estoque_atual from produtos where nome = " + "\"" + nome + "\"");
            Integer estoqueRes = rs.getInt(1) + estoqueAtual;
            st.executeUpdate("update produtos set preco_compra = " + precoCompra + ", " +
                    "preco_venda = " + precoVenda + ", estoque_atual = " + estoqueRes + " where nome_produto = '" + nome + "'");
        }catch(SQLException e){
            System.out.println("erro no alterar produto: " + e.getMessage());
        }
    }

    private void getDados(){
        try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select * from produtos");
			while (rs.next()) {
                Produtos f = new Produtos(rs.getString(1), rs.getDouble(2), rs.getDouble(3),
                rs.getInt(4));
                listaDeProdutos.add(f);
			}
		}catch(SQLException e) {System.out.println("erro no pegar dados dos produtos: " + e.getMessage());}
    }
}

package dao;
import java.sql.SQLException;
import java.sql.Statement;

import config.DatabaseConnection;
import model.Fornecedores;
import model.Produtos;

public class ComprasDAO extends DatabaseConnection{

    public boolean addCompras(Produtos p, Fornecedores f, Integer quantidade){
        try {
			Statement st = conexao.createStatement();
			st.executeUpdate("insert into compras values (null, "+ "\""+ f.getNome().toLowerCase()+
            "\"" + "," + "\""+ p.getNome_produto().toLowerCase()+ "\"" + "," + quantidade + "," +
            p.getPreco_compra()  + ",default , "+ (p.getPreco_compra()*quantidade) +")");
			return true;
		}catch(SQLException e) {System.out.println("erro ao add compra: " + e.getMessage()); return false;}
        
    }

    /*public Produtos getProdutoPorNome(String nome){
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
    }*/
}
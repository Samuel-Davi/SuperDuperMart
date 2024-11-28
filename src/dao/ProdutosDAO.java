package dao;
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
            String query = "insert into produtos (id, nome, nome_marca, preco_compra, preco_venda, estoque_atual, data_cadastro)" + 
            "values (null, ?, ?, ?, ?, ?, default)";
			PreparedStatement ps = conexao.prepareStatement(query);
            String campoNomeGeral = p.getNome_produto() + " " + "(" + p.getQtdUM() + "," + p.getUM() + ")";
            ps.setString(1, campoNomeGeral);
            ps.setString(2, p.getMarca());
            ps.setBigDecimal(3, p.getPreco_compra());
            ps.setBigDecimal(4, p.getPreco_venda());
            ps.setInt(5, p.getEstoque_atual());
            ps.executeUpdate();
			return true;
		}catch(SQLException e) {System.out.println("erro ao add produto: " + e.getMessage()); return false;}
        
    }

    public Integer getIdPorNome(String nome){
        getDados();
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select id from produtos where nome = " + "\"" + nome + "\"");
            if(rs.next()){
                int id = (Integer.valueOf(rs.getInt(1)));
                return id;
            }
            return null;
        }catch(SQLException e){
            System.out.println("Erro ao pegar id: " + e.getMessage());
            return null;
        }
    }

    public String getNomePorId(Integer id){
        getDados();
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select nome from produtos where id = " + id);
            if(rs.next()){
                return rs.getString(1);
            }
            return null;
        }catch(SQLException e){
            System.out.println("Erro ao pegar nome por id: " + e.getMessage());
            return null;
        }
    }

    public void addMarca(String marca){
        try{
            Statement st = conexao.createStatement();
            st.executeUpdate("insert into marcas (nome) values (" + "\"" + marca + "\")");
        }catch(Exception e){
            System.out.println("Erro ao add marca: " + e.getMessage());
        }
    }

    public boolean ProdutoExiste(Produtos p){
        for(Produtos pro: listaDeProdutos){
            if(pro.getId() == (p.getId())) return true;
        }
        return false;
    }

    public Produtos getProdutoPorNome(String nome){
        getDados();
        for(Produtos p: listaDeProdutos){
            if(p.getNomeTotal().equals(nome)) return p;
        }
        return null;
    }

    public ArrayList<String> getMarcas(){
        ArrayList<String> marcas = new ArrayList<String>();
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select nome from marcas");
            while(rs.next()){
                marcas.add(rs.getString(1));
            }
            return marcas;
        }catch(SQLException e){
            System.out.println("erro ao pegar marcas: " + e.getMessage());
            return marcas;
        }
    }

    public boolean MarcaExiste(String marca){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from marcas where nome = " + "\"" + marca + "\"");
            if(rs.next()){
                int quantidade = (Integer.valueOf(rs.getInt(1)));
                if(quantidade > 0) return true;
            }
            return false;
        }catch(SQLException e){
            System.out.println("Erro ao verificar se a marca existe: " + e.getMessage());
            return false;
        }
    }
    public Produtos getProdutoPorId(String Id){
        Integer id = Integer.parseInt(Id);
        for(Produtos p: listaDeProdutos){
            if(p.getId() == (id)) return p;
        }
        return null;
    }

    public void alterarProduto(Integer id, String nome, String marca, double precoCompra, double precoVenda, int estoqueAtual){
        try{
            String sql = "UPDATE produtos SET nome =?, nome_marca =?, preco_compra =?, preco_venda =?, estoque_atual =? WHERE id =?";  // SQL query to update records in the table
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, nome);
            st.setString(2, marca);
            st.setDouble(3, precoCompra);
            st.setDouble(4, precoVenda);
            st.setInt(5, estoqueAtual);
            st.setInt(6, id);
            st.executeUpdate();

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
                Produtos f = new Produtos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getBigDecimal(5),
                rs.getInt(6));
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
    public ArrayList<String> getIdsProdutos(){
        ArrayList<String> nomes = new ArrayList<>();
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select id from produtos");
            while(rs.next()){
                Integer id = rs.getInt(1);
                nomes.add(id.toString());
            }
            return nomes;
        }catch(SQLException e){
            System.out.println("Erro ao pegar ids dos produtos: " + e.getMessage());
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

    public void deleteProduto(Integer id){
        try{
            Statement st1 = conexao.createStatement();
            Statement st2 = conexao.createStatement();
            Statement st3 = conexao.createStatement();
            st3.executeUpdate("delete from compras where id_produto = '" + id + "'");
            st2.executeUpdate("delete from vendas where id_produto = '" + id + "'");
            st1.executeUpdate("delete from produtos where id = '" + id + "'");
        }catch(SQLException e){
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        }
    }
}

package dao;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.DatabaseConnection;
import javafx.scene.control.ComboBox;
import model.Fornecedores;
import model.Produtos;

public class VendaDAO extends DatabaseConnection{

    public boolean addVenda(Integer quantidade, Double valorUnitario, Double valorTotal, Double valorPago,
    Double troco , String nomeProduto, String formaPagamento){

        String queryVenda = "insert into vendas values (null, default, " + new BigDecimal(valorTotal) + "," +
        new BigDecimal(valorPago) + "," + new BigDecimal(troco) + "," + "\"" + formaPagamento + "\"" + ")";

        try {
			Statement st = conexao.createStatement();
            st.executeUpdate(queryVenda);
            Statement st2 = conexao.createStatement();
			ResultSet rs = st2.executeQuery("select last_insert_id()");
            if (rs.next()) {
                int lastId = rs.getInt(1);
                String queryItemVenda = "INSERT INTO item_venda (id, id_venda, nome_produto, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conexao.prepareStatement(queryItemVenda);
                ps.setNull(1, java.sql.Types.INTEGER);
                ps.setInt(2, lastId);
                ps.setString(3, nomeProduto);
                ps.setInt(4, quantidade);
                ps.setBigDecimal(5, new BigDecimal(valorUnitario));
                ps.setBigDecimal(6, new BigDecimal(valorTotal));
                ps.executeUpdate();
            }
            alterarProduto(nomeProduto, getEstoqueProduto(nomeProduto) - quantidade);
            return true;
		}catch(SQLException e) {System.out.println("erro ao add venda ou item_venda: " + e.getMessage()); return false;}
        
    }

    public void getNomesProdutos(ComboBox<String> comboBox){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select nome from produtos");
            while(rs.next()){
                comboBox.getItems().add(rs.getString("nome"));
            }
        }catch(SQLException e){System.out.println("erro ao carregar produtos: " + e.getMessage());}
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
}
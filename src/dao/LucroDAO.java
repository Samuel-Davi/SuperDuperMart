package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DatabaseConnection;

public class LucroDAO extends DatabaseConnection{
    public Double getValorCompras(){
        try{
            String sql = "SELECT SUM(valor_total) as valor FROM compras";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Double soma = 0.0;
            if(rs.next()){
                if(rs.getBigDecimal("valor") != null){
                    soma = (Double.valueOf(rs.getBigDecimal("valor").toString()));
                }
            }
            return soma;
        }catch(SQLException e){
            System.out.println("erro ao pegar valor compras: " + e.getMessage());
            return (0.0);
        }
    }

    public Double getValorVendas(){
        try{
            String sql = "SELECT SUM(valor_total) as valor FROM vendas";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Double soma = 0.0;
            if(rs.next()){
                if(rs.getBigDecimal("valor")!= null){
                    soma = (Double.valueOf(rs.getBigDecimal("valor").toString()));
                }
            }
            return soma;
        }catch(SQLException e){
            System.out.println("erro ao pegar valor vendas: " + e.getMessage());
            return (0.0);
        }
    }

    public Integer getQuantidadeTotal(){
        try{
            String sql = "SELECT SUM(estoque_atual) as quantidade FROM produtos";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer quantidade = 0;
            if(rs.next()){
                quantidade = (Integer.valueOf(rs.getInt("quantidade")));
            }
            return quantidade;
        }catch(SQLException e){
            System.out.println("erro ao pegar quantidade: " + e.getMessage());
            return (0);
        }
    }

    public Double getValorFinanceiro(){
        try{
            String sql = "select (preco_venda, estoque_atual) from produtos";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Double soma = 0.0;
            while(rs.next()){
                Double valorVenda = rs.getDouble(1);
                Integer quantidade = rs.getInt(2);
                Double totalVenda = valorVenda * quantidade;
                soma += totalVenda;
            }
            return soma;
        }catch(SQLException e){
            System.out.println("erro ao pegar valor financeiro: " + e.getMessage());
            return (0.0);
        }
    }
}

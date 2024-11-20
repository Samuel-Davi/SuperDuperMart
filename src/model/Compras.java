package model;

import java.math.BigDecimal;

import dao.ProdutosDAO;

public class Compras {

    private String nomeProduto;
    private BigDecimal precoUnitario;
    private Integer id, quantidade;

    public Compras(Integer id, String nomeProduto, BigDecimal precoUnitario, Integer quantidade) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public String getDescricao(){
        Produtos p = new ProdutosDAO().getProdutoPorNome(nomeProduto);
        return p.getDescricao();
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotalCompra() {
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }

}

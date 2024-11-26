package model;

import java.math.BigDecimal;

public class Produtos {
    private String nome_produto, descricao;
    private BigDecimal preco_compra, preco_venda;
    private int estoque_atual;

    public Produtos(String nome_produto,String descricao ,BigDecimal preco_compra,
     BigDecimal preco_venda, int estoque_atual){
        this.nome_produto = nome_produto;
        this.descricao = descricao;
        this.preco_compra = (preco_compra);
        this.preco_venda = (preco_venda);
        this.estoque_atual = estoque_atual;
    }

    // Getters and Setters
    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public BigDecimal getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(BigDecimal preco_compra) {
        this.preco_compra = preco_compra;
    }

    public BigDecimal getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(BigDecimal preco_venda) {
        this.preco_venda = preco_venda;
    }

    public int getEstoque_atual() {
        return estoque_atual;
    }

    public void setEstoque_atual(int estoque_atual) {
        this.estoque_atual = estoque_atual;
    }
    
}

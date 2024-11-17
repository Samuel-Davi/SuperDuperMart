package model;

public class Produtos {
    private String nome_produto;
    private double preco_compra, preco_venda;
    private int estoque_atual;

    public Produtos(String nome_produto, double preco_compra,
     double preco_venda, int estoque_atual) {
        this.nome_produto = nome_produto;
        this.preco_compra = preco_compra;
        this.preco_venda = preco_venda;
        this.estoque_atual = estoque_atual;
    }

    // Getters and Setters
    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }
    public double getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(double preco_compra) {
        this.preco_compra = preco_compra;
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public int getEstoque_atual() {
        return estoque_atual;
    }

    public void setEstoque_atual(int estoque_atual) {
        this.estoque_atual = estoque_atual;
    }
    
}

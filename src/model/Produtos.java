package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Produtos {
    private String marca, nome_produto;
    private BigDecimal preco_compra, preco_venda;
    private int id, estoque_atual;

    public Produtos(int id, String nome_produto, String marca, BigDecimal preco_compra,
     BigDecimal preco_venda, int estoque_atual){
        this.marca = marca;
        this.id = id;
        this.nome_produto = nome_produto;
        this.preco_compra = (preco_compra);
        this.preco_venda = (preco_venda);
        this.estoque_atual = estoque_atual;
    }

    public void imprimeProduto(){
        System.out.println("ID: " + id + ", Nome do Produto: " + nome_produto + ", Marca: " + marca +
        ", Preço de Compra: " + preco_compra + ", Preço de Venda: " + preco_venda + ", Estoque Atual: " + estoque_atual);
    }

    // Getters and Setters

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNome_produto() {
        String[] vetor = this.nome_produto.split(" ");
        ArrayList<String> campoNomeProduto = new ArrayList<>(Arrays.asList(vetor));
        ArrayList<String> subCampo = new ArrayList<>(campoNomeProduto.subList(0, campoNomeProduto.size() - 1));
        return String.join(" ", subCampo);
    }

    public String getNomeTotal(){
        return this.nome_produto;
    }

    public String getQtdUM(){
        String[] vetor = this.nome_produto.split(" ");
        ArrayList<String> campoNomeProduto = new ArrayList<>(Arrays.asList(vetor));
        ArrayList<String> subCampo = new ArrayList<>(campoNomeProduto.subList(campoNomeProduto.size() - 1, campoNomeProduto.size()));
        String separaSubCampo[] = subCampo.get(0).split(",");
        return separaSubCampo[0].replace("(", "");
    }

    public String getUM(){
        String[] vetor = this.nome_produto.split(" ");
        ArrayList<String> campoNomeProduto = new ArrayList<>(Arrays.asList(vetor));
        ArrayList<String> subCampo = new ArrayList<>(campoNomeProduto.subList(campoNomeProduto.size() - 1, campoNomeProduto.size()));
        String separaSubCampo[] = subCampo.get(0).split(",");
        return separaSubCampo[1].replace(")", "");
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
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

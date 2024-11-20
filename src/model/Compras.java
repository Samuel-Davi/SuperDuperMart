package model;

import java.math.BigDecimal;

public class Compras {

    private String nomeFornecedor, nomeProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;

    public Compras(String nomeFornecedor, String nomeProduto, BigDecimal precoUnitario, Integer quantidade) {
        this.nomeFornecedor = nomeFornecedor;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    // Getters and Setters
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
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

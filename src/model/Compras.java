package model;

import java.math.BigDecimal;

public class Compras {

    private Integer idProduto;
    private BigDecimal precoUnitario;
    private Integer id, quantidade;

    public Compras(Integer id, Integer idProduto, BigDecimal precoUnitario, Integer quantidade) {
        this.id = id;
        this.idProduto = idProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getidProduto() {
        return idProduto;
    }

    public void setidProduto(Integer idProduto) {
        this.idProduto = idProduto;
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

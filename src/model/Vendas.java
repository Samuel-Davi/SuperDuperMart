package model;

import java.math.BigDecimal;

public class Vendas {
    private Integer id, quantidade;
    private BigDecimal valorTotal, valorPago, troco, precoUnitario;
    private String formaPagamento, nomeProduto;

    public Vendas(Integer id,  BigDecimal valorPago, String formaPagamento,
     BigDecimal troco, String nomeProduto, BigDecimal precoUnitario, Integer quantidade) {
        this.id = id;
        this.valorTotal = precoUnitario.multiply(new BigDecimal(quantidade));
        this.valorPago = valorPago;
        this.formaPagamento = formaPagamento;
        if(Double.valueOf(valorPago.subtract(valorTotal).toString()) >= 0.0) 
            this.troco = valorPago.subtract(valorTotal);
        else this.troco = new BigDecimal(0.0);
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


}

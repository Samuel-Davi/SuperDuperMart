package model;

import java.math.BigDecimal;

public class Vendas {
    private Produtos produto;
    private Integer id, quantidade;
    private BigDecimal valorTotal, valorPago, troco, precoUnitario;
    private String formaPagamento;

    public Vendas(Integer id,  BigDecimal valorPago, String formaPagamento,
     BigDecimal troco, Produtos produto, BigDecimal precoUnitario, Integer quantidade) {
        this.id = id;
        this.valorTotal = precoUnitario.multiply(new BigDecimal(quantidade));
        this.valorPago = valorPago;
        this.formaPagamento = formaPagamento;
        if(Double.valueOf(valorPago.subtract(valorTotal).toString()) >= 0.0) 
            this.troco = valorPago.subtract(valorTotal);
        else this.troco = new BigDecimal(0.0);
        this.produto = produto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public void imprimeVenda(){
        System.out.println("ID: " + id);
        System.out.println("Nome do Produto: " + produto.getNome_produto());
        System.out.println("Marca: " + produto.getMarca());
        System.out.println("Valor Total: " + valorTotal);
        System.out.println("Valor Pago: " + valorPago);
        System.out.println("Troco: " + troco);
        System.out.println("Forma de Pagamento: " + formaPagamento);
        System.out.println("Preço Unitário: " + precoUnitario);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("--------------------------------------------");
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

    public Produtos getProduto() {
        return produto;
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

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


}

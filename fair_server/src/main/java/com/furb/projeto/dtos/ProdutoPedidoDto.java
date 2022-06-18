package com.furb.projeto.dtos;

import java.math.BigDecimal;

public class ProdutoPedidoDto {

    private Integer fkProduto;
    private Integer fkPedido;
    private Integer quantidade;
    private BigDecimal peso;
    private BigDecimal preco;

    public ProdutoPedidoDto() {
    }

    public ProdutoPedidoDto(Integer fkProduto, Integer fkPedido, Integer quantidade, BigDecimal peso, BigDecimal preco) {
        this.fkProduto = fkProduto;
        this.fkPedido = fkPedido;
        this.quantidade = quantidade;
        this.peso = peso;
        this.preco = preco;
    }

    public Integer getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Integer fkProduto) {
        this.fkProduto = fkProduto;
    }

    public Integer getFkPedido() {
        return fkPedido;
    }

    public void setFkPedido(Integer fkPedido) {
        this.fkPedido = fkPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}

package com.furb.projeto.dtos;

import java.math.BigInteger;

public class ProdutoPedidoDto {

    private Integer fkProduto;
    private Integer fkPedido;
    private Integer quantidade;
    private BigInteger peso;
    private BigInteger preco;

    public ProdutoPedidoDto() {
    }

    public ProdutoPedidoDto(Integer fkProduto, Integer fkPedido, Integer quantidade, BigInteger peso, BigInteger preco) {
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

    public BigInteger getPeso() {
        return peso;
    }

    public void setPeso(BigInteger peso) {
        this.peso = peso;
    }

    public BigInteger getPreco() {
        return preco;
    }

    public void setPreco(BigInteger preco) {
        this.preco = preco;
    }
}

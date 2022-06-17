package com.furb.projeto.dtos;

import java.math.BigInteger;

public class ProdutoLojaDto {

    private Integer fkProduto;
    private Integer fkLoja;

    private BigInteger preco;

    public ProdutoLojaDto() {
    }

    public ProdutoLojaDto(Integer fkProduto, Integer fkLoja, BigInteger preco) {
        this.fkProduto = fkProduto;
        this.fkLoja = fkLoja;
        this.preco = preco;
    }

    public Integer getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Integer fkProduto) {
        this.fkProduto = fkProduto;
    }

    public Integer getFkLoja() {
        return fkLoja;
    }

    public void setFkLoja(Integer fkLoja) {
        this.fkLoja = fkLoja;
    }

    public BigInteger getPreco() {
        return preco;
    }

    public void setPreco(BigInteger preco) {
        this.preco = preco;
    }
}

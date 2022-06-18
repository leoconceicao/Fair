package com.furb.projeto.dtos;

import java.math.BigDecimal;

public class ProdutoLojaDto {

    private Integer fkProduto;
    private Integer fkLoja;

    private BigDecimal preco;

    public ProdutoLojaDto() {
    }

    public ProdutoLojaDto(Integer fkProduto, Integer fkLoja, BigDecimal preco) {
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}

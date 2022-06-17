package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "Produto_Loja")
public class ProdutoLojaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProdutoLoja;
    @OneToOne
    @JoinColumn(name = "idProduto")
    private ProdutoModel fkProduto;

    @OneToOne
    @JoinColumn(name = "idLoja")
    private LojaModel fkLoja;

    @Column(nullable = false, length = 10)
    private BigInteger preco;

    public ProdutoLojaModel() {
    }

    public ProdutoLojaModel(Integer idProdutoLoja, ProdutoModel fkProduto, LojaModel fkLoja, BigInteger preco) {
        this.idProdutoLoja = idProdutoLoja;
        this.fkProduto = fkProduto;
        this.fkLoja = fkLoja;
        this.preco = preco;
    }

    public ProdutoModel getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(ProdutoModel fkProduto) {
        this.fkProduto = fkProduto;
    }

    public LojaModel getFkLoja() {
        return fkLoja;
    }

    public void setFkLoja(LojaModel fkLoja) {
        this.fkLoja = fkLoja;
    }

    public Integer getIdProdutoLoja() {
        return idProdutoLoja;
    }

    public void setIdProdutoLoja(Integer idProdutoLoja) {
        this.idProdutoLoja = idProdutoLoja;
    }

    public BigInteger getPreco() {
        return preco;
    }

    public void setPreco(BigInteger preco) {
        this.preco = preco;
    }
}

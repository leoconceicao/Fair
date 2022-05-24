package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "Produto")
public class ProdutoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProduto;
    @Column(nullable = false, length = 45)
    private String nome;
    @Column(nullable = false, length = 45)
    private String tipo;
    @Column(nullable = false, length = 10)
    private BigInteger preco;
    @Column(nullable = false)
    private String foto;
    @Column(nullable = false, length = 45)
    private String validade;
    @Column(nullable = false, length = 10)
    private BigInteger peso;

    public ProdutoModel(Integer idProduto, String nome, String tipo, BigInteger preco, String foto, String validade, BigInteger peso) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.foto = foto;
        this.validade = validade;
        this.peso = peso;
    }

    public ProdutoModel() {
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigInteger getPreco() {
        return preco;
    }

    public void setPreco(BigInteger preco) {
        this.preco = preco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public BigInteger getPeso() {
        return peso;
    }

    public void setPeso(BigInteger peso) {
        this.peso = peso;
    }
}

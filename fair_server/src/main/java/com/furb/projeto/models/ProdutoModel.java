package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

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
    private BigDecimal preco;
    @Column(nullable = false)
    private String foto;
    @Column(nullable = false, length = 45)
    private String validade;
    @Column(nullable = false, length = 10)
    private BigDecimal peso;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1")
    private Boolean active;

    public ProdutoModel(Integer idProduto, String nome, String tipo, BigDecimal preco, String foto, String validade, BigDecimal peso, Boolean active) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.foto = foto;
        this.validade = validade;
        this.peso = peso;
        this.active = active;
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
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

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

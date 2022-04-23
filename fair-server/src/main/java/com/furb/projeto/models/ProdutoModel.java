package com.furb.projeto.models;

import com.fasterxml.jackson.databind.node.BigIntegerNode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name = "Produto")
public class ProdutoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProduto;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private Blob foto;
    @Column(nullable = false)
    private String validade;
    @Column(nullable = false, length = 10)
    private BigIntegerNode peso;

    public ProdutoModel(Integer idProduto, String nome, String tipo, Blob foto, String validade, BigIntegerNode peso) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.tipo = tipo;
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

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public BigIntegerNode getPeso() {
        return peso;
    }

    public void setPeso(BigIntegerNode peso) {
        this.peso = peso;
    }
}

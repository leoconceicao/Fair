package com.furb.projeto.dtos;

import java.math.BigDecimal;

public class ProdutoDto {

    private Integer produtoId;
    private String nome;
    private String tipo;

    private BigDecimal preco;

    private String foto;
    private String validade;
    private BigDecimal peso;

    private Boolean active;

    public ProdutoDto(Integer produtoId, String nome, String tipo, BigDecimal preco, String foto, String validade, BigDecimal peso, Boolean active) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.foto = foto;
        this.validade = validade;
        this.peso = peso;
        this.active = active;
    }

    public ProdutoDto() {
    }

    public Integer getPessoaId() {
        return produtoId;
    }

    public void setPessoaId(Integer produtoId) {
        this.produtoId = produtoId;
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

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

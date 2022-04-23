package com.furb.projeto.dtos;

import java.math.BigInteger;

public class ProdutoDto {

    private Integer produtoId;
    private String nome;
    private String tipo;
    private String foto;
    private String validade;
    private BigInteger peso;

    public ProdutoDto(Integer produtoId, String nome, String tipo, String foto, String validade, BigInteger peso) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.tipo = tipo;
        this.foto = foto;
        this.validade = validade;
        this.peso = peso;
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

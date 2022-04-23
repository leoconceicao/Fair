package com.furb.projeto.dtos;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import org.hibernate.type.BlobType;

public class ProdutoDto {

    private Integer produtoId;
    private String nome;
    private String tipo;
    private BlobType foto;
    private String validade;
    private BigIntegerNode peso;

    public ProdutoDto(Integer produtoId, String nome, String tipo, BlobType foto, String validade, BigIntegerNode peso) {
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

    public BlobType getFoto() {
        return foto;
    }

    public void setFoto(BlobType foto) {
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

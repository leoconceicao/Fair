package com.furb.projeto.dtos;

public class LojaDto {

    private Integer idLoja;
    private String nome;
    private String cnpj;
    private String telefone;

    private String endereco;
    private Integer fkLogradouro;

    public LojaDto() {
    }

    public LojaDto(Integer idLoja, String nome, String cnpj, String telefone, String endereco, Integer fkLogradouro) {
        this.idLoja = idLoja;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.fkLogradouro = fkLogradouro;
    }

    public Integer getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Integer idLoja) {
        this.idLoja = idLoja;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getFkLogradouro() {
        return fkLogradouro;
    }

    public void setFkLogradouro(Integer fkLogradouro) {
        this.fkLogradouro = fkLogradouro;
    }
}

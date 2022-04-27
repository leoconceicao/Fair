package com.furb.projeto.dtos;

public class LojaDto {

    private Integer idLoja;
    private String nome;
    private String cnpj;
    private String telefone;

    public LojaDto(Integer idLoja, String nome, String cnpj, String telefone) {
        this.idLoja = idLoja;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    public LojaDto() {
    }

    public Integer getPessoaId() {
        return idLoja;
    }

    public void setPessoaId(Integer idLoja) {
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
}

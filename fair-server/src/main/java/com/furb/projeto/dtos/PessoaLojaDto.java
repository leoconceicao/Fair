package com.furb.projeto.dtos;

public class PessoaLojaDto {

    private Integer tipoPessoa;
    private Integer fkPessoa;
    private Integer fkLoja;

    public PessoaLojaDto(Integer tipoPessoa, Integer fkPessoa, Integer fkLoja) {
        this.tipoPessoa = tipoPessoa;
        this.fkPessoa = fkPessoa;
        this.fkLoja = fkLoja;
    }

    public PessoaLojaDto() {
    }

    public Integer getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Integer tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Integer getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(Integer fkPessoa) {
        this.fkPessoa = fkPessoa;
    }

    public Integer getFkLoja() {
        return fkLoja;
    }

    public void setFkLoja(Integer fkLoja) {
        this.fkLoja = fkLoja;
    }
}

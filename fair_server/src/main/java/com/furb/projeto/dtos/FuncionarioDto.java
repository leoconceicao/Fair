package com.furb.projeto.dtos;

public class FuncionarioDto {

    private String cargo;
    private Integer fkPessoa;
    private Integer fkLoja;

    public FuncionarioDto(String cargo, Integer fkPessoa, Integer fkLoja) {
        this.cargo = cargo;
        this.fkPessoa = fkPessoa;
        this.fkLoja = fkLoja;
    }

    public FuncionarioDto() {
    }

    public String getCargo() {
        return cargo;
    }

    public void setTipoPessoa(String cargo) {
        this.cargo = cargo;
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

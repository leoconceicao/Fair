package com.furb.projeto.dtos;

public class LogradouroDto {

    private Integer idLogradouro;
    private String nomeRua;
    private String bairro;
    private String cep;
    private Integer fkCidade;

    public LogradouroDto() {
    }

    public LogradouroDto(Integer idLogradouro, String nomeRua, String bairro, String cep, Integer fkCidade) {
        this.idLogradouro = idLogradouro;
        this.nomeRua = nomeRua;
        this.bairro = bairro;
        this.cep = cep;
        this.fkCidade = fkCidade;
    }

    public Integer getIdLogradouro() {
        return idLogradouro;
    }

    public void setIdLogradouro(Integer idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getFkCidade() {
        return fkCidade;
    }

    public void setFkCidade(Integer fkCidade) {
        this.fkCidade = fkCidade;
    }
}

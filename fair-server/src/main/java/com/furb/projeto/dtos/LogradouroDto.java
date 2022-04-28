package com.furb.projeto.dtos;

public class LogradouroDto {

    private Integer idLogradouro;
    private String nomeLogradouro;
    private String bairro;
    private String cep;
    private Integer fkCidade;

    public LogradouroDto() {
    }

    public LogradouroDto(Integer idLogradouro, String nomeLogradouro, String bairro, String cep, Integer fkCidade) {
        this.idLogradouro = idLogradouro;
        this.nomeLogradouro = nomeLogradouro;
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

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
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

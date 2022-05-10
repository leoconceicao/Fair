package com.furb.projeto.dtos;

public class CidadeDto {

    private Integer idCidade;
    private String dsCidade;
    private Integer fkEstado;

    public CidadeDto() {
    }

    public CidadeDto(Integer idCidade, String dsCidade, Integer fkEstado) {
        this.idCidade = idCidade;
        this.dsCidade = dsCidade;
        this.fkEstado = fkEstado;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getDsCidade() {
        return dsCidade;
    }

    public void setDsCidade(String dsCidade) {
        this.dsCidade = dsCidade;
    }

    public Integer getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Integer fkEstado) {
        this.fkEstado = fkEstado;
    }
}

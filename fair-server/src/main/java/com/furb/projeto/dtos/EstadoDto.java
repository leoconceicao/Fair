package com.furb.projeto.dtos;

public class EstadoDto {

    private Integer idEstado;
    private String dsEstado;
    private String dsSigla;

    public EstadoDto() {
    }

    public EstadoDto(Integer idEstado, String dsEstado, String dsSigla) {
        this.idEstado = idEstado;
        this.dsEstado = dsEstado;
        this.dsSigla = dsSigla;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getDsEstado() {
        return dsEstado;
    }

    public void setDsEstado(String dsEstado) {
        this.dsEstado = dsEstado;
    }

    public String getDsSigla() {
        return dsSigla;
    }

    public void setDsSigla(String dsSigla) {
        this.dsSigla = dsSigla;
    }
}

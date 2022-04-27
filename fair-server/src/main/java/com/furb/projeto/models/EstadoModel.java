package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Estado")
public class EstadoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEstado;
    @Column(nullable = false, length = 45)
    private String dsEstado;
    @Column(nullable = false, length = 45)
    private String dsSigla;

    public EstadoModel() {
    }

    public EstadoModel(Integer idEstado, String dsEstado, String dsSigla) {
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

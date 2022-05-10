package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cidade")
public class CidadeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCidade;
    @Column(nullable = false, length = 45)
    private String dsCidade;
    @OneToOne
    @JoinColumn(name = "fkEstado")
    private EstadoModel fkEstado;

    public CidadeModel() {
    }

    public CidadeModel(Integer idCidade, String dsCidade, EstadoModel fkEstado) {
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

    public EstadoModel getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(EstadoModel fkEstado) {
        this.fkEstado = fkEstado;
    }
}

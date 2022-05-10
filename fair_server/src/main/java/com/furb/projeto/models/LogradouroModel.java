package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Logradouro")
public class LogradouroModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLogradouro;
    @Column(nullable = false, length = 45)
    private String nomeLogradouro;
    @Column(nullable = false, length = 45)
    private String bairro;
    @Column(nullable = false, length = 9)
    private String cep;
    @OneToOne
    @JoinColumn(name = "fkCidade")
    private CidadeModel fkCidade;

    public LogradouroModel() {
    }

    public LogradouroModel(Integer idLogradouro, String nomeLogradouro, String bairro, String cep, CidadeModel fkCidade) {
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

    public CidadeModel getFkCidade() {
        return fkCidade;
    }

    public void setFkCidade(CidadeModel fkCidade) {
        this.fkCidade = fkCidade;
    }
}

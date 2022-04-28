package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Loja")
public class LojaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLoja;
    @Column(nullable = false, length = 45)
    private String nome;
    @Column(nullable = false, length = 45)
    private String cnpj;
    @Column(nullable = false, length = 45)
    private String telefone;

    @OneToOne
    @JoinColumn(name = "fkLogradouro")
    private LogradouroModel fkLogradouro;

    public LojaModel() {
    }

    public LojaModel(Integer idLoja, String nome, String cnpj, String telefone, LogradouroModel fkLogradouro) {
        this.idLoja = idLoja;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.fkLogradouro = fkLogradouro;
    }

    public Integer getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Integer idLoja) {
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

    public LogradouroModel getFkLogradouro() {
        return fkLogradouro;
    }

    public void setFkLogradouro(LogradouroModel fkLogradouro) {
        this.fkLogradouro = fkLogradouro;
    }
}

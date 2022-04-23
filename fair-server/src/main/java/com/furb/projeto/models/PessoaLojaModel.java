package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Pessoa_Loja")
public class PessoaLojaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 11)
    private Integer tipoPessoa;

    @OneToOne
    @JoinColumn(name = "idLoja")
    private String fkLoja;

    @OneToOne
    @JoinColumn(name = "idPessoa")
    private String fkPessoa;

    public PessoaLojaModel(String fkLoja, String fkPessoa) {
        this.fkLoja = fkLoja;
        this.fkPessoa = fkPessoa;
    }

    public PessoaLojaModel() {
    }

    public String getFkLoja() {
        return fkLoja;
    }

    public void setFkLoja(String fkLoja) {
        this.fkLoja = fkLoja;
    }

    public String getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(String fkPessoa) {
        this.fkPessoa = fkPessoa;
    }
}

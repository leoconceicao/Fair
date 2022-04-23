package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Pessoa_Loja")
public class PessoaLojaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPessoaLoja;

    @Column(length = 11)
    private Integer tipoPessoa;

    @OneToOne
    @JoinColumn(name = "idLoja")
    private LojaModel fkLoja;

    @OneToOne
    @JoinColumn(name = "idPessoa")
    private PessoaModel fkPessoa;

    public PessoaLojaModel(Integer idPessoaLoja, Integer tipoPessoa, LojaModel fkLoja, PessoaModel fkPessoa) {
        this.idPessoaLoja = idPessoaLoja;
        this.tipoPessoa = tipoPessoa;
        this.fkLoja = fkLoja;
        this.fkPessoa = fkPessoa;
    }

    public PessoaLojaModel() {
    }

    public Integer getIdPessoaLoja() {
        return idPessoaLoja;
    }

    public void setIdPessoaLoja(Integer idPessoaLoja) {
        this.idPessoaLoja = idPessoaLoja;
    }

    public Integer getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Integer tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public LojaModel getFkLoja() {
        return fkLoja;
    }

    public void setFkLoja(LojaModel fkLoja) {
        this.fkLoja = fkLoja;
    }

    public PessoaModel getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(PessoaModel fkPessoa) {
        this.fkPessoa = fkPessoa;
    }
}

package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Funcionario")
public class FuncionarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idFuncionario;

    @Column(nullable = false, length = 2)
    private String cargo;

    @OneToOne
    @JoinColumn(name = "idLoja")
    private LojaModel fkLoja;

    @OneToOne
    @JoinColumn(name = "idPessoa")
    private PessoaModel fkPessoa;

    public FuncionarioModel(Integer idFuncionario, String cargo, LojaModel fkLoja, PessoaModel fkPessoa) {
        this.idFuncionario = idFuncionario;
        this.cargo = cargo;
        this.fkLoja = fkLoja;
        this.fkPessoa = fkPessoa;
    }

    public FuncionarioModel() {
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

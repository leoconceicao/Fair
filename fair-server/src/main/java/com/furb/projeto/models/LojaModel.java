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
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private String cnpj;
    @Column(nullable = false)
    private String telefone;

    public LojaModel(Integer idLoja, String nome, String endereco, String cnpj, String telefone) {
        this.idLoja = idLoja;
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    public LojaModel() {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
}

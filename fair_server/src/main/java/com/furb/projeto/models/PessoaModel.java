package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Pessoa")
public class PessoaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPessoa;
    @Column(nullable = false, length = 45)
    private String nome;
    @Column(nullable = false, length = 45)
    private String telefone;
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false, length = 45, unique=true)
    private String email;

    @Column(nullable = false, length = 45)
    private String password;

    @OneToOne
    @JoinColumn(name = "idLogradouro")
    private LogradouroModel fkLogradouro;

    public PessoaModel() {
    }

    public PessoaModel(Integer idPessoa, String nome, String telefone, String cpf, String email, String password, LogradouroModel fkLogradouro) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.fkLogradouro = fkLogradouro;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LogradouroModel getFkLogradouro() {
        return fkLogradouro;
    }

    public void setFkLogradouro(LogradouroModel fkLogradouro) {
        this.fkLogradouro = fkLogradouro;
    }
}

package com.furb.projeto.dtos;

public class PessoaDto {

    private Integer idPessoa;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private Integer fkLogradouro;

    public PessoaDto() {
    }

    public PessoaDto(Integer idPessoa, String nome, String telefone, String cpf, String email, Integer fkLogradouro) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
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

    public Integer getFkLogradouro() {
        return fkLogradouro;
    }

    public void setFkLogradouro(Integer fkLogradouro) {
        this.fkLogradouro = fkLogradouro;
    }
}

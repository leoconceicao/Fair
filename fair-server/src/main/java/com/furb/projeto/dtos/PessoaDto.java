package com.furb.projeto.dtos;

public class PessoaDto {

    private Integer idPessoa;
    private String nome;
    private String telefone;
    private String endereco;
    private String cpf;
    private String email;

    public PessoaDto(Integer idPessoa, String nome, String telefone, String endereco, String cpf, String email) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.email = email;
    }

    public PessoaDto() {
    }

    public Integer getPessoaId() {
        return idPessoa;
    }

    public void setPessoaId(Integer idPessoa) {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
}

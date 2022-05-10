package com.furb.projeto.dtos;

public class ComunicacaoDto {

    private Integer idMensagem;
    private Integer fkProduto;
    private Integer fkPessoaRemetente;
    private Integer fkPessoaDestinatario;
    private String dsConteudo;

    public ComunicacaoDto(Integer idMensagem, Integer fkProduto, Integer fkPessoaRemetente, Integer fkPessoaDestinatario, String dsConteudo) {
        this.idMensagem = idMensagem;
        this.fkProduto = fkProduto;
        this.fkPessoaRemetente = fkPessoaRemetente;
        this.fkPessoaDestinatario = fkPessoaDestinatario;
        this.dsConteudo = dsConteudo;
    }

    public ComunicacaoDto() {
    }

    public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Integer getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Integer fkProduto) {
        this.fkProduto = fkProduto;
    }

    public Integer getFkPessoaRemetente() {
        return fkPessoaRemetente;
    }

    public void setFkPessoaRemetente(Integer fkPessoaRemetente) {
        this.fkPessoaRemetente = fkPessoaRemetente;
    }

    public Integer getFkPessoaDestinatario() {
        return fkPessoaDestinatario;
    }

    public void setFkPessoaDestinatario(Integer fkPessoaDestinatario) {
        this.fkPessoaDestinatario = fkPessoaDestinatario;
    }

    public String getDsConteudo() {
        return dsConteudo;
    }

    public void setDsConteudo(String dsConteudo) {
        this.dsConteudo = dsConteudo;
    }
}

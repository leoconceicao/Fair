package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Comunicacao")
public class ComunicacaoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMensagem;

    @OneToOne
    @JoinColumn(name = "idProduto")
    private ProdutoModel fkProduto;

    @ManyToOne
    @JoinColumn(name = "idRemetente")
    private PessoaModel fkPessoaRemetente;

    @ManyToOne
    @JoinColumn(name = "idDestinatario")
    private PessoaModel fkPessoaDestinatario;

    @Column(nullable = false, length = 2000)
    private String dsConteudo;

    public ComunicacaoModel(Integer idMensagem, ProdutoModel fkProduto, PessoaModel fkPessoaRemetente, PessoaModel fkPessoaDestinatario, String dsConteudo) {
        this.idMensagem = idMensagem;
        this.fkProduto = fkProduto;
        this.fkPessoaRemetente = fkPessoaRemetente;
        this.fkPessoaDestinatario = fkPessoaDestinatario;
        this.dsConteudo = dsConteudo;
    }

    public ComunicacaoModel() {
    }

    public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public ProdutoModel getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(ProdutoModel fkProduto) {
        this.fkProduto = fkProduto;
    }

    public PessoaModel getFkPessoaRemetente() {
        return fkPessoaRemetente;
    }

    public void setFkPessoaRemetente(PessoaModel fkPessoaRemetente) {
        this.fkPessoaRemetente = fkPessoaRemetente;
    }

    public PessoaModel getFkPessoaDestinatario() {
        return fkPessoaDestinatario;
    }

    public void setFkPessoaDestinatario(PessoaModel fkPessoaDestinatario) {
        this.fkPessoaDestinatario = fkPessoaDestinatario;
    }

    public String getDsConteudo() {
        return dsConteudo;
    }

    public void setDsConteudo(String dsConteudo) {
        this.dsConteudo = dsConteudo;
    }
}

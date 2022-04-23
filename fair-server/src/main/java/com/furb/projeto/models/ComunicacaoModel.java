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
    private String fkProduto;

    @OneToOne
    @JoinColumn(name = "idPessoa")
    private String fkPessoaRemetente;

    @OneToOne
    @JoinColumn(name = "idPessoa")
    private String fkPessoaDestinatario;

    @Column(nullable = false, length = 2000)
    private String dsConteudo;

    public ComunicacaoModel(Integer idMensagem, String fkProduto, String fkPessoaRemetente, String fkPessoaDestinatario, String dsConteudo) {
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

    public String getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(String fkProduto) {
        this.fkProduto = fkProduto;
    }

    public String getFkPessoaRemetente() {
        return fkPessoaRemetente;
    }

    public void setFkPessoaRemetente(String fkPessoaRemetente) {
        this.fkPessoaRemetente = fkPessoaRemetente;
    }

    public String getFkPessoaDestinatario() {
        return fkPessoaDestinatario;
    }

    public void setFkPessoaDestinatario(String fkPessoaDestinatario) {
        this.fkPessoaDestinatario = fkPessoaDestinatario;
    }

    public String getDsConteudo() {
        return dsConteudo;
    }

    public void setDsConteudo(String dsConteudo) {
        this.dsConteudo = dsConteudo;
    }
}

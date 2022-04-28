package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "Pessoa_Loja")
public class ProdutoPedidoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProdutoPedido;
    @OneToOne
    @JoinColumn(name = "idProduto")
    private ProdutoModel fkProduto;

    @OneToOne
    @JoinColumn(name = "idPedido")
    private PedidoModel fkPedido;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, length = 10)
    private BigInteger peso;

    public ProdutoPedidoModel() {
    }

    public ProdutoPedidoModel(Integer idProdutoPedido, ProdutoModel fkProduto, PedidoModel fkPedido, Integer quantidade, BigInteger peso) {
        this.idProdutoPedido = idProdutoPedido;
        this.fkProduto = fkProduto;
        this.fkPedido = fkPedido;
        this.quantidade = quantidade;
        this.peso = peso;
    }

    public Integer getIdProdutoPedido() {
        return idProdutoPedido;
    }

    public void setIdProdutoPedido(Integer idProdutoPedido) {
        this.idProdutoPedido = idProdutoPedido;
    }

    public ProdutoModel getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(ProdutoModel fkProduto) {
        this.fkProduto = fkProduto;
    }

    public PedidoModel getFkPedido() {
        return fkPedido;
    }

    public void setFkPedido(PedidoModel fkPedido) {
        this.fkPedido = fkPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigInteger getPeso() {
        return peso;
    }

    public void setPeso(BigInteger peso) {
        this.peso = peso;
    }
}

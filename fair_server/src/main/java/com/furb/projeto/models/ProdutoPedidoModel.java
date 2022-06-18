package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Produto_Pedido")
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

    @Column()
    private Integer quantidade;

    @Column(length = 10)
    private BigDecimal peso;

    @Column(nullable = false, length = 10)
    private BigDecimal preco;

    public ProdutoPedidoModel() {
    }

    public ProdutoPedidoModel(Integer idProdutoPedido, ProdutoModel fkProduto, PedidoModel fkPedido, Integer quantidade, BigDecimal peso, BigDecimal preco) {
        this.idProdutoPedido = idProdutoPedido;
        this.fkProduto = fkProduto;
        this.fkPedido = fkPedido;
        this.quantidade = quantidade;
        this.peso = peso;
        this.preco = preco;
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

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}

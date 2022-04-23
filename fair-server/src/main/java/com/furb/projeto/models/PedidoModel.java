package com.furb.projeto.models;

import com.fasterxml.jackson.databind.node.BigIntegerNode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "Pedido")
public class PedidoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPedido;
    @Column(nullable = false, length = 11)
    private Integer quantidade;
    @Column
    private String periodicidade;
    @Column(length = 11)
    private Integer numeroEntregas;
    @Column(nullable = false, length = 10)
    private BigInteger peso;
    @OneToOne
    @JoinColumn(name = "idPessoa")
    private String fkCliente;
    @OneToOne
    @JoinColumn(name = "idPessoa")
    private String fkVendedor;

    public PedidoModel(Integer idPedido, Integer quantidade, String periodicidade, Integer numeroEntregas, BigInteger peso, String fkCliente, String fkVendedor) {
        this.idPedido = idPedido;
        this.quantidade = quantidade;
        this.periodicidade = periodicidade;
        this.numeroEntregas = numeroEntregas;
        this.peso = peso;
        this.fkCliente = fkCliente;
        this.fkVendedor = fkVendedor;
    }

    public PedidoModel() {
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public Integer getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(Integer numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public BigInteger getPeso() {
        return peso;
    }

    public void setPeso(BigInteger peso) {
        this.peso = peso;
    }

    public String getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(String fkCliente) {
        this.fkCliente = fkCliente;
    }

    public String getFkVendedor() {
        return fkVendedor;
    }

    public void setFkVendedor(String fkVendedor) {
        this.fkVendedor = fkVendedor;
    }
}

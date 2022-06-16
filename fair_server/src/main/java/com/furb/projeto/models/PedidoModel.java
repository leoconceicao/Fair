package com.furb.projeto.models;

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

    @Column(nullable = false)
    private String data;
    @Column(nullable = false)
    private String periodicidade;
    @Column(nullable = false, length = 10)
    private BigInteger peso;
    @OneToOne
    @JoinColumn(name = "idCliente")
    private PessoaModel fkCliente;
    @OneToOne
    @JoinColumn(name = "idVendedor")
    private PessoaModel fkVendedor;

    public PedidoModel(Integer idPedido, String data, String periodicidade, BigInteger peso, PessoaModel fkCliente, PessoaModel fkVendedor) {
        this.idPedido = idPedido;
        this.data = data;
        this.periodicidade = periodicidade;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public BigInteger getPeso() {
        return peso;
    }

    public void setPeso(BigInteger peso) {
        this.peso = peso;
    }

    public PessoaModel getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(PessoaModel fkCliente) {
        this.fkCliente = fkCliente;
    }

    public PessoaModel getFkVendedor() {
        return fkVendedor;
    }

    public void setFkVendedor(PessoaModel fkVendedor) {
        this.fkVendedor = fkVendedor;
    }
}

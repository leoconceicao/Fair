package com.furb.projeto.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

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
    private BigDecimal peso;
    @OneToOne
    @JoinColumn(name = "idCliente")
    private PessoaModel fkCliente;
    @OneToOne
    @JoinColumn(name = "idVendedor")
    private LojaModel fkVendedor;

    public PedidoModel(Integer idPedido, String data, String periodicidade, BigDecimal peso, PessoaModel fkCliente, LojaModel fkVendedor) {
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

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public PessoaModel getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(PessoaModel fkCliente) {
        this.fkCliente = fkCliente;
    }

    public LojaModel getFkVendedor() {
        return fkVendedor;
    }

    public void setFkVendedor(LojaModel fkVendedor) {
        this.fkVendedor = fkVendedor;
    }
}

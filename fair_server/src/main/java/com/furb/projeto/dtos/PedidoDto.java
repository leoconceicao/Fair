package com.furb.projeto.dtos;

import java.math.BigDecimal;

public class PedidoDto {

    private Integer idPedido;

    private String data;
    private String periodicidade;
    private BigDecimal peso;
    private Integer fkCliente;
    private Integer fkVendedor;

    public PedidoDto(Integer idPedido, String data, Integer quantidade, String periodicidade, BigDecimal peso, Integer fkCliente, Integer fkVendedor) {
        this.idPedido = idPedido;
        this.data = data;
        this.periodicidade = periodicidade;
        this.peso = peso;
        this.fkCliente = fkCliente;
        this.fkVendedor = fkVendedor;
    }

    public PedidoDto() {
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

    public Integer getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Integer fkCliente) {
        this.fkCliente = fkCliente;
    }

    public Integer getFkVendedor() {
        return fkVendedor;
    }

    public void setFkVendedor(Integer fkVendedor) {
        this.fkVendedor = fkVendedor;
    }
}

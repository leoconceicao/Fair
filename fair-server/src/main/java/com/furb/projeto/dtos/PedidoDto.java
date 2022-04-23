package com.furb.projeto.dtos;

import java.math.BigInteger;

public class PedidoDto {

    private Integer idPedido;
    private Integer quantidade;
    private String periodicidade;
    private Integer numeroEntregas;
    private BigInteger peso;
    private Integer fkCliente;
    private Integer fkVendedor;

    public PedidoDto(Integer idPedido, Integer quantidade, String periodicidade, Integer numeroEntregas, BigInteger peso, Integer fkCliente, Integer fkVendedor) {
        this.idPedido = idPedido;
        this.quantidade = quantidade;
        this.periodicidade = periodicidade;
        this.numeroEntregas = numeroEntregas;
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

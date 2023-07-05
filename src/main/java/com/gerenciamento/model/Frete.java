package com.gerenciamento.model;

import com.gerenciamento.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "frete")
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "produto")
    @NotNull
    private String produto;
    @Column(name = "distancia")
    @NotNull
    private Integer distancia;

    @Column(name = "veiculo")
    @NotNull
    private String veiculo;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "valorTotal")
    @NotNull
    private double valorTotal;

    @Column(name = "valorEntregador")
    @NotNull
    private double valorEntregador;

    @Column(name = "data")
    private LocalDate data;


    //getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorEntregador() {
        return valorEntregador;
    }

    public void setValorEntregador(double valorEntregador) {
        this.valorEntregador = valorEntregador;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}

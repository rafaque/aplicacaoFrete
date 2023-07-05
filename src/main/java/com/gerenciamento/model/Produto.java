package com.gerenciamento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    @NotBlank(message = "O nome não pode ser vazio")
    @NotNull
    private String nome;
    private double peso;

    private double pesoAntigo;

    //getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPesoAntigo() {
        return pesoAntigo;
    }

    public void setPesoAntigo(double pesoAntigo) {
        this.pesoAntigo = pesoAntigo;
    }
}

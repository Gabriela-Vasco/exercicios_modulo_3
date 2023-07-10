package com.m3s02exercicios.despesas.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "despesas")
@Entity
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String credor;

    @Column(nullable = false)
    private Date dataVencimento = new Date();

    private Date dataPagemento = new Date();

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    private String descricao;

    @Column(length = 2, nullable = false)
    private String status = "PE";
}

package com.caso1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cleta")
public class Cleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cleta")
    private Integer idCleta;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "precio_tope")
    private Double precioTope;

    @Column(name = "salida_cleta")
    private Integer salidaCleta;

    @Column(name = "imagen_cleta")
    private String imagenCleta;
}

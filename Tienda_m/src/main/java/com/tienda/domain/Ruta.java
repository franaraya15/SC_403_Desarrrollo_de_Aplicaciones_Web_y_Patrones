/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Faraya
 */
@Data
@Entity
@Table(name="ruta")//se pone como está escrito en bd
public class Ruta implements Serializable {
    //Para generar identificadores unicos de objetos serializados
    private static final long serialVersionUID = 1l;
    
    //anotaciones de categoria
    @Id//es llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRuta; 
    
    //anotaciones de descripcion
    @Column(nullable = false, length = 25)
    @NotNull
    @Size(max=25)
    private String ruta;
    
    private boolean requiereRol;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_rol")
    private Rol rol;
 
}

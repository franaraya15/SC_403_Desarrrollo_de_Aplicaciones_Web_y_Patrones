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
@Table(name="rol")//se pone como está escrito en bd
public class Rol implements Serializable {
    //Para generar identificadores unicos de objetos serializados
    private static final long serialVersionUID = 1l;
    
    //anotaciones de categoria
    @Id//es llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol; 
    
    //anotaciones de descripcion
    @Column(unique = true, nullable = false, length = 20)
    @NotNull
    @Size(max=20)
    private String rol;
    
}

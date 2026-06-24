/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author maria
 */
@Data
@Entity
@Table(name="categoria")//se pone como está escrito en bd
public class Categoria implements Serializable {
    //Para generar identificadores unicos de objetos serializados
    private static final long serialVersionUID = 1l;
    
    //anotaciones de categoria
    @Id//es llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria; 
    
    //anotaciones de descripcion
    @Column(unique = true, nullable = false, length = 50)
    @NotNull
    @Size(max=50)
    private String descripcion;
    
    //anotaciones de rutaImagen
    @Column(length = 1024)
    @Size(max=1024)
    private String rutaImagen;
    
    private boolean activo;
    
    
    @OneToMany (mappedBy="categoria")
    private List<Producto> productos;
    
}

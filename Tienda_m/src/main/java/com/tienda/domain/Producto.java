/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import static org.thymeleaf.templatemode.TemplateMode.TEXT;

/**
 *
 * @author maria
 */
@Data
@Entity
@Table(name="producto")//se pone como está escrito en bd
public class Producto implements Serializable {
    //Para generar identificadores unicos de objetos serializados
    private static final long serialVersionUID = 1l;
    
    //anotaciones de categoria
    @Id//es llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto; 
    //private Integer idCategoria; Ya no se usa porque está en la asociación ManyToOne
    
    //anotaciones de descripcion
    @Column(unique = true, nullable = false, length = 50)
    @NotNull(message ="La descripción no puede ser vacía")
    @Size(max=50,message="La descripción no puede ser más de 50 caracteres")
    private String descripcion;
    
    @Column (columnDefinition ="TEXT")
    private String detalle;
    
    @Column (precision=12, scale=2)
    @DecimalMin(value="0.00", inclusive=true, message="El precio no puede ser negativo")
    private BigDecimal precio; 
    
    @Min(value=0,message="Las existencias no puede ser negativas")
    private Integer existencias; 
    
    //anotaciones de rutaImagen
    @Column(length = 1024)
    @Size(max=1024)
    private String rutaImagen;
    
    private boolean activo;
    
    @ManyToOne
    @JoinColumn (name="id_categoria")
    private Categoria categoria;
    
}

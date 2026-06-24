package com.practica.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Entidad que representa una suculenta del catalogo.
 */
@Data
@Entity
@Table(name = "suculenta")
public class Suculenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suculenta")
    private Integer idSuculenta;

    @Column(name = "nombre_comun", nullable = false, length = 60)
    @NotBlank(message = "El nombre comun es obligatorio")
    @Size(max = 60, message = "El nombre comun no puede superar 60 caracteres")
    private String nombreComun;

    @Column(name = "nombre_cientifico", length = 80)
    @Size(max = 80, message = "El nombre cientifico no puede superar 80 caracteres")
    private String nombreCientifico;

    @Column(name = "familia", length = 50)
    @Size(max = 50, message = "La familia no puede superar 50 caracteres")
    private String familia;

    @Column(name = "color_principal", length = 30)
    @Size(max = 30, message = "El color principal no puede superar 30 caracteres")
    private String colorPrincipal;

    @Column(name = "altura_cm")
    @Min(value = 0, message = "La altura no puede ser negativa")
    private Integer alturaCm;

    @Column(name = "precio_estimado", precision = 10, scale = 2)
    @DecimalMin(value = "0.00", inclusive = true, message = "El precio no puede ser negativo")
    private BigDecimal precioEstimado;

    @Column(name = "nivel_riego", length = 20)
    @Size(max = 20, message = "El nivel de riego no puede superar 20 caracteres")
    private String nivelRiego;

    @Column(name = "ruta_imagen", length = 1024)
    @Size(max = 1024, message = "La ruta de imagen no puede superar 1024 caracteres")
    private String rutaImagen;

    @Column(name = "activo")
    private boolean activo;

}

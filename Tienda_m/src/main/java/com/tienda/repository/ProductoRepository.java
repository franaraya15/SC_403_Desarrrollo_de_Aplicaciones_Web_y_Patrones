/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Faraya
 */
@Repository
public interface ProductoRepository 
        extends JpaRepository<Producto, Integer>{
    //se crea una consulta derivada ...
    public List<Producto> findByActivoTrue();
   //consulta derivada que recupera los productos de un rango de precios ordenado de precio ascendentemente
    public List<Producto> findByPrecioBetweenOrderByPrecioAsc(double precioInf,double precioSup);
    
   //consulta jpql que recupera los productos de un rango de precios ordenado de precio ascendentemente
    @Query(value="SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaJPQL(double precioInf,double precioSup);
    
   //consulta SQL que recupera los productos de un rango de precios ordenado de precio ascendentemente
    @Query(nativeQuery=true,
            value="SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaSQL(double precioInf,double precioSup);

    //PRACTICA #2: consulta ampliada JPQL - busca productos cuya descripcion o detalle
    //contenga el texto indicado (LIKE, sin distinguir mayusculas) y con existencias mayores
    //o iguales al minimo, ordenados por precio ascendente.
    @Query(value="SELECT p FROM Producto p "
            + "WHERE (LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :texto, '%')) "
            + "   OR  LOWER(p.detalle)     LIKE LOWER(CONCAT('%', :texto, '%'))) "
            + "AND p.existencias >= :existMin "
            + "ORDER BY p.precio ASC")
    public List<Producto> consultaPractica2(String texto, int existMin);

}


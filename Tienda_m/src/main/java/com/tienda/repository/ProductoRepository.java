/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author maria
 */
@Repository
public interface ProductoRepository 
        extends JpaRepository<Producto, Integer>{
    //se crea una consulta derivada ...
    public List<Producto> findByActivoTrue();
}


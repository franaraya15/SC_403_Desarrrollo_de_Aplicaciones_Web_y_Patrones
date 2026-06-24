package com.practica.repository;

import com.practica.domain.Suculenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuculentaRepository extends JpaRepository<Suculenta, Integer> {

    // Consulta derivada: devuelve solo las suculentas activas
    List<Suculenta> findByActivoTrue();

}

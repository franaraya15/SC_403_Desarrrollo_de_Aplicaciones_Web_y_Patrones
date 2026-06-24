package com.practica.service;

import com.practica.domain.Suculenta;
import com.practica.repository.SuculentaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SuculentaService {

    private final SuculentaRepository suculentaRepository;

    public SuculentaService(SuculentaRepository suculentaRepository) {
        this.suculentaRepository = suculentaRepository;
    }

    // Devuelve todas las suculentas, o solo las activas si se solicita
    @Transactional(readOnly = true)
    public List<Suculenta> getSuculentas(boolean soloActivas) {
        if (soloActivas) {
            return suculentaRepository.findByActivoTrue();
        }
        return suculentaRepository.findAll();
    }

    // Recupera una suculenta por su identificador
    @Transactional(readOnly = true)
    public Optional<Suculenta> getSuculenta(Integer idSuculenta) {
        return suculentaRepository.findById(idSuculenta);
    }

    // Crea o actualiza una suculenta
    @Transactional
    public void save(Suculenta suculenta) {
        suculentaRepository.save(suculenta);
    }

    // Elimina una suculenta por su identificador
    @Transactional
    public void delete(Integer idSuculenta) {
        suculentaRepository.deleteById(idSuculenta);
    }

}

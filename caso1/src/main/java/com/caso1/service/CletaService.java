package com.caso1.service;

import com.caso1.domain.Cleta;
import com.caso1.repository.CletaRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CletaService {

    private final CletaRepository cletaRepository;
    private final FirebaseStorageService firebaseStorageService;

    public CletaService(CletaRepository cletaRepository, FirebaseStorageService firebaseStorageService) {
        this.cletaRepository = cletaRepository;
        this.firebaseStorageService = firebaseStorageService;
    }

    @Transactional(readOnly = true)
    public List<Cleta> buscarTodos() {
        return cletaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Cleta> buscarPorId(Integer idCleta) {
        return cletaRepository.findById(idCleta);
    }

    @Transactional
    public void guardar(Cleta cleta, MultipartFile imagenFile) {
        cleta = cletaRepository.save(cleta);
        if (imagenFile != null && !imagenFile.isEmpty()) {
            try {
                String imagenCleta = firebaseStorageService.uploadImage(
                        imagenFile, "cleta", cleta.getIdCleta());
                cleta.setImagenCleta(imagenCleta);
                cletaRepository.save(cleta);
            } catch (IOException e) {
            }
        }
    }

    @Transactional
    public void eliminar(Integer idCleta) {
        cletaRepository.deleteById(idCleta);
    }
}

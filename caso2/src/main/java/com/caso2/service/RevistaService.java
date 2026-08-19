package com.caso2.service;

import com.caso2.domain.Revista;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class RevistaService {

    private final List<Revista> catalogoRevistas = new ArrayList<>();

    public RevistaService() {
        catalogoRevistas.add(new Revista(1, "National Geographic", "15/01/2026", 120));
        catalogoRevistas.add(new Revista(2, "Muy Interesante", "22/03/2026", 84));
        catalogoRevistas.add(new Revista(3, "Summa Negocios", "05/06/2026", 96));
    }

    public void guardar(Revista revista) {
        var posicion = -1;
        for (var registro : catalogoRevistas) {
            posicion++;
            if (Objects.equals(registro.getIdRevista(), revista.getIdRevista())) {
                catalogoRevistas.remove(posicion);
                break;
            }
        }
        catalogoRevistas.add(revista);
    }

    public Revista buscarRevista(Revista revista) {
        for (var registro : catalogoRevistas) {
            if (Objects.equals(registro.getIdRevista(), revista.getIdRevista())) {
                return registro;
            }
        }
        return null;
    }

    public List<Revista> listarRevistas() {
        catalogoRevistas.sort(Comparator.comparingInt(Revista::getIdRevista));
        return catalogoRevistas;
    }
}

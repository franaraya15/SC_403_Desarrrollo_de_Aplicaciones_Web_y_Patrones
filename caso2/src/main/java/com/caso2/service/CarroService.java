package com.caso2.service;

import com.caso2.domain.Carro;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 *
 * @author wmolina
 */
@Service
public class CarroService {

    public List<Carro> listaCarros = new ArrayList<>();

    public CarroService() {
        listaCarros.add(new Carro(1, "Toyota Tercel", 4));
        listaCarros.add(new Carro(2, "Lexus", 8));
    }

    public void save(Carro carro) {
        var indice = -1;
        for (var a : listaCarros) {
            indice++;
            if (Objects.equals(a.getIdCarro(), carro.getIdCarro())) {
                listaCarros.remove(indice);
                break;
            }
        }
        listaCarros.add(carro);
    }

    public void delete(Carro carro) {
        int indice = -1;
        for (var a : listaCarros) {
            indice++;
            if (Objects.equals(a.getIdCarro(), carro.getIdCarro())) {
                listaCarros.remove(indice);
                break;
            }
        }
    }

    public Carro getCarro(Carro carro) {
        for (var a : listaCarros) {
            if (Objects.equals(a.getIdCarro(), carro.getIdCarro())) {
                return a;
            }
        }
        return null;
    }

    public List<Carro> getCarros() {
        return listaCarros;
    }
}

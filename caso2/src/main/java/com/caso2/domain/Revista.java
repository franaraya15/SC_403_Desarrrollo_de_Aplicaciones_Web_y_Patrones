package com.caso2.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Revista {

    private int idRevista;
    private String titulo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private int paginasRevista;

    public Revista() {
    }

    public Revista(int idRevista, String titulo, int paginasRevista) {
        this.idRevista = idRevista;
        this.titulo = titulo;
        this.paginasRevista = paginasRevista;
        this.fecha = new Date();
    }

    public Revista(int idRevista, String titulo, String fechaTexto, int paginasRevista) {
        this.idRevista = idRevista;
        this.titulo = titulo;
        this.paginasRevista = paginasRevista;
        this.fecha = convertirFecha("dd/MM/yyyy", fechaTexto);
    }

    private Date convertirFecha(String patronFecha, String fechaTexto) {
        try {
            SimpleDateFormat formateador = new SimpleDateFormat(patronFecha);
            return formateador.parse(fechaTexto);
        } catch (ParseException ex) {
            Logger.getLogger(Revista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

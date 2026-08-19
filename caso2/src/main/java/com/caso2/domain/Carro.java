package com.caso2.domain;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

@Data
public class Carro {

    private int idCarro;
    private String descripcion;
    private int cilindros;
    private Date modelo;

    public Carro() {
    }

    public Carro(int idCarro, String descripcion, int cilindros) {
        this.idCarro = idCarro;
        this.descripcion = descripcion;
        this.cilindros = cilindros;
        this.modelo = new Date();
    }

    private Date getDateFormat(String formatPattern, String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
            return formatter.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

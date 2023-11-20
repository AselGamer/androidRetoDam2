package com.example.juegalmi.model;

import java.io.Serializable;

public class FotoGaleria implements Serializable {
    private int recurso = -1;
    private String texto = "";

    public FotoGaleria(int recurso, String texto) {
        this.recurso = recurso;
        this.texto = texto;
    }

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}

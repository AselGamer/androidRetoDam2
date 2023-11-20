package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Etiqueta implements Serializable {
    @SerializedName("idetiqueta")
    private int idetiqueta;
    @SerializedName("nombre")
    private String nombre;

    public Etiqueta(int idetiqueta, String nombre) {
        this.idetiqueta = idetiqueta;
        this.nombre = nombre;
    }

    public int getIdetiqueta() {
        return idetiqueta;
    }

    public void setIdetiqueta(int idetiqueta) {
        this.idetiqueta = idetiqueta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

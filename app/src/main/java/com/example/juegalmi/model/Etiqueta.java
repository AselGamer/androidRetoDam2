package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

public class Etiqueta {
    @SerializedName("idetiqueta")
    int idetiqueta;
    @SerializedName("nombre")
    String nombre;

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

package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Plataforma implements Serializable {
    @SerializedName("idplataforma")
    private int idplataforma;
    @SerializedName("nombre")
    private String nombre;

    public Plataforma(int idplataforma, String nombre) {
        this.idplataforma = idplataforma;
        this.nombre = nombre;
    }

    public int getIdplataforma() {
        return idplataforma;
    }

    public void setIdplataforma(int idplataforma) {
        this.idplataforma = idplataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

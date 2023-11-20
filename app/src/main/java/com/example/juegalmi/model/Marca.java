package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Marca implements Serializable {
    @SerializedName("idmarca")
    private int idmarca;

    @SerializedName("nombre")
    private String nombre;

    public Marca(int idmarca, String nombre) {
        this.idmarca = idmarca;
        this.nombre = nombre;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

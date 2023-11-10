package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

public class Marca {
    @SerializedName("idmarca")
    String idmarca;

    @SerializedName("nombre")
    String nombre;

    public String getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(String idmarca) {
        this.idmarca = idmarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

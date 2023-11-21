package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EstadoReparacion implements Serializable {
    @SerializedName("idestadoreparacion")
    private int idestadoreparacion;
    @SerializedName("nombre")
    private String nombre;

    public EstadoReparacion(int idestadoreparacion, String nombre) {
        this.idestadoreparacion = idestadoreparacion;
        this.nombre = nombre;
    }

    public int getIdestadoreparacion() {
        return idestadoreparacion;
    }

    public void setIdestadoreparacion(int idestadoreparacion) {
        this.idestadoreparacion = idestadoreparacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

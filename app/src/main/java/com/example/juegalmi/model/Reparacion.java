package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Reparacion implements Serializable {
    @SerializedName("idreparacion")
    private int idreparacion;
    @SerializedName("problema")
    private String problema;
    @SerializedName("comentarioReparacion")
    private String comentarioReparacion;
    @SerializedName("fechaInicio")
    private String fechaInicio;
    @SerializedName("fechaFin")
    private String fechaFin;
    @SerializedName("precio")
    private float precio;
    @SerializedName("idusuario")
    private Usuario idusuario;
    @SerializedName("idestadoreparacion")
    private EstadoReparacion idestadoreparacion;

    public Reparacion(int idreparacion, String problema, String comentarioReparacion, String fechaInicio, String fechaFin, float precio, Usuario idusuario, EstadoReparacion idestadoreparacion) {
        this.idreparacion = idreparacion;
        this.problema = problema;
        this.comentarioReparacion = comentarioReparacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.idusuario = idusuario;
        this.idestadoreparacion = idestadoreparacion;
    }

    public int getIdreparacion() {
        return idreparacion;
    }

    public void setIdreparacion(int idreparacion) {
        this.idreparacion = idreparacion;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getComentarioReparacion() {
        return comentarioReparacion;
    }

    public void setComentarioReparacion(String comentarioReparacion) {
        this.comentarioReparacion = comentarioReparacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public EstadoReparacion getIdestadoreparacion() {
        return idestadoreparacion;
    }

    public void setIdestadoreparacion(EstadoReparacion idestadoreparacion) {
        this.idestadoreparacion = idestadoreparacion;
    }
}

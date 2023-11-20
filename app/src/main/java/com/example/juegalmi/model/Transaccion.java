package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Transaccion implements Serializable {
    @SerializedName("idtransaccion")
    private int idtransaccion;
    @SerializedName("latitud")
    private String latitud;
    @SerializedName("longitud")
    private String longitud;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("fecha_inicio")
    private String fecha_inicio;
    @SerializedName("fecha_fin")
    private String fecha_fin;
    @SerializedName("fecha_devolucion")
    private String fecha_devolucion;
    @SerializedName("precio")
    private float precio;
    @SerializedName("detalles")
    private DetalleTransaccion[] detalles;

    public Transaccion(int idtransaccion, String latitud, String longitud, String fecha, String fecha_inicio, String fecha_fin, String fecha_devolucion, float precio, DetalleTransaccion[] detalles) {
        this.idtransaccion = idtransaccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.fecha_devolucion = fecha_devolucion;
        this.precio = precio;
        this.detalles = detalles;
    }

    public int getIdtransaccion() {
        return idtransaccion;
    }

    public void setIdtransaccion(int idtransaccion) {
        this.idtransaccion = idtransaccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public DetalleTransaccion[] getDetalles() {
        return detalles;
    }

    public void setDetalles(DetalleTransaccion[] detalles) {
        this.detalles = detalles;
    }
}

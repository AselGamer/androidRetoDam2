package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetalleTransaccion implements Serializable {
    @SerializedName("iddetalletransaccion")
    int iddetalletransaccion;
    @SerializedName("idproducto")
    Articulo idproducto;
    @SerializedName("longitud")
    String longitud;
    @SerializedName("precio_total")
    float precio_total;

    public DetalleTransaccion(int iddetalletransaccion, Articulo idproducto, String longitud, float precio_total) {
        this.iddetalletransaccion = iddetalletransaccion;
        this.idproducto = idproducto;
        this.longitud = longitud;
        this.precio_total = precio_total;
    }

    public int getIddetalletransaccion() {
        return iddetalletransaccion;
    }

    public void setIddetalletransaccion(int iddetalletransaccion) {
        this.iddetalletransaccion = iddetalletransaccion;
    }

    public Articulo getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Articulo idproducto) {
        this.idproducto = idproducto;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public float getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(float precio_total) {
        this.precio_total = precio_total;
    }
}

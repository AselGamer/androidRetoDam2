package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetalleTransaccion implements Serializable {
    @SerializedName("iddetalletransaccion")
    private int iddetalletransaccion;
    @SerializedName("idarticulo")
    private Articulo idarticulo;
    /*@SerializedName("idarticulo")
    private int idarticulo;*/
    @SerializedName("precio_total")
    private float precio_total;

    public DetalleTransaccion(Articulo idarticulo) {
        this.idarticulo = idarticulo;
    }

    public int getIddetalletransaccion() {
        return iddetalletransaccion;
    }

    public void setIddetalletransaccion(int iddetalletransaccion) {
        this.iddetalletransaccion = iddetalletransaccion;
    }

    public Articulo getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(Articulo idarticulo) {
        this.idarticulo = idarticulo;
    }

    public float getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(float precio_total) {
        this.precio_total = precio_total;
    }
}
